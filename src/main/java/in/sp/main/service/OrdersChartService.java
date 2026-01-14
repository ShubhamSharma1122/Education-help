package in.sp.main.service;

import java.util.List;

public interface OrdersChartService {

	List<Object[]> findCoursesAmountTotalSales();

	List<Object[]> findCoursesSoldPerDay();

	List<Object[]> findCoursesTotalSales();

}
