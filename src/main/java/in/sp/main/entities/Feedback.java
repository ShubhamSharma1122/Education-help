package in.sp.main.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Feedback
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String userName;
	
	@Column
	private String userEmail;
	
	@Column(length = 3000)
	private String userFeedback;
	
	@Column
	private String dateOfFeedback;
	
	@Column
	private String timeOfFeedback;
	
	@Column
	private String readStatus;


}
