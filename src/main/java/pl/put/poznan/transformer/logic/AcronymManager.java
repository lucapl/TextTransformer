package pl.put.poznan.transformer.logic;

import java.util.HashMap;
import java.util.Map;

public class AcronymManager {
    private HashMap<String, String> acronyms;

    AcronymManager() {
        acronyms = new HashMap<>();
        acronyms.put("for exmaple", "e.g.");
        acronyms.put("amoung others", "i.a.");
        acronyms.put("and so on", "aso");
    }

    /**
     * Get mapping between unwinded acronym and acronym
     * @return
     */
    public HashMap<String, String> getAcronyms() {
        return acronyms;
    }

    /**
     * Get mapping between acronym and unwinded acronym
     * @return
     */
    public HashMap<String, String> getExpansions() {
        HashMap<String, String> expansions = new HashMap<>();

        for (Map.Entry<String, String> entry : acronyms.entrySet()) {
            expansions.put(entry.getValue(), entry.getKey());
        }

        return expansions;
    }
}
