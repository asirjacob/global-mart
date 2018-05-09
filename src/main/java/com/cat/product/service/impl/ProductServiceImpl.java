package com.cat.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cat.product.dao.ProductServiceDAO;
import com.cat.product.model.ProductBO;
import com.cat.product.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired(required=false)
	ProductServiceDAO productServiceDao;
	
	@Override
	public int insertProduct(ProductBO productBO){
		return productServiceDao.insertProduct(productBO);
	}

	@Override
	public List<ProductBO> getProductInfo(String productType) {
		return productServiceDao.getProductInfo(productType);
	}

	@Override
	public int deleteProduct(String productName, String productType){
		Integer productId = productServiceDao.getProductId(productName, productType);
		return productServiceDao.deleteProduct(productId);
	}
}
