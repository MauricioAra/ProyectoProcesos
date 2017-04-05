package com.cenfotec.procesos.service.util;

/**
 * Utility class that does all the appropriate project management calculations
 * 
 * @author mmullerc
 *
 */
public final class PMCalculationsUtil {
	
	private static int ZERO = 0;
	
	//Private constructor so this class can't get instantiated 
	private PMCalculationsUtil() {
		
	}
	
	public static double plannedValue(double costPerHour, double totalPlannedHours) {		
		return costPerHour * totalPlannedHours;
	}
	
	public static double earnedValue(double proyectBudget, double proyectCompletionPercent) {
		return proyectBudget * proyectCompletionPercent;
	}
	
	public static double actualCost(double costPerHour, double totalHoursToDate) {
		return costPerHour * totalHoursToDate;
	}
	
	public static double scheduleVariance(double earnedValue, double plannedValue) {
		return earnedValue - plannedValue;
	}
	
	public static double costVariance(double earnedValue, double actualCost) {		
		return earnedValue - actualCost;
	}
	
	public static double SPI(double earnedValue, double plannedValue) {
		double spi = ZERO;
	
		if (plannedValue != ZERO) {
			spi = earnedValue / plannedValue;
		}
		return spi;
	}
	
	public static double CPI(double earnedValue, double actualCost) {
		double cpi = ZERO;
		
		if(actualCost != 0) {
			cpi = earnedValue / actualCost;
		}
		return cpi;
	}
	
	public static double EAC(double BAC, double CPI) {
		return BAC / CPI;
	}
}
