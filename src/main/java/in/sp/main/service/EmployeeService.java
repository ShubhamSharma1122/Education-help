package in.sp.main.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import in.sp.main.entities.Employee;

public interface EmployeeService {

	boolean loginEmpService(String email, String password);

	void addEmployee(Employee employee);

	Employee getEmployeeDetails(String employeeEmail);

	Page<Employee> getAllEmployeeDetailsByPagination(Pageable pageable);

	void updateEmployeeDetails(Employee employee);

	void deleteEmployeeDetails(String employeeEmail);

}
