package in.sp.main.api;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

import in.sp.main.entities.Orders;
import in.sp.main.servicesimp.OrderServiceImpl;

@RestController
@RequestMapping("/api")
public class OrdersApi
{
	@Autowired
	private OrderServiceImpl orderService;
	

	
	
	@PostMapping("/storeOrderDetails")
	public ResponseEntity<String> storeUserOrdersDetails(@RequestBody Orders orders) throws RazorpayException
	{
		RazorpayClient razorpayClient = new RazorpayClient("rzp_test_S2hM1x3ln6KH6a", "sj2L47a5GBg0ZuCZBcgDJK04");

		JSONObject orderRequest = new JSONObject();
		orderRequest.put("amount", orders.getCourseAmount());
		orderRequest.put("currency","INR");
		orderRequest.put("receipt", "rcpt_id_"+System.currentTimeMillis());

		Order order = razorpayClient.orders.create(orderRequest);
//		System.out.println(order);
		
		String orderId = order.get("id");
		orders.setOrderId(orderId);
		
		orderService.storeUserOrders(orders);
		return ResponseEntity.ok("Order details stored successfully");
	}
}