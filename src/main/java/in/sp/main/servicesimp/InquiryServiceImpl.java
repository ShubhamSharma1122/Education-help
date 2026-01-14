package in.sp.main.servicesimp;

import java.util.List;

import org.springframework.stereotype.Service;

import in.sp.main.entities.Inquiry;
import in.sp.main.repositories.InquiryRepository;
import in.sp.main.service.InquiryService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class InquiryServiceImpl implements InquiryService
{
	
	private final InquiryRepository inquiryRepository;

	@Override
	public void addNewInquiry(Inquiry inquiry)
	{
		inquiryRepository.save(inquiry);
	}

	@Override
	public List<Inquiry> findInquiriesUsingPhno(String phoneno)
	{
		return inquiryRepository.findByPhoneno(phoneno);
	}
}
