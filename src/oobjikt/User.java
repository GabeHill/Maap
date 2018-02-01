package oobjikt;

public class User {
	private String email;
	private String fName;
	private String lName;

	public User(String fName, String lName, String email) {
		setfName(fName);
		setlName(lName);
		setEmail(email);
	}

	public String getEmail() {
		return email;
	}

	public String getfName() {
		return fName;
	}

	public String getlName() {
		return lName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	@Override
	public String toString() {
		final StringBuilder b = new StringBuilder("Person:");
		b.append(" fName=").append(getfName());
		b.append(", lName=").append(getlName());
		b.append(", email=").append(getEmail());
		return b.toString();
	}
}
