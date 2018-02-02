package oobjikt;

import java.util.Random;

public class Credential {
	public final String passwd;
	public final String username;

	public Credential(String username, String passwd) {
		this.username = username;
		this.passwd = passwd;
	}

	@Override
	public boolean equals(Object obj) {
		final Credential f = (Credential) obj;
		return (f.username.equals(username)) && (f.passwd.equals(passwd));
	}

	@Override
	public int hashCode() {
		final Random r = new Random(42);
		int res = 13;
		res += (passwd.hashCode() * r.nextInt());
		res += (username.hashCode() * r.nextInt());
		return res;
	}

}
