package in.sp.main.servicesimp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import in.sp.main.entities.Course;
import in.sp.main.repositories.CourseRepository;
import in.sp.main.service.CourseService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CourseServiceImpl implements CourseService
{
	private String UPLOAD_DIR = "src/main/resources/static/uploads/";
	private String IMAGE_URL = "http://localhost:8080/uploads/";
	
	private final CourseRepository courseRepository;
	
	@Override
	public List<Course> getAllCourseDetails()
	{
		return courseRepository.findAll();
	}
	
	//Pageable is used to specify pagination information i.e. page number, page size, sorting order etc when querying with database
	//Page represents the chunk of data that is fetched according to pagination parameters defined by Pagable.
	@Override
	public Page<Course> getAllCourseDetailsByPagination(Pageable pageable)
	{
		return courseRepository.findAll(pageable);
	}

	@Override
	public void addCourse(Course course, MultipartFile courseImg) throws IOException
	{
		String imgName = courseImg.getOriginalFilename();
		Path imgPath = Paths.get(UPLOAD_DIR+imgName);
		Files.write(imgPath, courseImg.getBytes());
		
		String imgUrl = IMAGE_URL+imgName;
		course.setImageUrl(imgUrl);
		
		courseRepository.save(course);
	}

	@Override
	public Course getCourseDetails(String courseName)
	{
		return courseRepository.findByName(courseName);
	}

	@Override
	public void updateCourseDetails(Course course)
	{
		courseRepository.save(course);
	}

	@Override
	public void deleteCourseDetails(String courseName)
	{
		Course course = courseRepository.findByName(courseName);
		if(course != null)
		{
			courseRepository.delete(course);
		}
		else
		{
			throw new RuntimeException("Course not found with name : "+courseName);
		}
	}
	
//	public List<String> getAllCourseNames()
//	{
//		List<Course> coursesList = courseRepository.findAll();
//		
//		List<String> courseNameList = new ArrayList<>();
//		
//		for(Course course : coursesList)
//		{
//			String courseName = course.getName();
//			courseNameList.add(courseName);
//		}
//		
//		return courseNameList;
//	}
	@Override
	public List<String> getAllCourseNames()
	{
		return courseRepository.findAll().stream()
				.map(Course::getName)
				.collect(Collectors.toList());
	}
}
