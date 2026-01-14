package in.sp.main.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Inquiry 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String phoneno;
	
	@Column
	private String name;
	
	@Column
	private String interestedCourse;
	
	@Column
	private String discussion;
	
	@Column
	private String inquiryType;
	
	@Column
	private String callType;
	
	@Column
	private String status;
	
	@Column
	private String empEmail;
	
	@Column
	private String dateOfInquiry;
	
	@Column
	private String timeOfInquiry;
	

}