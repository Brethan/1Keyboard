package main;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Ported from Zack Freedman's insult generator:
 * https://github.com/ZackFreedman/text-generators
 * 
 * @author Brethan
 *
 */
public class TextGenerator {

	private Random random = new SecureRandom();

	private String[] insultingAdjectives = { "sweaty", "big", "fat", "dumb", "stupid", "goddamn", "moist", "mega-",
			"turbo-", "hyper-", "idiotic", "worthless", "flesh-covered", "uptight", "poop flinging", "diseased",
			"hairy", "miserable", "lonely", "sad", "brain-damaged", "stupendous", "terminally-online", "millennial",
			"broke-ass", "braindead", "fetal alcohol", "underdeveloped", "talentless", "ignorant", "son-of-a-",
			"store-brand", "irrelevant", "giant", "deformed", "wannabe", "obsolete", "old", "infantile", "unfunny",
			"greasy", "degenerate", "lazy", "slovenly", "buck-toothed", "pathetic", "reptilian", "absolute",
			"feeble-minded", "effeminate", "lumpy", "low-IQ", "sticky", "cringey", "stinky", "phallic", "second-class",
			"corporate", "anime-watching", "wrinkly", "fugly", "disposable", "v-tuber simping", "desperate", "sleazy",
			"lowly", };

	private String[] adnouns = { "-obsessed", "-infested", "-ass", "-like", "-esque", "-face", "-bag", "-sack",
			"-shaped", "-head", "-smelling", "-covered", "-filled", "-looking-ass", "less", "-tuber", };

	private String[] insultingNouns = { "egg", "glue", "fart", "diarrhea", "hair", "poop", "doo-doo", "mother", "daddy",
			"granny", "pizza", "baby", "sewer", "keeb", "crotch", "frog", "bong", "3D-printer", "puppy", "Funko Pop",
			"diaper", "moustache", "octopus", "kitten", "banana", "Quagsire", "pony", "garbage", "finger", "watermelon",
			"Bionicle", "RGB-LED", "robot", "sausage", "uncle", "goop", "hobo", "cigar", "vape", "cat", "spoon",
			"nugget", "taint", "beer", "hamster", };

	private String[] nounsForYou = { "meat sack", "millennial", "loser", "peasant", "oxygen thief", "waste of space",
			"dork", "nerd", "dweeb", "ignoramus", "knucklehead", "moron", "hack", "psychopath", "mama's boy", "virgin",
			"disappointment", "wimp", "so-and-so", "degenerate", "charlatan", "hack", "drain on society", "parasite",
			"stain", "weeaboo", "creep", "redneck", "phony", "future divorcee", "sellout", "noob", };

	private String[] versatileNouns = { "worm", "douche", "monkey", "orangutan", "wildebeest", "boomer", "dog", "bitch",
			"turd", "booger", "goblin", "ass", "penis", "hoo-ha", "sphinchter", "rodent", "reptile", "dipstick",
			"neanderthal", "wiener", "dingus", "scum", "snake", "weasel", "donkey", "bum", "butt", "pimple", "pustule",
			"boob", "amogus", "corncob", "nut", "nozzle", "redacted", "donut", };

	private String[] insultingVerbs = { "suck", "lick", "kick", "punch", "cuddl", "slapp", "fapp", "tugg", "blast",
			"snort", "defil", "snuggl", "hump", "eat", "lov", "sniff", "touch", "look", "slurp", "ogl", "fondl", "huff",
			"chew", "spew", "punt", "tast", "grabb", "strok", "pucker", "smooch", "disappoint", "hat", "hoard", "guzzl",
			"pinch", };

	private int randomChoice(int max) {
		return random.nextInt(max);
	}

	private String pickCandidate(String[] words, List<String> exclusions) {
		if (random.nextDouble() < 0.02)
			return words[randomChoice(words.length)];

		List<String> candidates = Arrays.stream(words).filter(s -> !exclusions.contains(s))
				.collect(Collectors.toList());

		if (candidates.isEmpty())
			return words[randomChoice(words.length)];

		return candidates.get(randomChoice(candidates.size()));

	}

	private String gimmeAVerb(List<String> exclusions) {
		return pickCandidate(insultingVerbs, exclusions);
	}

	private String nounYourself(boolean compoundWords, List<String> exclusions) {
		double roll = random.nextDouble();
		if (compoundWords || roll < 0.8) {
			return pickCandidate(nounsForYou, exclusions);
		} else if (roll < 0.9) {
			String firstNoun = pickCandidate(random.nextDouble() > 0.5 ? insultingNouns : versatileNouns, exclusions);

			exclusions.add(firstNoun);
			String secondNoun = pickCandidate(random.nextDouble() > 0.5 ? insultingNouns : versatileNouns, exclusions);
			exclusions.remove(firstNoun);

			return firstNoun + " " + secondNoun;

		} else {
			String noun = nounSomethingElse(exclusions);
			exclusions.add(noun);
			String verb = gimmeAVerb(exclusions);
			exclusions.remove(noun);
			return noun + "-" + verb + "er";
		}
	}

	private String nounSomethingElse(List<String> exclusions) {
		return random.nextDouble() > 0.5 ? pickCandidate(versatileNouns, exclusions)
				: pickCandidate(insultingNouns, exclusions);
	}

	private String getAnAdjective(boolean nextNoun, List<String> exclusions) {
		double roll = random.nextDouble();
		if (roll < 0.75 && nextNoun) {
			return pickCandidate(Arrays.stream(insultingAdjectives).filter(s -> !s.endsWith("-"))
					.collect(Collectors.toList()).toArray(new String[0]), exclusions);
		} else if (roll < 0.75)
			return pickCandidate(insultingAdjectives, exclusions);

		String noun = nounSomethingElse(exclusions);

		if (insultingVerbs.length > random.nextInt(insultingVerbs.length + adnouns.length)) {
			String verb = pickCandidate(insultingVerbs, exclusions);
			return noun + "-" + verb + "ing";
		} else {
			String adjective = pickCandidate(adnouns, exclusions);
			return noun + adjective;
		}
	}

	public String hit(int maxWords, List<String> exclusions, double oddsOfContinuing) {
		StringBuilder output = new StringBuilder();
		int wordCount = 1;
		double roll = random.nextDouble();

		while (wordCount < maxWords && roll < oddsOfContinuing) {
			roll = random.nextDouble();
			exclusions.add(output.toString());
			output.append(getAnAdjective(roll < oddsOfContinuing, exclusions));
			if (!output.toString().endsWith("-"))
				output.append(" ");

			wordCount++;
		}

		boolean hypenated = !output.isEmpty() && output.toString().endsWith("-");
		exclusions.add(output.toString());
		output.append(nounYourself(!hypenated, exclusions));

		return output.toString();
	}

}
