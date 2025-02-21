/**
 * This is the Order_Product class
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
public class OrderProduct {
	@NonNull
	private Long orderId;
	@NonNull
	private Long productId;
	@NonNull
	private Integer quantity;
}