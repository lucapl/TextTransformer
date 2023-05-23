public class TextCapitalizer extends TextDecorator {

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

    private String capitalizeWord(String word) {
        if (word.isEmpty()) {
            return word;
        }
        return Character.toUpperCase(word.charAt(0)) + word.substring(1).toLowerCase();
    }
}
