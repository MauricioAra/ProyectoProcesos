package com.cenfotec.procesos.service.dto;
/**
 * DTO Entity for all the PM Calculations
 *
 */
public class CalculationsDTO {

	private Double plannedValue;
	
	private Double earnedValue;
	
	private Double actualCost;
	
	private Double scheduleVariance;
	
	private Double costVariance;
	
	private Double spi;
	
	private Double cpi;
	
	private Double eac;

	public Double getPlannedValue() {
		return plannedValue;
	}

	public void setPlannedValue(Double plannedValue) {
		this.plannedValue = plannedValue;
	}

	public Double getEarnedValue() {
		return earnedValue;
	}

	public void setEarnedValue(Double earnedValue) {
		this.earnedValue = earnedValue;
	}

	public Double getActualCost() {
		return actualCost;
	}

	public void setActualCost(Double actualCost) {
		this.actualCost = actualCost;
	}

	public Double getScheduleVariance() {
		return scheduleVariance;
	}

	public void setScheduleVariance(Double scheduleVariance) {
		this.scheduleVariance = scheduleVariance;
	}

	public Double getCostVariance() {
		return costVariance;
	}

	public void setCostVariance(Double costVariance) {
		this.costVariance = costVariance;
	}

	public Double getSpi() {
		return spi;
	}

	public void setSpi(Double spi) {
		this.spi = spi;
	}

	public Double getCpi() {
		return cpi;
	}

	public void setCpi(Double cpi) {
		this.cpi = cpi;
	}

	public Double getEac() {
		return eac;
	}

	public void setEac(Double eac) {
		this.eac = eac;
	}
}
