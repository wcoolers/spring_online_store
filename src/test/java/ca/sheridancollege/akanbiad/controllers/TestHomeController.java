/**
 * This is the Test class for Home Controller. Minimal tests are here.
 * Will be updated
 * 
 * @author Adegoke Akanbi. Student ID: 991719830
 * @version 1.0
 */
package ca.sheridancollege.akanbiad.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import ca.sheridancollege.akanbiad.beans.Customer;



@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureMockMvc
public class TestHomeController {
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testLoadingIndex() throws Exception {
		this.mockMvc.perform(get("/"))
		.andExpect(status().isOk())
		.andExpect(view().name("index"));
	}
	
	@Test
	public void testLoadingRegister() throws Exception {
		this.mockMvc.perform(get("/register"))
		.andExpect(status().isOk())
		.andExpect(view().name("register"));
	}
	
	@Test
	public void testLoadingLogin() throws Exception {
		this.mockMvc.perform(get("/login"))
		.andExpect(status().isOk())
		.andExpect(view().name("login"));
	}
	
	@Test
	public void testLoadingPermissionDenied() throws Exception {
		this.mockMvc.perform(get("/permission-denied"))
		.andExpect(status().isOk())
		.andExpect(view().name("/error/permission-denied"));
	}
	
	@Test
    @WithMockUser(roles = {"USER"})
    void testGetSecureIndexWithLoggedInUser() throws Exception {
        
        mockMvc.perform(MockMvcRequestBuilders.get("/secure"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("/secure/index"));
               
    }
	

	@Test
    @WithMockUser(roles = {"ADMIN"})
    void testGetSecureAdminIndexWithLoggedInUser() throws Exception {
        
        mockMvc.perform(MockMvcRequestBuilders.get("/admin"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("/admin/index"));
               
    }
	
//	@Test
//    @WithMockUser(roles = {"GUEST"})
//    void testGetSecureAdminIndexWithGuestRoles() throws Exception {
//        
//        mockMvc.perform(MockMvcRequestBuilders.get("/admin"))
//                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
//                .andExpect(MockMvcResultMatchers.view().name("/permission-denied"));
//               
//    }
//	
//	@Test
//    @WithAnonymousUser
//    void testGetSecureCustomer_UnauthorizedAccess() throws Exception {
//        // Perform and Assert
//        mockMvc.perform(MockMvcRequestBuilders.get("/secure"))
//	        .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
//	        .andExpect(MockMvcResultMatchers.view().name("/login"));
//    }
	
//	 @Test
//	    void testPostRegister_RedirectsToLogin() throws Exception {
//	        // Arrange
//	        String username = "testUser";
//	        String password = "testPassword";
//
//	        // Act & Assert
//	        mockMvc.perform(MockMvcRequestBuilders.post("/register")
//	                .param("username", username)
//	                .param("password", password))
//	                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
//	                .andExpect(MockMvcResultMatchers.redirectedUrl("/login"));
//	    }
	
//	@Test
//	public void testLoadingPostCustomer() throws Exception {
//		this.mockMvc.perform(post("/secure/customer").flashAttr("customer", new Customer()))
//		.andExpect(status().isOk())
//		.andExpect(view().name("/secure/customer"));
//
//	}
//	
//	@Test
//	public void testLoadingEditStudentById() throws Exception {		
//		this.mockMvc.perform(get("/editStudentById/{id}", Long.valueOf(1)))
//		.andExpect(status().isOk())
//		.andExpect(view().name("index"));
//
//	}
//	
//	@Test
//	public void testLoadingDeleteStudentById() throws Exception {
//		this.mockMvc.perform(get("/deleteStudentById/{id}", Long.valueOf(1)))
//		.andExpect(status().isOk())
//		.andExpect(view().name("index"));
//	}
}
