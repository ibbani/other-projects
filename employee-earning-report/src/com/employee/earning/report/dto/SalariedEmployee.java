package com.employee.earning.report.dto;

public class SalariedEmployee extends Employee {

	private double monthlySalary;

	/**
	 * Default constructor
	 */
	public SalariedEmployee() {}

	/**
	 * Parameterized constructor
	 * @param firstName String
	 * @param lastName String
	 * @param classType String
	 * @param monthlySalary double
	 * @param isRewarded boolean
	 */
	public SalariedEmployee(String firstName, String lastName, String classType, double monthlySalary, boolean isRewarded) {
		super(firstName, lastName, classType, isRewarded);
		this.monthlySalary = monthlySalary;
	}

	public double getMonthlySalary() {
		return monthlySalary;
	}

	public void setMonthlySalary(double monthlySalary) {
		this.monthlySalary = monthlySalary;
	}

}
