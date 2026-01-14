package in.sp.main.service;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import in.sp.main.entities.Course;

public interface CourseService {

	List<Course> getAllCourseDetails();

	Page<Course> getAllCourseDetailsByPagination(Pageable pageable);

	void addCourse(Course course, MultipartFile courseImg) throws IOException;

	Course getCourseDetails(String courseName);

	void updateCourseDetails(Course course);

	void deleteCourseDetails(String courseName);

	List<String> getAllCourseNames();

}
