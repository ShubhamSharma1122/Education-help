package in.sp.main.controllers;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import in.sp.main.servicesimp.OrdersChartServiceImpl;

@Controller
public class OrdersChartController 
{
	@Autowired
	private OrdersChartServiceImpl ordersChartService;
	
	@GetMapping("/adminProfile")
	public String openAdminProfilePage(Model model)
	{
		// ---------- GRAPH 3 : Courses sold per day ----------
		List<Object[]> listOfCoursesSoldPerDay = ordersChartService.findCoursesSoldPerDay();
		
		List<String> dates1 = new ArrayList<>();
		List<Long> counts1 = new ArrayList<>();
		
		for(Object[] obj : listOfCoursesSoldPerDay)
		{
			Date sqlDate = (Date) obj[0]; // ✅ FIX
			Long count = (Long) obj[1];

			dates1.add(sqlDate.toString()); // ✅ convert to String
			counts1.add(count);
		}
		
		model.addAttribute("dates1", dates1);
		model.addAttribute("counts1", counts1);
		
		
		// ---------- GRAPH 2 : Total courses sold ----------
		List<Object[]> listOfCoursesTotalSales = ordersChartService.findCoursesTotalSales();
		
		List<String> coursename1 = new ArrayList<>();
		List<Long> coursecount1 = new ArrayList<>();
		
		for(Object[] obj : listOfCoursesTotalSales)
		{
			coursename1.add((String) obj[0]);
			coursecount1.add((Long) obj[1]);
		}
		
		model.addAttribute("coursename1", coursename1);
		model.addAttribute("coursecount1", coursecount1);
		
		
		// ---------- GRAPH 1 : Total amount per day ----------
		List<Object[]> listOfCoursesAmountSales = ordersChartService.findCoursesAmountTotalSales();
		
		List<String> date11 = new ArrayList<>();
		List<Long> totalAmount11 = new ArrayList<>();
		
		for(Object[] obj : listOfCoursesAmountSales)
		{
			Date sqlDate = (Date) obj[0]; // ✅ FIX
			Long totalAmount = ((Number) obj[1]).longValue(); // ✅ safe cast

			date11.add(sqlDate.toString());
			totalAmount11.add(totalAmount);
		}
		
		model.addAttribute("date11", date11);
		model.addAttribute("totalAmount11", totalAmount11);
		
		return "admin-profile";
	}
}
