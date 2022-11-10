package demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import demo.Service.IService;
import demo.entity.Category;
import demo.entity.Product;
import demo.entity.Warehouse;

@Controller
@RequestMapping(value = { "/warehouse" })
public class WarehouseController {

	@Autowired
	private IService<Product, Integer> productDao;

	@Autowired
	private IService<Warehouse, Integer> warehouseDao;

	@RequestMapping(value = "/indexWarehouse")
	public String getWarehouse(Model model,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "name", required = false, defaultValue = "") String name) {
		int pageSize = 10;
		int firstResult = (page - 1) * pageSize;
		List<Warehouse> warehouses;
		Long totalRecords;
		if (name == "") {
			totalRecords = warehouseDao.countTotalRecords(null);
			warehouses = warehouseDao.findAll(firstResult, pageSize);
		} else {
			totalRecords = warehouseDao.countTotalRecords(name);
			warehouses = warehouseDao.findAll(firstResult, pageSize, name);
		}

		model.addAttribute("wares", warehouses);
		model.addAttribute("totalRecords", totalRecords);
		model.addAttribute("page", page);
		model.addAttribute("name", name);
		return "warehouse/index";
	}

	@RequestMapping(value = "/importWarehouse")
	public String importProduct(Model model,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "name", required = false, defaultValue = "") String name) {
		int pageSize = 10;
		int firstResult = (page - 1) * pageSize;
		List<Product> products;
		Long totalRecords;
		if (name == "") {
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
		return "warehouse/importProduct";
	}

	@RequestMapping(value = { "/createVotes" })
	public String getListVotes(@RequestParam("listImport") List<Integer> id, Model model) {
		List<Product> products = productDao.findByIdIn(id);
		model.addAttribute("pros", products);
		Warehouse warehouse = new Warehouse();
		model.addAttribute("warehouse", warehouse);
		return "warehouse/index";
	}

	@RequestMapping(value = { "/importWarehouseDB" })
	public String importWarehouseDB(@Valid @ModelAttribute("warehouse") Warehouse warehouses, Model model) {
		
		return "warehouse/index";
	}
}
