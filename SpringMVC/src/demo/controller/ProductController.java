package demo.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import demo.Service.IService;
import demo.Service.ProductServiceImpl;
import demo.entity.Category;
import demo.entity.Product;

@Controller
@RequestMapping(value = "product")
public class ProductController {
	@Autowired
	private IService<Product, Integer> productDao;

	@Autowired
	private IService<Category, Integer> categoryDao;

	@Autowired
	private ServletContext servletContext;

	@RequestMapping(value = { "", "/index" })
	public String getListProduct(Model model,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "name", required = false, defaultValue = "") String name) {
		int pageSize = 10;
		int firstResult = (page - 1) * pageSize;
		List<Product> products;
		Long totalRecords;
		if(name == "") {
			totalRecords = productDao.countTotalRecords(null);
			products = productDao.findAll(firstResult, pageSize);
		} else {
			totalRecords = productDao.countTotalRecords(name);
			products = productDao.findAll(firstResult, pageSize, name);
		}
		
		model.addAttribute("pros", products);
		model.addAttribute("totalRecords", totalRecords);
		model.addAttribute("page", page);
		model.addAttribute("name", name);
		return "product/indexProduct";
	}

	@GetMapping(value = "/insertProduct")
	public String insertProduct(Model model) {
		Product product = new Product();
		List<Category> list = categoryDao.findAll();
		model.addAttribute("pro", product);
		model.addAttribute("list", list);
		return "product/insertProduct";
	}

	@RequestMapping(value = { "/edit" })
	public String getListProduct(@RequestParam("id") int id, Model model) {
		Product product = (Product) productDao.findById(id);
		List<Category> list = categoryDao.findAll();
		model.addAttribute("pro", product);
		model.addAttribute("list", list);
		return "product/edit";
	}

	@PostMapping(value = "/insertProduct")
	public String insertProduct(@Valid @ModelAttribute("pro") Product product, BindingResult bindingResult,
			@RequestParam("upload") MultipartFile file, Model model) throws IOException {
		String view = "";
		if (bindingResult.hasErrors()) {
			model.addAttribute("pro", product);
			view = "product/insertProduct";
		} else {

			String fileName = servletContext.getRealPath("/") + "resources\\images\\" + file.getOriginalFilename();
			product.setImage(file.getOriginalFilename());
			try {
				file.transferTo(new File(fileName));
			} catch (IOException e) {
				e.printStackTrace();
			}
			Product products = (Product) productDao.save(product);
			if (!Objects.isNull(products)) {
				view = "redirect:/product";
			} else {
				model.addAttribute("pro", product);
				view = "product/insertProduct";
			}
		}
		return view;
	}

	@PostMapping(value = "/updateProduct")
	public String updateProduct(@Valid @ModelAttribute("prod") Product product, BindingResult bindingResult,
			@RequestParam("upload") MultipartFile file, Model model) throws IOException {
		String view = "";
		if (bindingResult.hasErrors()) {
			model.addAttribute("pro", product);
			view = "product/edit?id=" + product.getId();
		} else {
			String nameImg = file.getOriginalFilename();
			if(nameImg == "") {
				Product productDetail = (Product) productDao.findById(product.getId());
				product.setImage(productDetail.getImage());
			} else {
				String fileName = servletContext.getRealPath("/") + "resources\\images\\" + file.getOriginalFilename();
				product.setImage(file.getOriginalFilename());
				try {
					file.transferTo(new File(fileName));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			Product products = (Product) productDao.save(product);
			if (!Objects.isNull(products)) {
				view = "redirect:/product";
			} else {
				model.addAttribute("pro", product);
				view = "product/edit?id=" + product.getId();
			}
		}
		return view;
	}

	@RequestMapping(value = { "/delete" })
	public String deleteProduct(@RequestParam("id") int id, Model model) {
		boolean check = productDao.deleteById(id);
		List<Product> products = productDao.findAll();
		model.addAttribute("pros", products);
		return "redirect:/product";
	}
}
