package logicc;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import lib.ConsoleIO;
import lib.Filer;
import oobjikt.Credential;
import oobjikt.User;

public class LoginManager {

	private static HashMap<Credential, User> users;

	private static HashMap<Credential, User> assignMap() {
		final List<String> pieces = getFileData();
		final List<String> spliteds = new LinkedList<>();
		final int siz = pieces.size();
		for (int i = 0; i < siz; i++) {
			final String[] s = pieces.get(i).split("[;;\\\n]");
			for (final String string : s) {
				if (!string.equals("") && !string.equals(" ")) {
					spliteds.add(string);
				}
			}
		}
		final HashMap<Credential, User> temp = new HashMap<>();
		for (int i = 0; i < spliteds.size(); i += 5) {
			final Credential f = new Credential(spliteds.get(i), spliteds.get(i + 1));
			final User u = new User(spliteds.get(i + 2), spliteds.get(i + 3), spliteds.get(i + 4));
			temp.put(f, u);
		}
		return temp;
	}

	private static void closeAcct() {
		final String[] f = getCredentials();
		final Credential tmp = new Credential(f[0], f[1]);
		final User u = users.get(tmp);
		String message;
		if (u != null) {
			message = u.toString();
			users.remove(tmp);
		} else {
			message = "No user found for credentials provided.";
		}
		System.out.println(message);

	}

	private static String[] getCredentials() {
		final String[] f = new String[2];
		f[0] = ConsoleIO.promptForInput("Enter your username:", false);
		f[1] = ConsoleIO.promptForInput("Enter your password: ", false);
		return f;
	}

	private static List<String> getFileData() {
		String in;
		List<String> data;
		while (true) {
			in = ConsoleIO.promptForInput("Please enter a file path:", false);
			try {
				data = (Filer.readFileToList(in));
				return data;
			} catch (final IOException e) {
				System.err.println("No file at specified path.");
			}
		}
	}

	private static void initHashMap() {
		users = assignMap();
	}

	private static void login() {
		final String[] f = getCredentials();
		final User u = users.get(new Credential(f[0], f[1]));
		String message;
		if (u != null) {
			message = u.toString();
		} else {
			message = "No user found for credentials provided.";
		}
		System.out.println(message);
	}

	public static void run() {
		boolean cont = true;
		do {
			final int select = ConsoleIO.promptForMenuSelection(new String[] { "Load Users", "Login", "Close Account" },
					true);
			switch (select) {
			case 1:
				initHashMap();
				break;
			case 2:

				login();
				break;
			case 3:
				closeAcct();
				break;
			default:
				cont = false;
				break;
			}
		} while (cont);
	}
}
