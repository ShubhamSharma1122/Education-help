package in.sp.main.service;

import in.sp.main.entities.User;

public interface UserService {

	boolean loginUserService(String email, String password);

	void registerUserService(User user);

}
