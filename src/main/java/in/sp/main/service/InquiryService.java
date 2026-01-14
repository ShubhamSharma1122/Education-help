package in.sp.main.service;

import java.util.List;

import in.sp.main.entities.Inquiry;

public interface InquiryService {

	void addNewInquiry(Inquiry inquiry);

	List<Inquiry> findInquiriesUsingPhno(String phoneno);

}
