package com.cenfotec.procesos.service.util;

import java.util.Set;

import com.cenfotec.procesos.domain.Task;

import net.logstash.logback.encoder.org.apache.commons.lang.BooleanUtils;

/**
 * Utility class that does all the appropriate project management calculations
 *
 * @author mmullerc
 *
 */
public final class PMCalculationsUtil {

	//Private constructor so this class can't get instantiated
	private PMCalculationsUtil() {

	}

	public static double plannedValue(double costPerHour, double totalPlannedHours) {
		return costPerHour * totalPlannedHours;
	}

	public static double earnedValue(double projectBudget, double projectCompletionPercent) {
		return projectBudget * projectCompletionPercent;
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
		double spi = 0;

		if (plannedValue != 0) {
			spi = earnedValue / plannedValue;
		}
		return spi;
	}

	public static double CPI(double earnedValue, double actualCost) {
		double cpi = 0;

		if(actualCost != 0) {
			cpi = earnedValue / actualCost;
		}
		return cpi;
	}

	public static double EAC(double BAC, double CPI) {
		return BAC / CPI;
	}

	/**
	 * Calculates an Average of the Hourly Rate
	 * @param tasks
	 * @return hourlyRate
	 */
	public static double hourlyRateAverage(Set<Task> tasks) {
		double hourlyRate = 0;
		double totalHours = 0;

		for (Task task : tasks) {
			hourlyRate = hourlyRate + task.getCost();
			totalHours = totalHours + task.getTime();
		}

		if (hourlyRate != 0) {
			hourlyRate = hourlyRate / totalHours;
		}
		return hourlyRate;
	}
	/**
	 * Calculates the total of hours planned for all the given tasks
	 * @param tasks
	 * @return totalPlannedHours
	 */
	public static double totalPlannedHours(Set<Task> tasks) {
		double totalPlannedHours = 0;

		for (Task task : tasks) {
			totalPlannedHours = totalPlannedHours + task.getTime();
		}
		return totalPlannedHours;
	}
	/**
	 * Calculates the project budget, based on the cost of the tasks
	 * @param tasks
	 * @return projectBudget
	 */
	public static double projectBudget(Set<Task> tasks) {
		double projectBudget = 0;

		for (Task task : tasks) {
			projectBudget = projectBudget + task.getCost();
		}
		return projectBudget;
	}
	/**
	 * Calculates the project completion percent, based on the status of the tasks
	 * @param tasks
	 * @return
	 */
	public static float projectCompletionPercent(Set<Task> tasks) {
		float projectCompletionPercent = 0;
		int totalTasks = tasks.size();
		int completedTasks = 0;

		for (Task task : tasks) {
			if (BooleanUtils.isTrue(task.isStatus())) {
				completedTasks = completedTasks + 1;
			}
		}

		projectCompletionPercent = (float) completedTasks / totalTasks;

		return projectCompletionPercent;
	}
	/**
	 * Calculates the hours to date in a project
	 * @param tasks
	 * @return hoursToDate
	 */
	public static double hoursToDate(Set<Task> tasks) {
		double hoursToDate = 0;

		for (Task task : tasks) {
			if (task.getRealHour() != null) {
				hoursToDate = hoursToDate + task.getRealHour();
			}
		}
		return hoursToDate;
	}
}
