package pl.put.poznan.transformer.logic;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AcronymManager {
    private HashMap<String, String> acronyms;

    AcronymManager() {
        acronyms = new HashMap<>();
        acronyms.put("for example", "e.g.");
        acronyms.put("among others", "i.a.");
        acronyms.put("and so on", "aso");
        acronyms.put("professor", "prof.");
        acronyms.put("doctor", "dr.");
    }

    private HashMap<String, String> getExpansions() {
        HashMap<String, String> expansions = new HashMap<>();

        for (Map.Entry<String, String> entry : acronyms.entrySet()) {
            expansions.put(entry.getValue(), entry.getKey());
        }

        return expansions;
    }

    /**
     * Replaces keys in mapping with values
     * @param mapping
     * @param text
     * @return
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
                matcher.appendReplacement(result, getRightCase(match, replacement) );
            }
            matcher.appendTail(result);
        }

        return result.toString();
    }

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
     * Replaces acronyms with their unwind versions
     * @param text text on replacements will be performed
     * @return
     */
    public String unwindAcronyms(String text) {
        return applyMapping(getExpansions(), text);
    }

    /**
     * Replaces unwind acronyms with their short versions
     * @param text text on replacements will be performed
     * @return
     */
    public String acronymize(String text) {
        return applyMapping(acronyms, text);
    }
}
