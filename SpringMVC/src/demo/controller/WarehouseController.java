package demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import demo.Service.IService;
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
	public String importProduct() {
		return "warehouse/importProduct";
	}
}
