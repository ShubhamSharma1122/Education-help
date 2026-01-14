package in.sp.main.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity
@Table(name = "userTable")
public class User
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	@Pattern(regexp = "^[a-z,A-Z ]{5,25}$", message = "Invalid name format")
	private String name;
	
	@Column
	@Pattern(regexp = "^[a-zA-Z0-9._%±]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,}$", message = "Invalid email format")
	private String email;
	
	@Column
	@Pattern(regexp = "^[a-z,A-Z,0-9]{5,25}$", message = "Invalid password format")
	private String password;
	
	@Column
	@Pattern(regexp = "^[0-9]{10}$", message = "Invalid phone number format")
	private String phoneno;
	
	@Column
	@Pattern(regexp = "^[a-z,A-Z]{3,25}$", message = "Invalid city format")
	private String city;
	
	@Column
	private boolean banStatus;
	
}
