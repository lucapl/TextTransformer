package pl.put.poznan.transformer.logic.decorator;

import pl.put.poznan.transformer.logic.TextConverter;

import static java.lang.Float.parseFloat;

public class FloattoTextConverter extends NumberConverter{

    /**
     * Creates an instance of the class, with another text converter inside
     *
     * @param wrappee a text converter which is part of the decorator pipeline
     */
    public FloattoTextConverter(TextConverter wrappee) {
        super(wrappee);
    }

    @Override
    public String trueConvert(String text) {
        String[] parts = text.split(" ");
        String result = "";
        for (String part : parts) {
            try {
                float number = parseFloat(part);
                result += convertFloat(number) + " ";
            } catch (NumberFormatException e) {
                result += part + " ";
            }
        }

        return result;
    }

    private String convertFloat(float number){
        if (number == 0.0) {
            return "zero";
        }
        String result = "";
        if(number>1){
            result += convertNumber((int)(number)) + " and " + convertFloat(number%1);
        }
        if (number < 0) {
            result += "minus " + convertFloat(-number);
        }
        if (number >0.1){
            result += convertNumber((int)(number*10)) + " tenths";
        }
        if (number == 0.1){
            result += convertNumber((int)(number*10)) + " tenth";
        }
        if (number >0.01){
            result += convertNumber((int)(number*100)) + " hundredths";
        }
        if (number ==0.01){
            result += convertNumber((int)(number*100)) + " hundredth";
        }
        if (number >0.001){
            result += convertNumber((int)(number*1000)) + " thousandths";
        }
        if (number == 0.001){
            result += convertNumber((int)(number*1000)) + " thousandth";
        }
        if (number<0.001){
            result += "Number too small to convert. Please choose above 0.001.";
        }
        return result;
    }
}
