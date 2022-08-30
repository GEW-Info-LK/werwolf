import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static java.lang.System.out;

public class Prompts {
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
		out.print(prompt);
		return bool();
	}

	public static String next() {
		return scan.nextLine();
	}

	public static String next(String prompt) {
		out.print(prompt);
		return next();
	}

	public static void warte() {
		scan.nextLine();
	}
}
