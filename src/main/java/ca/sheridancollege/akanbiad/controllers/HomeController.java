/**
 * This is the Home Controller. Helps to manage routing
 * 
 * @author Adegoke Akanbi. Student ID: 991719830
 * @version 1.0
 */

package ca.sheridancollege.akanbiad.controllers;


import java.util.ArrayList;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ca.sheridancollege.akanbiad.beans.Address;
import ca.sheridancollege.akanbiad.beans.Customer;
import ca.sheridancollege.akanbiad.beans.Product;
import ca.sheridancollege.akanbiad.database.DatabaseAccess;

@Controller
public class HomeController {
	
	
	@Autowired
	@Lazy
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	@Lazy
	private DatabaseAccess da;
	
	@Autowired
	private UserDetailsService userService;
	
	
	//UNPROTECTED ROUTES
	@GetMapping("/register")
	public String getRegister() {
		return "register";
	}
	
	@PostMapping("/register")
	public String postRegister(@RequestParam String username, @RequestParam String password) {
		da.addUser(username, password);
		Long userId = da.findUserAccount(username).getUserId();
		da.addRole(userId, Long.valueOf(1));
		
		return "redirect:/login";
	}
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	

	@GetMapping("/login")
	public String login() {
		return "login";
	}
	

	@GetMapping("/permission-denied")
	public String permissionDenied() {
		return "/error/permission-denied";
	}
	

	//SECURE ROUTES FOR /SECURE PATH
	@GetMapping("/secure")
	public String secureIndex(Model model, Authentication authentication) {
		
		
		List<String> roleList = new ArrayList<String>();
		for (GrantedAuthority ga: authentication.getAuthorities()) {
			roleList.add(ga.getAuthority());
		}
		model.addAttribute("roleList", roleList);
		
		return "/secure/index";
	}
	
	
	//SECURE PRODUCTS PAGE
	@GetMapping("/secure/products")
	public String getAllProducts(Model model, Authentication authentication) {
		List<Product> productList = da.getProductList();
		List<String> roleList = new ArrayList<String>();
		for (GrantedAuthority ga: authentication.getAuthorities()) {
			roleList.add(ga.getAuthority());
		}
		model.addAttribute("roleList", roleList);
		model.addAttribute("productList", productList);
	
		return "/secure/products";
	}
	
	// VIEW CUSTOMER DETAILS
	@GetMapping("/secure/customer")
	public String getSecureCustomer(Model model, Authentication authentication) {
		Long userId = da.findUserAccount(authentication.getName()).getUserId();
		Customer customer = da.findCustomer(userId);
		
		List<String> roleList = new ArrayList<String>();
		for (GrantedAuthority ga: authentication.getAuthorities()) {
			roleList.add(ga.getAuthority());
		}
		model.addAttribute("roleList", roleList);
		model.addAttribute("customer", customer);
		return "/secure/customer";
	}
	//POST TO ADD CUSTOMER DETAILS
	@PostMapping("/secure/customer")
	public String postSecureCustomer(@RequestParam String firstname, @RequestParam String lastname, Authentication authentication) {
		
		Long userId = da.findUserAccount(authentication.getName()).getUserId();
		
		
		da.insertCustomer(firstname, lastname, Long.valueOf(userId));
		
		
		return "redirect:/secure/customer";
	}
	
	//UPDATE CUSTOMER
	@GetMapping("/secure/updateCustomer")
	public String getSecureUpdateCustomer(Model model, Authentication authentication) {
		return "/secure/updateCustomer";
	}
	
	@PostMapping("/secure/updateCustomer")
	public String postSecureUpdateCustomer(@RequestParam String firstname, @RequestParam String lastname, Authentication authentication) {
		
		Long userId = da.findUserAccount(authentication.getName()).getUserId();
		da.updateCustomer(firstname, lastname, userId);
		
		return "redirect:/secure/customer";
	}
	
	//DELETE CUSTOMER
	@GetMapping("/secure/deleteCustomer")
	public String getDeleteCustomer() {
		
		return "/secure/deleteCustomer";
	}
	
	@PostMapping("/secure/deleteCustomer")
	public String postDeleteCustomer(Authentication authentication) {
		
		Long userId = da.findUserAccount(authentication.getName()).getUserId();
		
		
		da.deleteCustomer(Long.valueOf(userId));
		

		return "redirect:/secure/customer";
	}
	
