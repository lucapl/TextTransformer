package pl.put.poznan.transformer.logic;

import pl.put.poznan.transformer.logic.decorator.TextDecorator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Responsible for dynamically creating a text converter based on the transforms
 */
public class TextConverterFactory {
    /**
     * Create the converter based on transforms
     * @param transforms array containing string that describe an appropriate transformation
     * @return the fully wrapped text converter
     */
    public TextConverter createConverter(List<String> transforms){
        List<Conversions> conversions = conversionsList(transforms);
        Collections.reverse(conversions);

        TextConverter textConverter = null;
        TextConverter wrapee = null;
        for (var conversion : conversions){
            textConverter = createSpecific(conversion);
            if (textConverter instanceof TextDecorator && wrapee != null) {
                TextDecorator tD = (TextDecorator) textConverter;
                tD.setWrappee(wrapee);
            }
            wrapee = textConverter;
        }
        return textConverter;
    }

    /**
     * Map a transform to appropriate conversion
     * @param transform as string
     * @return as Conversion enumeration
     */
    public Conversions mapTransform(String transform){
        switch (transform.toLowerCase()){
            case "upper":
            case "up":
                return Conversions.CASE_UPPER;
            case "low":
            case "lower":
                return Conversions.CASE_LOWER;
            case "cap":
            case "capital":
                return Conversions.CASE_CAPITAL;
            case "rev":
            case "reverse":
                return Conversions.REVERSE;
            case "rev_no_case":
            case "reverse_preserve_case":
                return Conversions.REVERSE_PRESERVE_CASE;
            case "acr":
            case "acronymise":
                return Conversions.ACRONYMISE;
            case "unwind":
                return Conversions.ACRONYMS_UNWIND;
            case "ltx":
            case "latex":
                return Conversions.LATEX;
            case "ints":
            case "integers":
                return Conversions.NUMS_INTS;
            case "reals": return Conversions.NUMS_REAL;
            case "unrepeat": return Conversions.REMOVE_REPEATS;
        }
        throw new IllegalArgumentException("No transformation for: " + transform);
    }

    /**
     * Create a list of mapped transformations
     * @param transforms array containing string that describe an appropriate transformation
     * @return array containing Conversions to be made
     */
    public List<Conversions> conversionsList(List<String> transforms){
        List<Conversions> conversions = new ArrayList<>();
        for ( var transform : transforms){
            conversions.add(mapTransform(transform));
        }
        return conversions;
    }

    /**
     * Maps Conversions to corresponding TextConverters
     * @param conversion to be made
     * @return corresponding TextConverter
     */
    public TextConverter createSpecific(Conversions conversion) {
        switch (conversion) {
            case ACRONYMISE:
                return new Acronymizer(null);
            case ACRONYMS_UNWIND:
                return new AcronymUnwinder(null);
        }
        return new TextDecorator(null){
            @Override
            public String trueConvert(String text){
                return text + "Invalid";
            }
        };
    }
}
