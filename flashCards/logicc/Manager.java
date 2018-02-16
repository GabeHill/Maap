package logicc;

import java.io.IOException;
import java.util.ArrayList;
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
		final ArrayList<String> s = new ArrayList<>();
		cards.keySet().forEach(k -> s.add(k));

		final ArrayList<String> f = new ArrayList<>();
		cards.values().forEach(k -> f.add(k));
		if (s.size() < 1 || f.size() < 1) {
			ConsoleIO.printLn("No cards available.");
		} else {

			for (int i = 0; i < cards.size();) {
				ConsoleIO.printLn((i + 1) + ": " + s.get(i) + ": " + f.get(i));
				++i;
			}
			final int in = ConsoleIO.promptForInt("Which card would you like to remove?", 1, cards.size());
			cards.remove(s.get(in - 1));
		}
	}

	private static void review() {
		final ArrayList<String> s = new ArrayList<>();
		cards.keySet().forEach(k -> s.add(k));
		if (s.size() <= 0) {
			ConsoleIO.printLn("No cards available.");
		} else {
			boolean cont = true;
			;

			do {
				final int f = (int) (Math.random() * s.size());
				ConsoleIO.printLn(s.get(f));

				final String input = ConsoleIO
						.promptForInput("To continue press enter. Enter anything else to return to menu.", true);
				if (input.equals("\n") || input.equals("")) {
					ConsoleIO.printLn(cards.get(s.get(f)) + "\n");
				} else {
					cont = false;
				}
			} while (cont);
		}
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
		final ArrayList<String> s = new ArrayList<>();
		cards.keySet().forEach(k -> s.add(k));

		final ArrayList<String> f = new ArrayList<>();
		cards.values().forEach(k -> f.add(k));

		final StringBuilder b = new StringBuilder();

		for (int i = 0; i < f.size(); i++) {
			b.append(s.get(i)).append(" :: ").append(f.get(i)).append("\n");
		}

		Filer.writeToFile(p, b.toString());
	}
}
