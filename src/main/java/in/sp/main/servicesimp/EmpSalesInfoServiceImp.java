package in.sp.main.servicesimp;

import java.util.List;

import org.springframework.stereotype.Service;

import in.sp.main.repositories.EmpSalesInfoRepository;
import in.sp.main.service.EmpSalesInfoService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EmpSalesInfoServiceImp implements EmpSalesInfoService
{
	
	private final EmpSalesInfoRepository empSalesInfoRepository;

	@Override
	public String findTotalSalesByAllEmployees()
	{
		return empSalesInfoRepository.findTotalSalesByAllEmployees();
	}

	@Override
	public List<Object[]> findTotalSalesByEachEmployee()
	{
		return empSalesInfoRepository.findTotalSalesByEachEmployee();
	}
}
