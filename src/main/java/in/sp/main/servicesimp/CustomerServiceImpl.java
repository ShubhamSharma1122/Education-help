package in.sp.main.servicesimp;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import in.sp.main.entities.User;
import in.sp.main.repositories.CustomerRepository;
import in.sp.main.service.CustomerService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService
{
	
	private final CustomerRepository customerRepository;

	@Override
	public Page<User> getAllUserDetailsByPagination(Pageable pageable)
	{
		return customerRepository.findAll(pageable);
	}

	@Override
	public User getCustomerDetails(String userEmail)
	{
		return customerRepository.findByEmail(userEmail);
	}

	@Override
	public void updateUserBanStatus(User user)
	{
		customerRepository.save(user);
	}
}
