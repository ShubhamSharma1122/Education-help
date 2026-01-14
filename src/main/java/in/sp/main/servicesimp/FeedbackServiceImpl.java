package in.sp.main.servicesimp;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import in.sp.main.entities.Feedback;
import in.sp.main.repositories.FeedbackRepository;
import in.sp.main.service.FeedbackService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService
{
	
	private final FeedbackRepository feedbackRepository;

	@Override
	public void sendFeedback(Feedback feedback)
	{
		feedbackRepository.save(feedback);
	}

	@Override
	public Page<Feedback> getAllFeedbacksByPagination(Pageable pageable)
	{
		return feedbackRepository.findAll(pageable);
	}

	@Override
	public boolean updateFeedbackStatus(Long id, String status)
	{
        // Find the feedback by its ID
        Feedback feedback = feedbackRepository.findById(id).orElse(null);
        
        // If feedback exists, update its status
        if (feedback != null) 
        {
            feedback.setReadStatus(status);
            feedbackRepository.save(feedback);
            return true; // Return true if the update was successful
        }
        
        // Return false if feedback with the given ID was not found
        return false;
    }
}
