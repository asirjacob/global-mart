package com.cat.product.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.cat.product.dao.ProductServiceDAO;
import com.cat.product.model.ProductBO;

@Repository
public class ProductServiceDAOImpl implements ProductServiceDAO {

	private Logger logger = Logger.getLogger(ProductServiceDAOImpl.class);
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	private static final String INSERT_PRODUCT = "INSERT INTO PRODUCTS (PRODUCT_NAME, PRODUCT_TYPE, PRODUCT_DES) VALUES (?,?,?)"; 
	private static final String GET_PRODUCT_BY_TYPE = "SELECT PRODUCT_NAME, PRODUCT_TYPE, PRODUCT_DES FROM PRODUCTS WHERE PRODUCT_TYPE = ?";
	private static final String DELETE_PRODUCT = "DELETE FROM PRODUCTS WHERE PRODUCT_ID = ?";
	private static final String GET_PRODUCT_BY_NAME_TYPE = "SELECT PRODUCT_ID FROM PRODUCTS WHERE PRODUCT_NAME = ? AND PRODUCT_TYPE = ?";
	
	@Override
	public int insertProduct(final ProductBO productBO){
		int insert = jdbcTemplate.update(INSERT_PRODUCT, new Object[]{67, productBO.getProductName()});
		return insert;
	}

	@Override
	public List<ProductBO> getProductInfo(final String productType) {
		List<ProductBO> products = null;
		try{
			products = jdbcTemplate.query(GET_PRODUCT_BY_TYPE, new Object[]{productType}, new RowMapper<ProductBO>(){
			@Override
			public ProductBO mapRow(ResultSet rs, int arg1) throws SQLException {
				ProductBO productBO = new ProductBO();
				productBO.setProductName(rs.getString("MARKER"));
				return productBO;
			}});
		}catch(Exception e){
			logger.error("Error while retrieving Product", e);
		}
		return products;
	}
	
	@Override
	public Integer getProductId(final String productName, final String productType) {
		Integer productId = null;
		try{
			productId = jdbcTemplate.queryForObject(GET_PRODUCT_BY_NAME_TYPE, new Object[]{productName, productType}, Integer.class);
		}catch(Exception e){
			logger.error("Error while retrieving Product Id", e);
		}
		return productId;
	}
	
	@Override
	public int deleteProduct(final Integer productId){
		int delete = jdbcTemplate.update(DELETE_PRODUCT, new Object[]{productId});
		return delete;
	}
}
