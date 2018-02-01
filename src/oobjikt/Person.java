package oobjikt;

public class Person {
	private int annualSalary;
	private String fName;
	private String ID;
	private String lName;

	public Person(int annualSalary, String fName, String lName, String ID) {
		setAnnualSalary(annualSalary);
		setfName(fName);
		setlName(lName);
		setID(ID);
	}

	public int getAnnualSalary() {
		return annualSalary;
	}

	public String getfName() {
		return fName;
	}

	public String getID() {
		return ID;
	}

	public String getlName() {
		return lName;
	}

	public void setAnnualSalary(int annualSalary) {
		this.annualSalary = annualSalary;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	@Override
	public String toString() {
		final StringBuilder b = new StringBuilder("Person:");
		b.append("\nannualSalary=").append(getAnnualSalary());
		b.append(", fName=").append(getfName());
		b.append(", lName=").append(getlName());
		return b.toString();
	}
}
