package com.employee.earning.report;

import com.employee.earning.report.dto.CommissionedEmployee;
import com.employee.earning.report.dto.Employee;
import com.employee.earning.report.dto.HourlyEmployee;
import com.employee.earning.report.dto.SalariedEmployee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class GenerateEarningReport {

	public static void main(String[] args) {
		System.out.println("=============================================");
		System.out.println("Welcome to Employee Earning Report Tool!");
		System.out.println("=============================================");

		// Setting the keyboard as an standard input device
		final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		// Loading the employee details
		final List<Employee> employeeDetails = new ArrayList<Employee>();
		loadEmployeeDetails(employeeDetails);

		String continueFlag = "y";
		while (continueFlag.equalsIgnoreCase("y")) {
			System.out.println();
			System.out.println("--------------------------------------------------------------------------");
			System.out.println("Please choose from the below options (enter either 1 or 2):");
			System.out.println("1. Calculate weekly pay for an employee");
			System.out.println("2. Reward a selected salaried employee by adding 10% to their salaries");
			System.out.println("--------------------------------------------------------------------------");

			try {
				int option = Integer.parseInt(reader.readLine());

				switch (option) {
				case 1:
					calculateEmployeeWeeklyPay(reader, employeeDetails);
					break;
				case 2:
					printEmployeeDetails(employeeDetails);
					rewardSalariedEmployee(reader, employeeDetails);
					break;
				default:
					System.out.println("Invalid option, please choose correct option!");
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid option, please choose correct option!");

			} catch (IOException e) {
				System.out.println("Invalid option, please choose correct option!");
			}

			System.out.print("\nDo you want to continue? (y/n): ");
			try {
				continueFlag = reader.readLine();
			} catch (IOException e) {
				System.out.println("Error occurred while reading the input, please try again!");
			}
		}

		System.out.println("\nThanks for using the tool!\n");

		// Closing the scanner
		try {
			reader.close();
		} catch (IOException e) {
			System.out.println("Error occurred while reading the input, please try again!");
		}
	}

	/**
	 * This method is used to calculate employee's weekly pay
	 * @param reader BufferedReader
	 * @param employeeDetails List<Employee>
	 * @throws IOException
	 */
	private static void calculateEmployeeWeeklyPay(final BufferedReader reader, final List<Employee> employeeDetails) throws IOException {
		System.out.println("Enter Employee First Name: ");
		String firstName = reader.readLine();

		System.out.println("Enter Employee Last Name: ");
		String lastName = reader.readLine();

		System.out.println("Enter Employee Type (Salaried or Hourly or Commissioned): ");
		String employeeType = reader.readLine();

		if (employeeType.equalsIgnoreCase("Salaried")) {
			System.out.println("Please enter your monthly salary: ");
			double monthlySalary = Double.parseDouble(reader.readLine());

			final SalariedEmployee salariedEmployee = new SalariedEmployee(firstName, lastName, employeeType, monthlySalary, false);
			employeeDetails.add(salariedEmployee);

			salariedEmployeeEarningReport(salariedEmployee);

		} else if (employeeType.equalsIgnoreCase("Hourly")) {

			System.out.println("Please enter your hourly rate: ");
			double hourlyRate = Double.parseDouble(reader.readLine());

			System.out.println("Please enter your weekly worked hours: ");
			double weeklyWorkedHours = Double.parseDouble(reader.readLine());

			final HourlyEmployee hourlyEmployee = new HourlyEmployee(firstName, lastName, employeeType, weeklyWorkedHours, hourlyRate, false);
			employeeDetails.add(hourlyEmployee);

			hourlyEmployeeEarningReport(hourlyEmployee);

		} else if (employeeType.equalsIgnoreCase("Commissioned")) {
			System.out.println("Please enter your weekly sales amount: ");
			double weeklySalesAmount = Double.parseDouble(reader.readLine());
			final CommissionedEmployee commissionedEmployee = new CommissionedEmployee(firstName, lastName, employeeType, weeklySalesAmount, false);
			employeeDetails.add(commissionedEmployee);

			commissionedEmployeeEarningReport(commissionedEmployee);
		} else {
			System.out.println("Invalid employee type");
		}
	}

	/**
	 * This method is used to print the earning report of a salaried employee
	 * @param salariedEmployee SalariedEmployee
	 */
	private static void salariedEmployeeEarningReport(final SalariedEmployee salariedEmployee) {
		System.out.format("%s\t\t\t%s\n", "Name", "Weekly Pay Amount");
		System.out.println("====================================================================");
		final StringBuilder employeeName = new StringBuilder();
		employeeName.append(salariedEmployee.getFirstName()).append(" ").append(salariedEmployee.getLastName());
		final NumberFormat formatter = NumberFormat.getCurrencyInstance();
		System.out.format("%s\t\t\t%s", employeeName.toString(), formatter.format(salariedEmployee.getMonthlySalary() / 4));
	}

	/**
	 * This method is used to print the earning report of an hourly employee
	 * @param hourlyEmployee HourlyEmployee
	 */
	private static void hourlyEmployeeEarningReport(final HourlyEmployee hourlyEmployee) {
		System.out.format("%s\t\t\t%s\n", "Name", "Weekly Pay Amount");
		System.out.println("====================================================================");
		final StringBuilder employeeName = new StringBuilder();
		employeeName.append(hourlyEmployee.getFirstName()).append(" ").append(hourlyEmployee.getLastName());

		double weeklyPayAmount = 40 * hourlyEmployee.getHourlyRate();

		// Rate will be doubled if it’s beyond 40 hours/week.
		if (hourlyEmployee.getWeeklyWorkedHours() > 40) {
			double overtime = hourlyEmployee.getWeeklyWorkedHours() - 40;
			double overtimePay = overtime * (hourlyEmployee.getHourlyRate() * 2);
			weeklyPayAmount += overtimePay;
		}

		final NumberFormat formatter = NumberFormat.getCurrencyInstance();
		System.out.format("%s\t\t\t%s", employeeName.toString(), formatter.format(weeklyPayAmount));
	}

	private static void commissionedEmployeeEarningReport(final CommissionedEmployee commissionedEmployee) {
		System.out.format("%s\t\t\t%s\n", "Name", "Weekly Pay Amount");
		System.out.println("====================================================================");
		final StringBuilder employeeName = new StringBuilder();
		employeeName.append(commissionedEmployee.getFirstName()).append(" ").append(commissionedEmployee.getLastName());

		final NumberFormat formatter = NumberFormat.getCurrencyInstance();
		System.out.format("%s\t\t\t%s", employeeName.toString(), formatter.format(commissionedEmployee.getWeeklySales() * commissionedEmployee.getCommissionRate()));
	}

	/**
	 * This method is used to choose and reward a salaried employee
	 * @param reader BufferedReader
	 * @throws IOException
	 */
	private static void rewardSalariedEmployee(final BufferedReader reader, final List<Employee> employeeDetails) throws IOException {
		System.out.print("Do you want to reward an employee? (y/n): ");
		String rewardFlag = reader.readLine();
		if (!rewardFlag.isEmpty() || rewardFlag.equalsIgnoreCase("y")) {
			System.out.println("Please choose any salaried employee name for the reward: ");
			final String rewardedEmployeeName = reader.readLine();
			boolean employeeFound = false;
			for (Employee employee : employeeDetails) {
				if (employee.getName().equalsIgnoreCase(rewardedEmployeeName)) {
					if (!employee.getClassType().equalsIgnoreCase("Salaried")) {
						System.out.println("\nERROR: Please choose only salaried employee for the reward");
						employeeFound = true;
					} else if (employee.isRewarded()) {
						System.out.println("\nERROR: This employee has already been awarded, please choose another employee");
						employeeFound = true;
					} else {
						employee.setRewarded(true);
						employeeFound = true;
						printEmployeeDetails(employeeDetails);
					}
					break;
				}
			}

			if (!employeeFound) {
				System.out.println("\nERROR: Employee not found!");
			}
		}
	}

	/**
	 * This method is used to print the employee details.
	 * @param employeeDetails List<Employee>
	 */
	private static void printEmployeeDetails(final List<Employee> employeeDetails) {
		String headerFormat = "%-25s%-20s%-20s%-20s%-20s%-20s\n";
		String rowFormat = "%-25s%-20s%-20s%-20s%-20s%-20s\n";
		System.out.println();
		System.out.println("Employee details:");
		System.out.println("=============================================================================================================================");
		System.out.format(headerFormat, "Name", "Class", "Hours", "Sales", "Rate", "Weekly Pay Amount");
		System.out.println("=============================================================================================================================");
		final NumberFormat formatter = NumberFormat.getCurrencyInstance();
		for (Employee employee : employeeDetails) {
			final StringBuilder employeeName = new StringBuilder();
			employeeName.append(employee.getFirstName()).append(" ").append(employee.getLastName());

			if (employee.getClassType().equalsIgnoreCase("Salaried")) {

				double monthlySalary = ((SalariedEmployee) employee).getMonthlySalary();
				boolean isRewarded = employee.isRewarded();
				if (isRewarded) {
					monthlySalary += monthlySalary * 0.1;
				}
				StringBuilder sb = new StringBuilder();
				sb.append(formatter.format(monthlySalary / 4));
				sb.append(isRewarded ? "*" : "");
				System.out.format(rowFormat, employeeName.toString(), employee.getClassType(), "", "", "", sb.toString());

			} else if (employee.getClassType().equalsIgnoreCase("Hourly")) {

				double weeklyWorkedHours = ((HourlyEmployee) employee).getWeeklyWorkedHours();
				double hourlyRate = ((HourlyEmployee) employee).getHourlyRate();
				double weeklyPayAmount = 40 * ((HourlyEmployee) employee).getHourlyRate();

				// Rate will be doubled if it’s beyond 40 hours/week.
				if (((HourlyEmployee) employee).getWeeklyWorkedHours() > 40) {
					double overtime = ((HourlyEmployee) employee).getWeeklyWorkedHours() - 40;
					double overtimePay = overtime * (((HourlyEmployee) employee).getHourlyRate() * 2);
					weeklyPayAmount += overtimePay;
				}

				System.out.format(rowFormat, employeeName.toString(), employee.getClassType(), weeklyWorkedHours, "", formatter.format(hourlyRate), formatter.format(weeklyPayAmount));

			} else {

				double weeklySales = ((CommissionedEmployee) employee).getWeeklySales();
				double commissionRate = ((CommissionedEmployee) employee).getCommissionRate();
				System.out.format(rowFormat, employeeName.toString(), employee.getClassType(), "", formatter.format(weeklySales), "", formatter.format(weeklySales * commissionRate));

			}
		}

		System.out.println("\nNote: * A 10% bonus is awarded\n");

	}

	/**
	 * This method is used to load the employee details
	 * @param employeeDetails List<Employee>
	 */
	private static void loadEmployeeDetails(final List<Employee> employeeDetails) {
		final SalariedEmployee salariedEmployee1 = new SalariedEmployee("James", "Hogan", "Salaried", 13200.00, true);
		final SalariedEmployee salariedEmployee2 = new SalariedEmployee("Joan", "Han", "Salaried", 10400.00, false);

		final HourlyEmployee hourlyEmployee1 = new HourlyEmployee("Jennifer", "Waltz", "Hourly", 45, 10.95, false);
		final HourlyEmployee hourlyEmployee2 = new HourlyEmployee("Moly", "Smith", "Hourly", 32, 15, false);

		final CommissionedEmployee commissionedEmployee = new CommissionedEmployee("Marry", "Butler", "Commissioned", 10000, false);

		employeeDetails.add(salariedEmployee1);
		employeeDetails.add(salariedEmployee2);
		employeeDetails.add(hourlyEmployee1);
		employeeDetails.add(hourlyEmployee2);
		employeeDetails.add(commissionedEmployee);
	}

}
