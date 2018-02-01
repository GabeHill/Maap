package logicc;

import java.util.HashMap;

import oobjikt.Person;

public class Hashe {

	private static HashMap<String, Person> hash = new HashMap<>();

	public static void initHashMap() {
		for (int i = 0; i < 4; i++) {
			final Person p = new Person((int) (Math.random() * 200000), "Flapper" + i, "Doodle" + i, "FlDo" + i + i);
			hash.put(p.getID(), p);
		}
	}

	public static void run() {
		initHashMap();

		hash.put("FlDo11", new Person(42, "ff", "fff", "FlDo11"));
		System.out.println(hash.get("FlDo11"));

	}

}
