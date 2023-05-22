package pl.put.poznan.transformer.logic;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AcronymManager {
    private HashMap<String, String> acronyms;

    AcronymManager() {
        acronyms = new HashMap<>();
        acronyms.put("for exmaple", "e.g.");
        acronyms.put("amoung others", "i.a.");
        acronyms.put("and so on", "aso");
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
        String result = text;
        for (Map.Entry<String, String> acronym : mapping.entrySet()) {
            String pattern = "(?i)" + acronym.getKey();
            Pattern compiledPattern = Pattern.compile(pattern);
            Matcher matcher = compiledPattern.matcher(result);
            result = matcher.replaceAll(acronym.getValue());
        }
        return result;
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
