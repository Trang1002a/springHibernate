package demo.controller;

import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.security.Timestamp;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import demo.Service.CategoryDaoImpl;
import demo.Service.IService;
import demo.entity.Category;
import demo.entity.CategoryView;
import demo.entity.Product;

@Controller
@RequestMapping(value = "/category")
public class CategoryController {
	
	@Autowired
	private IService<Category, Integer> categoryDao;

	@RequestMapping(value = { "", "/index" })
	public String getListCategory(Model model) {
		List<Category> categories = categoryDao.findAll();
		model.addAttribute("cats", categories);
		return "category/indexCategory";
	}

	@GetMapping(value = "/insertCategory")
	public String insertCategory(Model model) {
		Category cats = new Category();
		model.addAttribute("cat", cats);
		return "category/insertCategory";
	}

	@RequestMapping(value = { "/edit" })
	public String getListCategory(@RequestParam("id") int id, Model model) {
		Category category = (Category) categoryDao.findById(id);
		model.addAttribute("cat", category);
		return "category/edit";
	}

	@PostMapping(value = "/insertCategory")
	public String insertCategory(@Valid @ModelAttribute("cat") Category cats, BindingResult bindingResult, Model model)
			throws IOException {
		String view = "";
		if (bindingResult.hasErrors()) {
			model.addAttribute("cat", cats);
			view = "category/insertCategory";
		} else {
			Category category = (Category) categoryDao.save(cats);
			if (!Objects.isNull(category)) {
				view = "redirect:/category";
			} else {
				model.addAttribute("cat", cats);
				view = "category/insertCategory";
			}
		}
		return view;
	}

	@PostMapping(value = "/updateCategory")
	public String updateCategory(@Valid @ModelAttribute("cat") Category cats, BindingResult bindingResult, Model model)
			throws IOException {
		String view = "";
		if (bindingResult.hasErrors()) {
			model.addAttribute("cat", cats);
			view = "category/edit?id=" + cats.getId();
		} else {
			Category category = (Category) categoryDao.save(cats);
			if (!Objects.isNull(category)) {
				view = "redirect:/category";
			} else {
				model.addAttribute("cat", cats);
				view = "category/edit?id=" + cats.getId();
			}
		}
		return view;
	}

	@RequestMapping(value = { "/delete" })
	public String deleteCategory(@RequestParam("id") int id, Model model) {
		boolean check = categoryDao.deleteById(id);
		return "redirect:/category";
	}
//	@PostMapping(value = "/insertCategory")
//	public String insertCategory(@Valid @ModelAttribute("cat") Category cats, BindingResult bindingResult,
//			HttpServletRequest req, Model model) throws IOException {
//		if (bindingResult.hasErrors()) {
//			model.addAttribute("cat", cats);
//			return "insertCategory";
//		} else {
//			// Lấy đường dẫn lưu ảnh
//			String path = req.getServletContext().getRealPath("resources/images");
//			File folder = new File(path);
//			if (!folder.exists()) {
//				folder.mkdir();
//			}
//			MultipartFile fileUp = cats.getImage();
//			File file = new File(folder.getAbsolutePath() + "/" + fileUp.getOriginalFilename());
//
//			if (!file.exists()) {
//				byte[] bytes = fileUp.getBytes();
//				Files.write(file.toPath(), bytes, StandardOpenOption.CREATE);
//			}
//			CategoryView cat = new CategoryView();
//			cat.setId(cats.getId());
//			cat.setName(cats.getName());
//			cat.setImage(fileUp.getOriginalFilename());
//			model.addAttribute("cats", cat);
//				return "index";
//		}
//	}
}
