package in.sp.main.service;

import java.util.List;

public interface EmpSalesInfoService {

	String findTotalSalesByAllEmployees();

	List<Object[]> findTotalSalesByEachEmployee();

}
