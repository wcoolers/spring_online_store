/**
 * This is the Address class
 * 
 * @author Adegoke Akanbi. Student ID: 991719830
 * @version 1.0
 */

package ca.sheridancollege.akanbiad.beans;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Address {
	@NonNull
	private Long addressId;
	@NonNull
	private String streetName;
	@NonNull
	private String streetNumber;
	@NonNull
	private String city;
	@NonNull
	private String province;
	@NonNull
	private String postalCode;	
}