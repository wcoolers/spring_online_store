/**
 * This is the Customer_Address class
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
public class CustomerAddress {
	@NonNull
	private Long customerId;
	@NonNull
	private Long userId;
	@NonNull
	private Long addressId;
}