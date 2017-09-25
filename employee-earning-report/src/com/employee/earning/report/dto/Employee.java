package com.employee.earning.report.dto;

public class Employee {

	private String firstName;
	private String lastName;
	private String classType;
	private boolean isRewarded;

	/**
	 * Default constructor
	 */
	public Employee() {}

	/**
	 * Parameterized constructor
	 * @param firstName String
	 * @param lastName String
	 * @param classType String
	 * @param isRewarded boolean
	 */
	public Employee(String firstName, String lastName, String classType, boolean isRewarded) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.classType = classType;
		this.isRewarded = isRewarded;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

	public boolean isRewarded() {
		return isRewarded;
	}

	public void setRewarded(boolean isRewarded) {
		this.isRewarded = isRewarded;
	}

	/**
	 * This method returns the employees full name
	 * @return String
	 */
	public String getName() {
		return this.firstName + " " + this.lastName;
	}

}
