/**
 * This is the Order class
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
public class Order {
	@NonNull
	private Long orderId;
	@NonNull
	private String orderDate;
	@NonNull
	private String orderStatus;
	@NonNull
	private Long customerId;
	@NonNull
	private Double orderAmount;
}