package in.sp.main.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import in.sp.main.entities.Feedback;

public interface FeedbackService {

	void sendFeedback(Feedback feedback);

	Page<Feedback> getAllFeedbacksByPagination(Pageable pageable);

	boolean updateFeedbackStatus(Long id, String status);

}
