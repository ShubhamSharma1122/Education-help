package in.sp.main.servicesimp;

import org.springframework.stereotype.Service;

import in.sp.main.entities.Orders;
import in.sp.main.repositories.OrdersRepository;
import in.sp.main.service.OrderService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService
{
	
	private final OrdersRepository ordersRepository;

	@Override
	public void storeUserOrders(Orders orders)
	{
		ordersRepository.save(orders);
	}
}