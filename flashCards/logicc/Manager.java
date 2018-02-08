package logicc;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import lib.ConsoleIO;
import lib.Filer;

public class Manager {

	private static HashMap<String, String> cards;

	private static void addCard() {
		final String k = ConsoleIO.promptForInput("Enter front side of card:", false);
		final String v = ConsoleIO.promptForInput("Enter back side of card:", false);
		cards.put(k, v);
	}

	private static void loadCards() {
		String p;
		List<String> l = null;
		boolean broken = true;
		do {
			try {
				p = ConsoleIO.promptForInput("Enter file path to read from:", false);
				l = Filer.readFileToList(p);
				broken = false;
			} catch (final IOException e) {
				ConsoleIO.printErr("Something went wrong. Check your file path and try again.");
			}
		} while (broken);

		for (final String s : l) {
			final String[] f = s.split(" :: ");
			cards.put(f[0], f[1]);
		}
	}

	private static void manageMenu() {
		final int in = ConsoleIO.promptForMenuSelection(
				new String[] { "Add card", "Remove card", "Save cards", "Load Cards", "Return to main menu" }, false);

		switch (in) {

		case 1:
			addCard();
			break;

		case 2:
			removeCard();
			break;

		case 3:
			saveCards();
			break;

		case 4:
			loadCards();
			break;
		default:
			break;
		}
	}

	private static void removeCard() {
		final String[] s = (String[]) cards.keySet().toArray();
		final String[] f = (String[]) cards.values().toArray();
		for (int i = 0; i < cards.size();) {
			ConsoleIO.printLn((++i) + ": " + s[i] + ": " + f[i]);
		}
		final int in = ConsoleIO.promptForInt("Which card would you like to remove?", 1, cards.size());
		cards.remove(s[in]);
	}

	private static void review() {
		boolean cont = true;
		final String[] s = (String[]) cards.keySet().toArray();

		do {
			final int f = (int) (Math.random() * s.length);
			ConsoleIO.printLn(s[f]);

			final String input = ConsoleIO
					.promptForInput("To continue press enter. Enter anything else to return to menu.", true);
			if (!input.equals("")) {
				ConsoleIO.printLn(cards.get(s[f]));
			} else {
				cont = false;
			}
		} while (cont);

	}

	public static void run() {
		boolean cont = true;
		cards = new HashMap<>();
		do {
			final int in = ConsoleIO.promptForMenuSelection(new String[] { "Manage cards", "Review" }, true);

			switch (in) {
			case 1:
				manageMenu();
				break;

			case 2:
				review();
				break;

			default:
				cont = false;
				break;
			}

		} while (cont);
	}

	private static void saveCards() {
		final String p = ConsoleIO.promptForInput("Enter a file path to save the cards to", false);
		final String[] s = (String[]) cards.keySet().toArray();
		final String[] f = (String[]) cards.values().toArray();

		final StringBuilder b = new StringBuilder();

		for (int i = 0; i < f.length; i++) {
			b.append(s[i]).append(" :: ").append(f[i]).append("\n");
		}

		Filer.writeToFile(p, b.toString());
	}
}
