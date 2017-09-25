package com.employee.earning.report.dto;

public class CommissionedEmployee extends Employee {

	private double weeklySales;
	private double commissionRate;

	/**
	 * Default constructor
	 */
	public CommissionedEmployee() {}

	/**
	 * Parameterized constructor
	 * @param firstName String
	 * @param lastName String
	 * @param classType String
	 * @param weeklySales
	 * @param isRewarded boolean
	 */
	public CommissionedEmployee(String firstName, String lastName, String classType, double weeklySales, boolean isRewarded) {
		super(firstName, lastName, classType, isRewarded);
		this.weeklySales = weeklySales;
		this.commissionRate = 0.2;
	}

	public double getWeeklySales() {
		return weeklySales;
	}

	public void setWeeklySales(double weeklySales) {
		this.weeklySales = weeklySales;
	}

	public double getCommissionRate() {
		return commissionRate;
	}

	public void setCommissionRate(double commissionRate) {
		this.commissionRate = commissionRate;
	}

}
