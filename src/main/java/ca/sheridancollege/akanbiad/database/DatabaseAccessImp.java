/**
 * This is the Database Implementation Class, it implements the DatabaseAccess Interface
 * 
 * @author Adegoke Akanbi. Student ID: 991719830
 * @version 1.0
 */

package ca.sheridancollege.akanbiad.database;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.akanbiad.beans.Address;

import ca.sheridancollege.akanbiad.beans.Customer;
import ca.sheridancollege.akanbiad.beans.Product;
import ca.sheridancollege.akanbiad.beans.User;

@Repository
public class DatabaseAccessImp implements DatabaseAccess{
	@Autowired
	protected NamedParameterJdbcTemplate jdbc;
	
	
	@Autowired
	@Lazy
	private PasswordEncoder passwordEncoder;
	
	public void addUser(String email, String password) {
		MapSqlParameterSource namedParameters = new	MapSqlParameterSource();
		String query = "INSERT INTO `user` "
				+ "(email, encrypted_password, enabled) "
				+ "VALUES (:email, :encrypted_password, 1)";
		namedParameters.addValue("email", email);
		namedParameters.addValue("encrypted_password", passwordEncoder.encode(password));
		
		jdbc.update(query, namedParameters);
	}
	
	public void addRole(Long userId, Long roleId) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "INSERT INTO `user_role` (user_id, role_id) "
				+ "VALUES (:userId, :roleId)";
		namedParameters.addValue("userId", userId);
		namedParameters.addValue("roleId", roleId);
		
		jdbc.update(query, namedParameters);
	}


	public User findUserAccount(String email) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT * FROM `user` WHERE email = :email";
		namedParameters.addValue("email", email);
		
		try {
			return jdbc.queryForObject(query, namedParameters, new BeanPropertyRowMapper<User>(User.class));
			
		}
		catch (EmptyResultDataAccessException erdae) {
			return null;
		}
	}
	
	public List<String> getRolesById(Long userId){
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		
		String query = "SELECT `role`.role_name " 
				+ "FROM `user_role`, `role` " 
				+ "WHERE `user_role`.role_id = `role`.role_id "
				+ "AND user_id = :userId";
		
		namedParameters.addValue("userId", userId);
		
		return jdbc.queryForList(query, namedParameters, String.class);
	}
	
	//GET PRODUCT LIST
	public List<Product> getProductList() {
		MapSqlParameterSource namedParameters= new MapSqlParameterSource();
		String query = "SELECT * FROM `product`";
		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Product>(Product.class));
	}
	
	//FIND CUSTOMER
	public Customer findCustomer(Long userId) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT * FROM `customer` WHERE user_id = :userId";
		namedParameters.addValue("userId", userId);
		
		try {
			return jdbc.queryForObject(query, namedParameters, new BeanPropertyRowMapper<Customer>(Customer.class));
			
		}
		catch (EmptyResultDataAccessException erdae) {
			return null;
		}
	}
	
	//UPDATE CUSTOMER
	public void updateCustomer(String firstname, String lastname, Long userId) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
				
		String query = "UPDATE `customer` SET first_name = :firstname, last_name = :lastname WHERE user_id = :userId"; 
		
		
		namedParameters.addValue("firstname", firstname);
		namedParameters.addValue("lastname", lastname); 
		namedParameters.addValue("userId", userId); 
		
	    jdbc.update(query, namedParameters);
	}
	
	//INSERT CUSTOMER
	public void insertCustomer(String firstname, String lastname, Long userId) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		
		String query = "INSERT INTO `customer` (first_name, last_name, user_id) VALUES (:firstName, :lastName, :userId)";
		
		namedParameters.addValue("firstName", firstname);
		namedParameters.addValue("lastName", lastname); 
		namedParameters.addValue("userId", userId); 
		
		 jdbc.update(query, namedParameters);
		 
	}
	
	//DELETE CUSTOMER
	public void deleteCustomer(Long userId) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		
		String query = "DELETE FROM `customer` WHERE user_id = :userId";
		namedParameters.addValue("userId", userId);
		jdbc.update(query, namedParameters);

	}
	
	
	//INSERT ADDRESS
	public Long insertAddress(String streetName, String streetNumber, String city, String province, String postalCode) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
		
		String query = "INSERT INTO `address` (street_name, street_number, city, province, postal_code) VALUES (:streetName, :streetNumber, :city, :province, :postalCode)"; 
		
		
		namedParameters.addValue("streetName", streetName);
		namedParameters.addValue("streetNumber", streetNumber); 
		namedParameters.addValue("city", city); 
		namedParameters.addValue("province", province); 
		namedParameters.addValue("postalCode", postalCode); 
		
	    jdbc.update(query, namedParameters, generatedKeyHolder);
	    return (Long) generatedKeyHolder.getKey();
	}
	
	//INSERT CUSTOMER ADDRESS
	public void insertCustomerAddress(Long customerId, Long userId, Long addressId) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();

		String query = "INSERT INTO `customer_address`(customer_id, user_id, address_id) VALUES(:customerId, :userId, :addressId)"; 
		
		
		namedParameters.addValue("customerId", customerId);
		namedParameters.addValue("userId", userId); 
		namedParameters.addValue("addressId", addressId); 
				
	    jdbc.update(query, namedParameters);
	}
	

	
	//GET CUSTOMER ADDRESS
	public Address getCustomerAddress(Long userId) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT ca.address_id, street_name, street_number, city, province, postal_code FROM `customer_address` ca join ADDRESS a on ca.address_id = a.address_id where user_id = :userId";
		namedParameters.addValue("userId", userId);
		
		try {
			return jdbc.queryForObject(query, namedParameters, new BeanPropertyRowMapper<Address>(Address.class));
			
		}
		catch (EmptyResultDataAccessException erdae) {
			return null;
		}
	}
	
	
	//UPDATE ADDRESS
	public void updateAddress(String streetname, String streetnumber, String city, String province, String postalcode, Long userId) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
				
		String query = "UPDATE `address` SET street_name = :streetname, street_number = :streetnumber, city = :city, province = :province, postal_code = :postalcode WHERE address_id = (SELECT address_id FROM `customer_address` WHERE user_id = :userId)"; 
		
		
		namedParameters.addValue("streetname", streetname);
		namedParameters.addValue("streetnumber", streetnumber);
		namedParameters.addValue("city", city);
		namedParameters.addValue("province", province);
		namedParameters.addValue("postalcode", postalcode); 
		namedParameters.addValue("userId", userId); 
		
	    jdbc.update(query, namedParameters);
	}

	
	//DELETE CUSTOMER ADDRESS
	public void deleteCustomerAddress(Long userId) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		
		String query = "DELETE FROM `address` WHERE address_id = (SELECT address_id FROM `customer_address`	WHERE user_id = :userId)";
		namedParameters.addValue("userId", userId);
		jdbc.update(query, namedParameters);

	}
	
	//PRODUCTS TRANSACTIONS
