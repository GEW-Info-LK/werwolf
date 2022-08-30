import java.util.*;

import static java.lang.System.out;

public class Prompts {
	static private final Spielleiter leiter = Spielleiter.get();
	static private final Scanner scan = new Scanner(System.in);
	static private final Random random = new Random();

	static private final List<String> positives = List.of("y", "yes", "j", "ja", "true", "1");
	static private final List<String> negatives = List.of("n", "no", "nein", "false", "0");


	public static boolean bool() {
		do {
			var input = scan.nextLine();
			if (positives.contains(input)) return true;
			else if (negatives.contains(input)) return false;
			else {
				out.println("Ungültige Eingabe: " + input + " (erwartete *boolean*)");
				out.println("Beispieleingabe: " + positives.get(random.nextInt(positives.size())));
			}
		} while (true);
	}

	public static boolean bool(String prompt) {
		out.print(prompt + "\t");
		return bool();
	}

	public static String next() {
		return scan.nextLine();
	}

	public static String next(String prompt) {
		out.print(prompt + "\t");
		return next();
	}

	public static void warte() {
		scan.nextLine();
	}

	public static Spieler spieler() {
		var input = scan.nextLine();
		try {
			var integer = Integer.parseInt(input);
			return leiter.spieler[integer];
		} catch (NumberFormatException ignored) {}
		for (Spieler s: leiter.spieler) {
			if (s.name.equalsIgnoreCase(input)) return s;
		}
		out.println("Ungültiger Spieler: " + input);
		out.println("Beispiel: " + leiter.spieler[random.nextInt(leiter.spieler.length)]);
		return spieler();
	}

	public static Spieler spieler(String prompt) {
		out.println(prompt + "\t");
		return spieler();
	}

	public static Spieler dorfbewohner() {
		var spieler = spieler();
		if (spieler.istWerwolf()) {
			System.out.println("Spieler " + spieler.name + " ist ein Werwolf!");
			return dorfbewohner();
		}
		return spieler;
	}

	public static Spieler dorfbewohner(String prompt) {
		out.println(prompt + "\t");
		return dorfbewohner();
	}
}
