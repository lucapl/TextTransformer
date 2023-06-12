public class TextCapitalizer extends TextDecorator {

    /**
     * Creates an instance of the class, with another text component inside
     *
     * @param textComponent a text component which is part of the decorator pipeline
     */
    public TextCapitalizer(TextComponent textComponent) {
        super(textComponent);
    }

    @Override
    public String getText() {
        String text = super.getText();
        String[] words = text.split("\\s+");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            String transformedWord = transformWord(word);
            result.append(transformedWord);

            if (i < words.length - 1) {
                result.append(" ");
            }
        }

        return result.toString();
    }

    /**
     * Transforms a word based on the specified option
     *
     * @param word the word to transform
     * @return the transformed word
     */
    private String transformWord(String word) {
        String option = getOption();
        switch (option) {
            case "upper":
                return word.toUpperCase();
            case "lower":
                return word.toLowerCase();
            case "capital":
                return capitalizeWord(word);
            default:
                return word;
        }
    }

    /**
     * Capitalizes a word by making the first letter uppercase and the rest lowercase
     *
     * @param word the word to capitalize
     * @return the capitalized word
     */
    private String capitalizeWord(String word) {
        if (word.isEmpty()) {
            return word;
        }
        return Character.toUpperCase(word.charAt(0)) + word.substring(1).toLowerCase();
    }
}

