package com.prs33.prs.request;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RequestRepository extends JpaRepository<Request, Integer> {
	
	List<Request> findAllByStatusAndUserIdNot(String status, int id);

}
