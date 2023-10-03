package com.org.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.org.domain.Bus;
import com.org.dto.BusDTO;
import com.org.repo.BusRepo;
import com.org.service.IBusService;

@Service
public class IBusServiceImpl implements IBusService {
	@Autowired
	BusRepo busRepo;

	@Override
	public BusDTO saveBus(BusDTO bus) {
	
		Bus entity = new Bus();
		entity.setRunning(true);
		entity.setDeleted(false);
		
		BeanUtils.copyProperties(bus, entity);
		Bus save = busRepo.save(entity);
		BeanUtils.copyProperties(save, bus);
		return bus;
	}

	public void softDeleteBus(Long busId) {
		busRepo.updateIsDeleted(busId);
	}

	public List<Bus> getActiveBuses() {
		return busRepo.findByIsDeletedFalse();
	}
	
	

	@Override
	public List<BusDTO> getAllBustes() {
		List<Bus> all = busRepo.findAll();
		List<BusDTO> list = new ArrayList<>();
		for (Bus busEntity : all) {
			BusDTO dto = new BusDTO();
			BeanUtils.copyProperties(busEntity, dto);
			list.add(dto);
		}

		return list;
	}

	@Override
	public List<BusDTO> getAllBustesWithSort(String field) {
		List<Bus> all = busRepo.findAll(Sort.by(Direction.ASC,field));
		List<BusDTO> list = new ArrayList<>();
		for (Bus busEntity : all) {
			BusDTO dto = new BusDTO();
			BeanUtils.copyProperties(busEntity, dto);
			list.add(dto);
		}

		return list;
	}



	@Override
	public Page<BusDTO> getAllBustesWithPagination(Integer offset, Integer pageSize) {
		  Page<Bus> busPage = busRepo.findAll(PageRequest.of(offset-1, pageSize));
	        List<BusDTO> busDTOs = new ArrayList<>();

	        for (Bus busEntity : busPage) {
	            BusDTO dto = new BusDTO();
	            BeanUtils.copyProperties(busEntity, dto);
	            busDTOs.add(dto);
	        }

	        return new PageImpl<>(busDTOs, busPage.getPageable(), busPage.getTotalElements());
	    }
	}

