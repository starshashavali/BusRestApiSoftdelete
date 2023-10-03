package com.org.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.org.domain.Bus;

public interface BusRepo extends JpaRepository<Bus, Long> {

	@Modifying
	@Query("UPDATE Bus  SET isDeleted = true WHERE id =:busId")
	@Transactional
	void updateIsDeleted(Long busId);

	List<Bus> findByIsDeletedFalse();

}
