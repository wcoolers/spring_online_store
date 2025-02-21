/**
 * This is the Database Access Interface, with methods to be implemented in the Database subclass.
 * 
 * @author Adegoke Akanbi. Student ID: 991719830
 * @version 1.0
 */

package ca.sheridancollege.akanbiad.database;

import java.util.List;


import ca.sheridancollege.akanbiad.beans.Address;
import ca.sheridancollege.akanbiad.beans.Customer;
import ca.sheridancollege.akanbiad.beans.Product;
import ca.sheridancollege.akanbiad.beans.User;

public interface DatabaseAccess {
	public void addUser(String email, String password);
	public void addRole(Long userId, Long roleId);
	public User findUserAccount(String email);
	public List<String> getRolesById(Long userId);
	public List<Product> getProductList();
	public void insertCustomer(String firstname, String lastname, Long userId);
	public Long insertAddress(String streetName, String streetNumber, String city, String province, String postalCode);
	public void insertCustomerAddress(Long customerId, Long userId, Long addressId);
	public Customer findCustomer(Long userId);
	public void updateCustomer(String firstname, String lastname, Long userId);
	public void deleteCustomer(Long userId);
	public Address getCustomerAddress(Long userId);
	public void deleteCustomerAddress(Long userId);
	public void updateAddress(String streetname, String streetnumber, String city, String province, String postalcode, Long userId);
	public void insertProduct(Product product);
	public Product getProductById(Long productId);
	public void updateProduct(Product product);
	public void deleteProductById(Long productId);
}
