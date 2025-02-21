/**
 * This is the js file. Contains password verification during registration and deleteProduct method to alert admin of the dangers of deleting a product
 * 
 * @author Adegoke Akanbi. Student ID: 991719830
 * @version 1.0
 */

function verify() {
	var password1 = document.forms['form']['password'].value;
	var password2 = document.forms['form']['verifyPassword'].value;
	if (password1 == null || password1 == "" || password1 != password2) {
		document.getElementById("error").innerHTML = "Please check your passwords";
		return false;
	}
}

function deleteProduct() {
	console.log("hello")
	return confirm("Deleting a product will delete all associated records, such as existing orders and quantity. Are you sure you want to continue? ");
}