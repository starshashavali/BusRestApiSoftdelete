package com.org.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.org.dto.BusDTO;

public interface IBusService {
	
	public BusDTO saveBus(BusDTO bus);
	
	public List<BusDTO> getAllBustes();
	
	public List<BusDTO> getAllBustesWithSort(String field);
	
	
	public Page<BusDTO> getAllBustesWithPagination(Integer offset, Integer pageSize);

}
