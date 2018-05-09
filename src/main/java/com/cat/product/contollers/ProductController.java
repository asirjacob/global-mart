package com.cat.product.contollers;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cat.product.model.ProductBO;
import com.cat.product.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	private Logger logger = Logger.getLogger(ProductController.class);
	
	@RequestMapping("/add")
	public String addProduct(@RequestParam String productName, @RequestParam String productType, @RequestParam String description){
		logger.info("Add Product");
		ProductBO productBO = new ProductBO();
		productBO.setProductName(productName);
		productBO.setProductType(productType);
		productBO.setDescription(description);
		int insert = productService.insertProduct(productBO);
		String insertStatus = "Insert Failed";
		if(insert > 0){
			insertStatus = "Insert Success";
		}
		return insertStatus;
	}
	
	@RequestMapping("/getProduct")
	public List<ProductBO> getProduct(@RequestParam String productType){
		logger.info("View Product");
		List<ProductBO> products = productService.getProductInfo(productType);
		if(null != products){
			return products;
		}else{
			return null;
		}
	}
	
	@RequestMapping("/remove")
	public String removeProduct(@RequestParam String productName, @RequestParam String productType){
		logger.info("Delete Product");
		int removed = productService.deleteProduct(productName, productType);
		String removeStatus = "Delete Failed";
		if(removed > 0){
			removeStatus = "Delete Success";
		}
		return removeStatus;
	}
}
