package pl.put.poznan.transformer.logic;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AcronymManager {
    private HashMap<String, String> acronyms;

    /**
     * Initializes the AcronymManager with predefined acronyms and their expansions.
     */
    AcronymManager() {
        acronyms = new HashMap<>();
        acronyms.put("for example", "e.g.");
        acronyms.put("among others", "i.a.");
        acronyms.put("and so on", "aso");
        acronyms.put("professor", "prof.");
        acronyms.put("doctor", "dr.");
    }

    /**
     * Retrieves a mapping of expansions to acronyms.
     *
     * @return a HashMap containing the expansions as keys and the acronyms as values
     */
    private HashMap<String, String> getExpansions() {
        HashMap<String, String> expansions = new HashMap<>();

        for (Map.Entry<String, String> entry : acronyms.entrySet()) {
            expansions.put(entry.getValue(), entry.getKey());
        }

        return expansions;
    }

    /**
     * Replaces keys in the mapping with their corresponding values in the given text.
     *
     * @param mapping a HashMap containing the mapping of acronyms and their expansions
     * @param text    the text in which the replacements will be performed
     * @return the text with the replacements applied
     */
    private String applyMapping(HashMap<String, String> mapping, String text) {
        StringBuffer result = new StringBuffer(text);

        for (Map.Entry<String, String> acronym : mapping.entrySet()) {
            String patternStr = "(?i)\\b" + acronym.getKey();
            String replacement = acronym.getValue();

            Pattern pattern = Pattern.compile(patternStr);
            Matcher matcher = pattern.matcher(result);

            result = new StringBuffer();
            while (matcher.find()) {
                String match = matcher.group();
                matcher.appendReplacement(result, getRightCase(match, replacement));
            }
            matcher.appendTail(result);
        }

        return result.toString();
    }

    /**
     * Returns the replacement with the correct casing based on the casing of the matched acronym.
     *
     * @param match       the matched acronym in the text
     * @param replacement the replacement value for the acronym
     * @return the replacement with the correct casing
     */
    private String getRightCase(String match, String replacement) {
        if (Character.isUpperCase(match.charAt(0))) {
            if (replacement.length() < 2) {
                return replacement.toUpperCase();
            } else {
                String firstLetter = replacement.substring(0, 1).toUpperCase();
                String restOfWord = replacement.substring(1).toLowerCase();
                return firstLetter + restOfWord;
            }
        }

        return replacement;
    }

    /**
     * Replaces acronyms with their expanded versions in the given text.
     *
     * @param text the text in which the replacements will be performed
     * @return the text with the acronyms expanded
     */
    public String unwindAcronyms(String text) {
        return applyMapping(getExpansions(), text);
    }

    /**
     * Replaces expanded acronyms with their short versions in the given text.
     *
     * @param text the text in which the replacements will be performed
     * @return the text with the acronyms replaced by their short versions
     */
    public String acronymize(String text) {
        return applyMapping(acronyms, text);
    }
}

