package demo.controller;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import demo.Service.IService;
import demo.Service.ProductServiceImpl;
import demo.entity.Category;
import demo.entity.Product;

@Controller
@RequestMapping(value = "product")
public class ProductController {
		@Autowired
		private IService<Product, Integer> productDao;

		@RequestMapping(value = { "", "/index" })
		public String getListProduct(Model model) {
			List<Product> products = productDao.findAll();
			model.addAttribute("pros", products);
			return "product/indexProduct";
		}

		@GetMapping(value = "/insertProduct")
		public String insertProduct(Model model) {
			Product product = new Product();
			model.addAttribute("pro", product);
			return "product/insertProduct";
		}

		@RequestMapping(value = { "/edit" })
		public String getListProduct(@RequestParam("id") int id, Model model) {
			Product product = (Product) productDao.findById(id);
			model.addAttribute("pro", product);
			return "product/edit";
		}

		@PostMapping(value = "/insertProduct")
		public String insertProduct(@Valid @ModelAttribute("pro") Product product, BindingResult bindingResult, Model model)
				throws IOException {
			String view = "";
			if (bindingResult.hasErrors()) {
				model.addAttribute("pro", product);
				view = "product/insertProduct";
			} else {
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
		public String updateProduct(@Valid @ModelAttribute("prod") Product product, BindingResult bindingResult, Model model)
				throws IOException {
			String view = "";
			if (bindingResult.hasErrors()) {
				model.addAttribute("pro", product);
				view = "product/edit?id=" + product.getId();
			} else {
				Product products = (Product) productDao.save(product);
				if (!Objects.isNull(products)) {
					view = "redirect:/product";
				} else {
					model.addAttribute("product", product);
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