//........................................................
	public void insertProduct(Product product) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		
		
		if (product.getProductId() == -1) {
			
			String query = "INSERT INTO  `product`(product_name, product_price) VALUES (:name, :price)";
			
			namedParameters.addValue("name", product.getProductName()); 
			namedParameters.addValue("price", product.getProductPrice()); 
			
			
			jdbc.update(query, namedParameters);
			
		} else {
			
			String query = "UPDATE `product` SET product_name = :productName, product_price = :productPrice WHERE product_id = :productId"; 
						
			namedParameters.addValue("productName", product.getProductName());
			namedParameters.addValue("productPrice", product.getProductPrice()); 
			namedParameters.addValue("productId", product.getProductId()); 
			
		    jdbc.update(query, namedParameters);
		    
			
		}
		

	}
	
	//GET PRODUCT
	public Product getProductById(Long productId){
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		
		String query = "SELECT * FROM `product` WHERE product_id = :productId";
		
		namedParameters.addValue("productId", productId);
		
		try {
			return jdbc.queryForObject(query, namedParameters, new BeanPropertyRowMapper<Product>(Product.class));
			
		}
		catch (EmptyResultDataAccessException erdae) {
			return null;
		}

	}
	
	//UPDATE PRODUCT
	public void updateProduct(Product product) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		System.out.println("UPDATE PRODUCT" + product);
				
		String query = "UPDATE `product` SET product_name = :productName, product_price = :productPrice WHERE product_id = :productId"; 
		
		
		namedParameters.addValue("productName", product.getProductName());
		namedParameters.addValue("productPrice", product.getProductPrice()); 
		namedParameters.addValue("productId", product.getProductId()); 
		
	    jdbc.update(query, namedParameters);
	}

//	DELETE PRODUCT
	public void deleteProductById(Long productId) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		
		String query = "DELETE FROM `product` WHERE product_id = :productId";
		namedParameters.addValue("productId", productId);
		jdbc.update(query, namedParameters);

	}
	


}
