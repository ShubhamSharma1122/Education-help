package in.sp.main.servicesimp;

import org.springframework.stereotype.Service;

import in.sp.main.entities.User;
import in.sp.main.repositories.UserRepository;
import in.sp.main.service.UserService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService
{
	
	private final UserRepository userRepository;

	@Override
	public void registerUserService(User user)
	{
		userRepository.save(user);
	}

	@Override
	public boolean loginUserService(String email, String password)
	{
		User user = userRepository.findByEmail(email);
		if(user != null)
		{
			return password.equals(user.getPassword());
		}
		return false;
	}
}
