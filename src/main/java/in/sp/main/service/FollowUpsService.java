package in.sp.main.service;

import java.util.List;

import in.sp.main.entities.FollowUps;

public interface FollowUpsService {

	void addFollowUps(FollowUps followUps);

	List<FollowUps> getMyFollowUps(String empEmail, String followUpDate);

}