	//VIEW ADDRESS DETAILS
	@GetMapping("/secure/address")
	public String getAddress(Model model, Authentication authentication) {
		
		Long userId = da.findUserAccount(authentication.getName()).getUserId();
		
		String firstName = null;
		
		
		Customer customer = da.findCustomer(userId);
		if (customer != null) {
			firstName = customer.getFirstName();
		}
		
		Address address = da.getCustomerAddress(userId);
		System.out.println("address : " + address);
		
		List<String> roleList = new ArrayList<String>();
		for (GrantedAuthority ga: authentication.getAuthorities()) {
			roleList.add(ga.getAuthority());
		}
		model.addAttribute("roleList", roleList);
		System.out.println("first name: " + firstName);
		model.addAttribute("address", address);
		model.addAttribute("firstName", firstName);
		
		
		
		return "/secure/address";
	}
	
	@PostMapping("/secure/address")
	public String postAddress(@RequestParam String streetname, @RequestParam String streetnumber,  @RequestParam String city, @RequestParam String province,  @RequestParam String postalcode, Authentication authentication) {
		
		Long addressId = da.insertAddress(streetname, streetnumber, city, province, postalcode);
		
		Long userId = da.findUserAccount(authentication.getName()).getUserId();
		
		Long customerId = da.findCustomer(userId).getCustomerId();
	

		da.insertCustomerAddress( customerId, userId, addressId);
		
		return "redirect:/secure/address";
	}
	
	//UPDATE ADDRESS
	@GetMapping("/secure/updateAddress")
	public String getSecureUpdateAddress(Model model, Authentication authentication) {
		
		return "/secure/updateAddress";
	}
	
	@PostMapping("/secure/updateAddress")
	public String postSecureUpdateAddress(@RequestParam String streetname, @RequestParam String streetnumber,  @RequestParam String city, @RequestParam String province,  @RequestParam String postalcode, Authentication authentication) {
		
		
		Long userId = da.findUserAccount(authentication.getName()).getUserId();
		
		da.updateAddress(streetname, streetnumber, city, province, postalcode, userId);
		return "redirect:/secure/address";
	}
		
	
	//DELETE ADDRESS
	@GetMapping("/secure/deleteAddress")
	public String getDeleteAddress() {
		
		return "/secure/deleteAddress";
	}
	
	@PostMapping("/secure/deleteAddress")
	public String postDeleteAddress(Authentication authentication) {
		
		Long userId = da.findUserAccount(authentication.getName()).getUserId();
		
		
		da.deleteCustomerAddress(Long.valueOf(userId));
		

		return "redirect:/secure/address";
	}
	
	//GET CART PAGE
	@GetMapping("/secure/cart")
	public String getCart(Model model, Authentication authentication) {
		List<String> roleList = new ArrayList<String>();
		for (GrantedAuthority ga: authentication.getAuthorities()) {
			roleList.add(ga.getAuthority());
		}
		model.addAttribute("roleList", roleList);
		
		return "/secure/cart";
	}
	
	
	//ADMIN VIEW CONTROLLERS
	
	@GetMapping("/admin")
	public String admin() {
		return "/admin/index";
	}
	
	@GetMapping("/admin/customerList")
	public String getAdminCustomerList() {
		return "/admin/customerList";
	}
	
	@GetMapping("/admin/productList")
	public String getProductList(Model model) {
		Product placeHolderProduct = new Product();
		placeHolderProduct.setProductId(Long.valueOf(-1));
		
		
		model.addAttribute("product", placeHolderProduct);
		model.addAttribute("productList", da.getProductList());
				
		return "/admin/productList";
	}
	
	@PostMapping("/admin/productList")
	public String insertProduct(Model model, @ModelAttribute Product product) {
		
		da.insertProduct(product);
		
		Product placeHolderProduct = new Product();
		
		model.addAttribute("product", placeHolderProduct);
		model.addAttribute("productList", da.getProductList());
		
		return "redirect:/admin/productList";
		
	}
	@GetMapping("/admin/editProductById/{productId}")
	public String editProductById(Model model, @PathVariable Long productId) {
		Product product = da.getProductById(productId);
		model.addAttribute("product", product);
				
		model.addAttribute("productList", da.getProductList());
		
		return "/admin/productList";
	}

	@GetMapping("/admin/deleteProductById/{productId}")
	public String deleteProductById(Model model, @PathVariable Long productId) {
		
		
		da.deleteProductById(productId);
		Product placeHolderProduct = new Product();
		placeHolderProduct.setProductId(Long.valueOf(-1));
		model.addAttribute("product", placeHolderProduct);
		model.addAttribute("productList", da.getProductList());
		
		return "/admin/productList";
	}
	
}
