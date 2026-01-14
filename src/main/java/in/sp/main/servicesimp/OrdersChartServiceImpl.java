package in.sp.main.servicesimp;

import java.util.List;

import org.springframework.stereotype.Service;

import in.sp.main.repositories.OrdersChartRepository;
import in.sp.main.service.OrdersChartService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OrdersChartServiceImpl implements OrdersChartService
{
	private final OrdersChartRepository ordersChartRepository;

	@Override
	public List<Object[]> findCoursesAmountTotalSales()
	{
		return ordersChartRepository.findCoursesAmountTotalSales();
	}

	@Override
	public List<Object[]> findCoursesSoldPerDay()
	{
		return ordersChartRepository.findCoursesSoldPerDay();
	}

	@Override
	public List<Object[]> findCoursesTotalSales()
	{
		return ordersChartRepository.findCoursesTotalSales();
	}
}
