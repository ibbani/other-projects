package com.employee.earning.report.dto;

public class HourlyEmployee extends Employee {

	private double weeklyWorkedHours;
	private double hourlyRate;

	/**
	 * Default constructor
	 */
	public HourlyEmployee() {}

	/**
	 * Parameterized constructor
	 * @param firstName String
	 * @param lastName String
	 * @param classType String
	 * @param weeklyWorkedHours double
	 * @param hourlyRate double
	 * @param isRewarded boolean
	 */
	public HourlyEmployee(String firstName, String lastName, String classType, double weeklyWorkedHours, double hourlyRate, boolean isRewarded) {
		super(firstName, lastName, classType, isRewarded);
		this.weeklyWorkedHours = weeklyWorkedHours;
		this.hourlyRate = hourlyRate;
	}

	public double getWeeklyWorkedHours() {
		return weeklyWorkedHours;
	}

	public void setWeeklyWorkedHours(double weeklyWorkedHours) {
		this.weeklyWorkedHours = weeklyWorkedHours;
	}

	public double getHourlyRate() {
		return hourlyRate;
	}

	public void setHourlyRate(double hourlyRate) {
		this.hourlyRate = hourlyRate;
	}

}
