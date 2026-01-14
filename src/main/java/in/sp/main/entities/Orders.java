package in.sp.main.entities;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Orders
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String courseName;

    // 🔥 CHANGE HERE
    private Integer courseAmount;   // amount in rupees

    private String userEmail;

	@JsonFormat(pattern = "dd/MM/yyyy, hh:mm:ss a")
    private LocalDateTime dateOfPurchase;

    private String paymentId;
    private String orderId;
}
