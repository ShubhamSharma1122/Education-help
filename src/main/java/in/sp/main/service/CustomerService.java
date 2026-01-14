package in.sp.main.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import in.sp.main.entities.User;

public interface CustomerService {

	Page<User> getAllUserDetailsByPagination(Pageable pageable);

	User getCustomerDetails(String userEmail);

	void updateUserBanStatus(User user);

}
