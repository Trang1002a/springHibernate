package demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"/warehouse"})
public class WarehouseController {
	
	@RequestMapping(value = "/importProduct")
	public String importProduct() {
		return "warehouse/importProduct";
	}
}
