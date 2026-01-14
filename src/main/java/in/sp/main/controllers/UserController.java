package in.sp.main.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import in.sp.main.dto.PurchasedCourse;
import in.sp.main.entities.Course;
import in.sp.main.entities.User;
import in.sp.main.repositories.OrdersRepository;
import in.sp.main.repositories.UserRepository;
import in.sp.main.servicesimp.CourseServiceImpl;
import in.sp.main.servicesimp.UserServiceImpl;
import jakarta.validation.Valid;

@Controller
@SessionAttributes("sessionUser")
public class UserController
{
	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CourseServiceImpl courseService;
	
	@Autowired
	private OrdersRepository ordersRepository;
	
	@GetMapping({"/", "/index"})
	public String openIndexPage(Model model, @SessionAttribute(name="sessionUser", required=false) User sessionUser)
	{
		List<Course> coursesList = courseService.getAllCourseDetails();
		model.addAttribute("coursesList", coursesList);
		
		if(sessionUser != null)
		{
			List<Object[]> purchasedCourseList = ordersRepository.findPurchasedCoursesByEmail(sessionUser.getEmail());
			
			List<String> purchasedCoursesNameList = new ArrayList<>();
			for(Object[] course : purchasedCourseList)
			{
				String courseName = (String) course[3];
				purchasedCoursesNameList.add(courseName);
			}
			
			model.addAttribute("purchasedCoursesNameList", purchasedCoursesNameList);
		}
		
		
		return "index";
	}
	
	//-----------register starts---------------------------------
	@GetMapping("/register")
	public String openRegisterPage(Model model)
	{
		model.addAttribute("user", new User());
		return "register";
	}
	
	@PostMapping("/regForm")
	public String handleRegForm(@Valid @ModelAttribute("user") User user, BindingResult result, Model model)
	{
		if(result.hasErrors())
		{
			return "register";
		}
		else
		{
			try
			{
				userService.registerUserService(user);
				
				model.addAttribute("successMsg", "Registered Successfully");
				return "register";
			}
			catch(Exception e)
			{
				e.printStackTrace();
				
				model.addAttribute("errorMsg", "Registration Failed");
				return "error";
			}
		}
	}
	//-----------register finished---------------------------------
	
	
	//-----------------------login starts---------------------------------
	@GetMapping("/login")
	public String openLoginPage(Model model)
	{
		model.addAttribute("user", new User());
		return "login";
	}
	@PostMapping("/loginForm")
	public String handleLoginForm(@ModelAttribute("user") User user, Model model)
	{
		boolean isAuthenticated = userService.loginUserService(user.getEmail(), user.getPassword());
		if(isAuthenticated)
		{
			User authenticatedUser = userRepository.findByEmail(user.getEmail());
			
			if(authenticatedUser.isBanStatus())
			{
				model.addAttribute("errorMsg", "Sorry, your account is banned, please contact admin, thank you...!!");
				return "login";
			}
			model.addAttribute("sessionUser", authenticatedUser);
			
			return "user-profile";
		}
		else
		{
			model.addAttribute("errorMsg", "Incorrect Email id or Password");
			return "login";
		}
	}
	//-----------------------login finished---------------------------------
	
	
	@GetMapping("/logout")
	public String logout(SessionStatus sessionStatus)
	{
		sessionStatus.setComplete();
		return "login";
	}
	
	@GetMapping("/userProfile")
	public String openUserProfile()
	{
		return "user-profile";
	}
	
	@GetMapping("/myCourses")
	public String myCoursesPage(@SessionAttribute("sessionUser") User sessionUser, Model model) {
	    List<Object[]> pcDbList = ordersRepository.findPurchasedCoursesByEmail(sessionUser.getEmail());

	    List<PurchasedCourse> purchasedCoursesList = new ArrayList<>();
	    for (Object[] course : pcDbList) {
	        PurchasedCourse purchasedCourse = new PurchasedCourse();

	        // Assuming course[0] and course[4] are Timestamp objects or Strings
	        Object purchasedOnObj = course[0];
	        Object updatedOnObj = course[4];

	        LocalDateTime purchasedOn = null;
	        LocalDateTime updatedOn = null;

	        // Use DateTimeFormatter to match the expected date format
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");  // Match the incoming string format

	        // If the value is a String, convert it into LocalDateTime
	        if (purchasedOnObj instanceof String) {
	            purchasedOn = LocalDateTime.parse((String) purchasedOnObj, formatter);  // Parsing '06-01-2026'
	        } else if (purchasedOnObj instanceof java.sql.Timestamp) {
	            purchasedOn = ((java.sql.Timestamp) purchasedOnObj).toLocalDateTime();
	        }

	        if (updatedOnObj instanceof String) {
	            updatedOn = LocalDateTime.parse((String) updatedOnObj, formatter);
	        } else if (updatedOnObj instanceof java.sql.Timestamp) {
	            updatedOn = ((java.sql.Timestamp) updatedOnObj).toLocalDateTime();
	        }

	        // Define a new formatter for displaying in the required format
	        DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy, hh:mm:ss a");

	        String formattedPurchasedOn = purchasedOn != null ? purchasedOn.format(displayFormatter) : null;
	        String formattedUpdatedOn = updatedOn != null ? updatedOn.format(displayFormatter) : null;

	        // Set the formatted strings into the PurchasedCourse object
	        purchasedCourse.setPurchasedOn(formattedPurchasedOn);
	        purchasedCourse.setDescription((String) course[1]);
	        purchasedCourse.setImageUrl((String) course[2]);
	        purchasedCourse.setCourseName((String) course[3]);
	        purchasedCourse.setUpdatedOn(formattedUpdatedOn);

	        // Add to the list
	        purchasedCoursesList.add(purchasedCourse);
	    }

	    model.addAttribute("purchasedCoursesList", purchasedCoursesList);

	    return "my-courses";
	}


}