package pl.put.poznan.transformer.logic.decorator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.put.poznan.transformer.logic.TextConverter;

import static java.lang.Float.parseFloat;

public class FloattoTextConverter extends NumberConverter{

    private static final Logger logger = LoggerFactory.getLogger(FloattoTextConverter.class);
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
            return convertNumber((int)(number)) + " and " + convertFloat(number%1);
        }
        if (number < 0) {
            return "minus " + convertFloat(-number);
        }

        logger.debug("Float accuracy up to a thousandths");
        int newnum = (int)(number * 1000);
        if (newnum % 100 == 0) {
            return convertNumber(newnum/100) + " tenths";
        }
        if (newnum % 10 == 0){
            return convertNumber(newnum/10) + " hundredths";
        }
        return convertNumber(newnum) + " thousandths";
    }
}
