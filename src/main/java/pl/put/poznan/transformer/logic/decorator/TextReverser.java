public class TextReverser extends TextDecorator {

    public TextReverser(TextComponent textComponent) {
        super(textComponent);
    }

    @Override
    public String getText() {
        String text = super.getText();
        StringBuilder reversedText = new StringBuilder();

        for (int i = text.length() - 1; i >= 0; i--) {
            reversedText.append(reverseCharacterCase(text.charAt(i)));
        }

        return reversedText.toString();
    }

    private char reverseCharacterCase(char c) {
        if (Character.isUpperCase(c)) {
            return Character.toLowerCase(c);
        } else if (Character.isLowerCase(c)) {
            return Character.toUpperCase(c);
        }
        return c;
    }
}