package pl.put.poznan.transformer.logic;

import pl.put.poznan.transformer.logic.decorator.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.put.poznan.transformer.logic.decorator.LatexAdapter;
import pl.put.poznan.transformer.logic.decorator.RepeatsRemover;
import pl.put.poznan.transformer.logic.decorator.TextDecorator;
import pl.put.poznan.transformer.rest.TextTransformerController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Responsible for dynamically creating a text converter based on the transforms
 */
public class TextConverterFactory {
    /**
     * for loggin information
     */
    private static final Logger logger = LoggerFactory.getLogger(TextConverterFactory.class);
    /**
     * Create the converter based on transforms
     * @param transforms array containing string that describe an appropriate transformation
     * @return the fully wrapped text converter
     */
    public TextConverter createConverter(List<String> transforms){
        logger.debug("Creating a converter");
        List<Conversions> conversions = conversionsList(transforms);
        Collections.reverse(conversions);

        TextConverter textConverter = null;
        TextConverter wrapee = null;
        for (var conversion : conversions){
            textConverter = createSpecific(conversion);
            if (textConverter instanceof TextDecorator && wrapee != null) {
                logger.debug("Adding a wrappee "+wrapee+" to "+textConverter);
                TextDecorator tD = (TextDecorator) textConverter;
                tD.setWrappee(wrapee);
            }
            wrapee = textConverter;
        }
        logger.debug("Converter created");
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
        //throw new IllegalArgumentException("No transformation for: " + transform);
        logger.info("NO TRANSFORM FOR: " + transform);
        return null;
    }

    /**
     * Create a list of mapped transformations
     * @param transforms array containing string that describe an appropriate transformation
     * @return array containing Conversions to be made
     */
    public List<Conversions> conversionsList(List<String> transforms){
        logger.debug("Mapping string transforms to enumeration");
        List<Conversions> conversions = new ArrayList<>();
        for ( var transform : transforms){
            Conversions conversion = mapTransform(transform);
            if(conversion != null) conversions.add(conversion);
        }
        return conversions;
    }

    /**
     * Maps Conversions to corresponding TextConverters
     * @param conversion to be made
     * @return corresponding TextConverter
     */
    public TextConverter createSpecific(Conversions conversion) {
        logger.debug("Creating a converter: "+ conversion.toString());
        switch (conversion) {
            case ACRONYMISE:
                return new Acronymizer(null);
            case ACRONYMS_UNWIND:
                return new AcronymUnwinder(null);
            case REMOVE_REPEATS: return new RepeatsRemover(null);
            case LATEX: return new LatexAdapter(null);
            default:
                logger.info("Empty convesrions, creating empty converter");
                return new TextDecorator(null){
                @Override
                public String trueConvert(String text){
                    return text;
                }
            };

            case NUMS_INTS:
                return new InttoTextConverter(null);
            case NUMS_REAL:
                return new FloattoTextConverter(null);

            case CASE_CAPITAL:
            case CASE_UPPER:
            case CASE_LOWER:
                return new TextCapitalizer(null,conversion);
            case REVERSE:
            case REVERSE_PRESERVE_CASE:
                return new TextReverser(null,conversion);
        }
    }
}
