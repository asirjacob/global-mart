package com.cat.product.dao;

import java.util.List;

import com.cat.product.model.ProductBO;

public interface ProductServiceDAO {

	public int insertProduct(ProductBO productBO);

	public List<ProductBO> getProductInfo(String productType);
	
	public Integer getProductId(final String productName, final String productType);
	
	public int deleteProduct(Integer productId);
	
}
