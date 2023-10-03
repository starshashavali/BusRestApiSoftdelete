package com.org.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

import lombok.Data;

@Data
public class BusDTO {
	
	private Long id;
	@NotBlank(message="busNumber not empty")
	private String busNumber;
	@NotEmpty(message="Seat number should be 1 to 50 ")
	@Positive
	@Min(value=1)
	@Max(value=50)
	private Integer totalSeats;
	@NotBlank(message="BusModel not empty")
	private String busModel;
	private boolean isRunning;
	private boolean isDeleted;

}
