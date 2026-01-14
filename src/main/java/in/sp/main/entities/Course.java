package in.sp.main.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Course
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String name;
	
	@Column
	private String description;
	
	@Column
	private String originalPrice;
	
	@Column
	private String discountedPrice;
	
//	@JsonFormat(pattern = "dd/MM/yyyy, hh:mm:ss a")
    private LocalDateTime updatedOn;
	
	@Column
	private String imageUrl;
	

}
