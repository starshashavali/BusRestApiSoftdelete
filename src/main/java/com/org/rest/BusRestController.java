package com.org.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.domain.Bus;
import com.org.dto.BusDTO;
import com.org.service.impl.IBusServiceImpl;

@RestController
@RequestMapping("/api/buses")
@Validated
public class BusRestController {

    @Autowired
    private IBusServiceImpl busService;

    // Endpoint to create a new bus
    @PostMapping("/save")
    public ResponseEntity<BusDTO> createBus(@Valid @RequestBody BusDTO bus) {
    BusDTO saveBus = busService.saveBus(bus);
        return new ResponseEntity<>(saveBus,HttpStatus.CREATED);
    }

    // Endpoint to mark a bus as soft deleted
    @DeleteMapping("bus/{busId}")
    public void softDeleteBus(@Valid @PathVariable Long busId) {
        busService.softDeleteBus(busId);
    }

    // Endpoint to get a list of active buses
    @GetMapping("/active")
    public List<Bus> getActiveBuses() {
        return busService.getActiveBuses();
    }
    @GetMapping("/getAllBuses")
    public ResponseEntity<?> getAllBuses(){
    	List<BusDTO> allBustes = busService.getAllBustes();
    	return new ResponseEntity<>(allBustes,HttpStatus.OK);
    }
    @GetMapping("/getAllBuses/{field}")
    public ResponseEntity<?> getAllBuseswithSorting(@PathVariable String field){
    	List<BusDTO> allBustes = busService.getAllBustesWithSort(field);
    	return new ResponseEntity<>(allBustes,HttpStatus.OK);
    }
    @GetMapping("/getAllBuses/{offset}/{pageSize}")
    public ResponseEntity<?> getAllBuseswithPagination(@PathVariable Integer offset,@PathVariable Integer pageSize){
     Page<BusDTO> dtos = busService.getAllBustesWithPagination(offset, pageSize);
    	return new ResponseEntity<>(dtos,HttpStatus.OK);
    }
    
}


