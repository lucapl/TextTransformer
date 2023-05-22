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
        String[] words = text.split(" ");
        String[] result = text.split(" ");
        for (Map.Entry<String, String> acronym : mapping.entrySet()) {
            // continue if string doesn't contain given word
            if (!text.toLowerCase().contains(acronym.getKey())) {
                continue;
            }

            for (int i = 0; i < words.length; i++) {
                if (!words[i].equalsIgnoreCase(acronym.getKey())) {
                } else if (Character.isUpperCase(words[i].charAt(0))) {
                    result[i] = acronym.getValue().substring(0,1).toUpperCase() + acronym.getValue().substring(1);
                } else {
                    result[i] = acronym.getValue();
                }
            }
        }
        return String.join(" ", result);
    }

    /**
     * Replaces acronyms with their unwind versions
     * @param text text on replacements will be performed
     * @return
     */
    public String unwindAcronyms(String text) {
        return applyMapping(acronyms, text);
    }

    /**
     * Replaces unwind acronyms with their short versions
     * @param text text on replacements will be performed
     * @return
     */
    public String acronimize(String text) {
        return applyMapping(getExpansions(), text);
    }
}
