/**
 * This is the Test class for Database Access class. 
 * Minimal tests are here.
 * Will be updated
 * 
 * @author Adegoke Akanbi. Student ID: 991719830
 * @version 1.0
 */

package ca.sheridancollege.akanbiad.database;

import static org.junit.jupiter.api.Assertions.assertEquals;



import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;


import ca.sheridancollege.akanbiad.beans.Product;


@SpringBootTest
@AutoConfigureTestDatabase
public class DatabaseAccessTest {
	@Autowired
	private DatabaseAccess da;
	
	@Autowired
	protected NamedParameterJdbcTemplate jdbc;
	
	@Test
	public void testGetProductList() {   
		
		/*	This is based on starter data in data.sql file, i.e,
			 INSERT INTO `product`(product_name, product_price) 
			VALUES('Pencil', '2.59');
			
			INSERT INTO `product`(product_name, product_price) 
			VALUES('Drawing Board', '5.99');
			
			INSERT INTO `product`(product_name, product_price) 
			VALUES('Paper', '0.99');
			
			INSERT INTO `product`(product_name, product_price) 
			VALUES('Erazer', '1.59');

		 */
       
		assertEquals(da.getProductList().get(2).getProductName(), "Paper"); //test to confirm the 3rd record in the list 
	}
	
	@Test
	public void testGetProductListById() {   
		
		assertEquals(da.getProductById(Long.valueOf(1)).getProductName(), "Pencil");
	}

	@Test
	public void whenInsertProductGetProductList() {
		int size = da.getProductList().size();
		Product product = new Product();
		product.setProductId(Long.valueOf(-1));
		product.setProductName("Ruler");
		product.setProductPrice(34.20);
		da.insertProduct(product);
		assertEquals(da.getProductList().size(), ++size);
	}
	
	@Test
	public void whenDeletedProductById() {
		//add a demo record to the existing record
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "INSERT INTO `product` VALUES(5, 'Table', '12.99');";
		
		jdbc.update(query, namedParameters);
		

		// call the deleteStudentById method with the id of the demo record
		da.deleteProductById(Long.valueOf(5));
		
		//loop through the list to see if the demo record has indeed been removed
		boolean found =  true;
		for (Product p: da.getProductList()) {
			if (p.getProductName() != "Table" ) {
				found = false;
			}
		}
		assertEquals(found, false);	
	}
}
