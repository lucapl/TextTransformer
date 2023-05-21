package pl.put.poznan.transformer.logic;

/**
 * Interface for a single text converter
 */
public interface TextConverter {
    /**
     * Converts the method according to the converter type
     * @param text input
     * @return converted text
     */
    public String convert(String text);
}
