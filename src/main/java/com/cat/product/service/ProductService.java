package com.cat.product.service;

import java.util.List;

import com.cat.product.model.ProductBO;

public interface ProductService {

	public int insertProduct(ProductBO productBO);

	public List<ProductBO> getProductInfo(String productType);
	
	public int deleteProduct(String productName, String productType);

}
