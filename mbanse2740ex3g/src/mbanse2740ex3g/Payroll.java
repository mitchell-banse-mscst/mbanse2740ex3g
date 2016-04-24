package mbanse2740ex3g;
import java.text.DecimalFormat;

public class Payroll {
	private int id;
	private String name;
	private double payRate;
	private double hours;
	
	public Payroll(int id, String name, double payRate) {
		
		this.id = id;
		this.name = name;
		this.payRate = payRate;
		this.hours = 0.0;
	}
	
	public Payroll(int id, String name, double payRate, double hours) {
		
		this.id = id;
		this.name = name;
		this.payRate = payRate;
		this.hours = hours;
	}

	@Override
	public String toString() {
		DecimalFormat dollarFmt = new DecimalFormat("#,##0.00");
		return "id: " + this.id + ", name: " + this.name + ", payRate: " + dollarFmt.format(this.payRate);
	}

	public int getId() {
		return this.id;
	}

	public boolean setId(int id) {
		if (id > 100) {
			this.id = id;
			return true;
		}
		else {
			return false;
		}
	}

	public String getName() {
		return this.name;
	}

	public boolean setName(String name) {
		if (!name.equals("")) {
			this.name = name;
			return true;
		}
		else {
			return false;
		}

	}

	public double getPayRate() {
		return this.payRate;
	}

	public boolean setPayRate(double payRate) {
		if (payRate >= 7.25 && payRate <= 100) {
			this.payRate = payRate;
			return true;
		}
		else {
			return false;
		}
	}

	public double getHours() {
		return this.hours;
	}

	public void setHours(double hours) {
		this.hours = hours;
	}
	
	public double calcGrossPay() {
		double grossPay;
		double overtimePay;
		
		// Determine if hoursWorked is greater than 40.
		if (hours > 40)
		{
			// Calculate regular pay of first 40 hours.
			grossPay = 40 * payRate;
			
			// Calculate overtime pay at 1.5 times rate of payRate.
			overtimePay = (hours - 40) * (payRate * 1.5);
			
			//Add overtime pay to grossPay.
			grossPay += overtimePay;
		}
		else
		{
			grossPay = payRate * hours;
		}
		
		return grossPay;
	}
	
	public boolean addHours(double hours) {
		
		if (hours >=0.10 && hours <= 20) {
			this.hours += hours;
			return true;
		}
		else {
			return false;
		}
	}

}
