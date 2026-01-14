package in.sp.main.servicesimp;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import in.sp.main.entities.Employee;
import in.sp.main.repositories.EmployeeRepository;
import in.sp.main.service.EmployeeService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService
{
	
	private final EmployeeRepository employeeRepository;

	@Override
	public boolean loginEmpService(String email, String password)
	{
		Employee employee = employeeRepository.findByEmail(email);
		if(employee != null)
		{
			return password.equals(employee.getPassword());
		}
		return false;
	}

	@Override
	public void addEmployee(Employee employee)
	{
		employeeRepository.save(employee);
	}

	@Override
	public Employee getEmployeeDetails(String employeeEmail)
	{
		return employeeRepository.findByEmail(employeeEmail);
	}

	@Override
	public Page<Employee> getAllEmployeeDetailsByPagination(Pageable pageable)
	{
		return employeeRepository.findAll(pageable);
	}

	@Override
	public void updateEmployeeDetails(Employee employee)
	{
		employeeRepository.save(employee);
	}

	@Override
	public void deleteEmployeeDetails(String employeeEmail)
	{
		Employee employee = employeeRepository.findByEmail(employeeEmail);
		if(employee != null)
		{
			employeeRepository.delete(employee);
		}
		else
		{
			throw new RuntimeException("Employee not found with email : "+employeeEmail);
		}
	}
}