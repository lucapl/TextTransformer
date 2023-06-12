package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.put.poznan.transformer.logic.decorator.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Test the behaviour of the TextTransformer
 */
class TextTransformerTest {

    private TextTransformer transformer;
    private TextConverterFactory textConverterFactory;
    @BeforeEach
    public void setup(){
        textConverterFactory = mock(TextConverterFactory.class);
        when(textConverterFactory.createConverter(Arrays.asList("unrepeat"))).thenReturn(new RepeatsRemover(null));
        when(textConverterFactory.createConverter(Arrays.asList("latex"))).thenReturn(new LatexAdapter(null));
        when(textConverterFactory.createConverter(Arrays.asList("reals"))).thenReturn(new FloattoTextConverter(null));
        when(textConverterFactory.createConverter(Arrays.asList("integers"))).thenReturn(new InttoTextConverter(null));
        when(textConverterFactory.createConverter(Arrays.asList("rev_no_case"))).thenReturn(new TextReverser(null,Conversions.REVERSE_PRESERVE_CASE));
        when(textConverterFactory.createConverter(Arrays.asList("rev"))).thenReturn(new TextReverser(null,Conversions.REVERSE));
        when(textConverterFactory.createConverter(Arrays.asList("up"))).thenReturn(new TextCapitalizer(null,Conversions.CASE_UPPER));
        when(textConverterFactory.createConverter(Arrays.asList("low"))).thenReturn(new TextCapitalizer(null,Conversions.CASE_LOWER));
        when(textConverterFactory.createConverter(Arrays.asList("cap"))).thenReturn(new TextCapitalizer(null,Conversions.CASE_CAPITAL));
        when(textConverterFactory.createConverter(Arrays.asList("acr"))).thenReturn(new Acronymizer(null));
        when(textConverterFactory.createConverter(Arrays.asList("unwind"))).thenReturn(new AcronymUnwinder(null));
        transformer = new TextTransformer(null);
        transformer.setTextConverterFactory(textConverterFactory);
    }

    /**
     * Test the repeated remover transformer
     */
    @Test
    public void repeaterRemoverTest(){
        List<String> transforms = Arrays.asList("unrepeat");
        transformer.setTransforms(transforms);
        // convert two repeated words to one
        assertEquals("to", transformer.transform("to to").strip());
        // convert any number of repeated words
        assertEquals("to", transformer.transform("to to to").strip());
        // replace all repetitions in test
        assertEquals("to after", transformer.transform("to to after after").strip());
        assertEquals("Your bread eat sticks", transformer.transform("Your bread bread bread eat sticks sticks").strip());
        // test for exotic UTF-8 characters
        assertEquals("Ϻ", transformer.transform("Ϻ Ϻ Ϻ Ϻ Ϻ Ϻ").strip());

        verify(textConverterFactory,times(5)).createConverter(transforms);
    }

    /**
     * Test the LatexAdapter transformer
     */
    @Test
    public void latexAdapterTest(){
        List<String> transforms = Arrays.asList("latex");
        transformer.setTransforms(transforms);
        // test & -> \&
        assertEquals("\\&",transformer.transform("&"));
        // test $ -> \$
        assertEquals("\\$",transformer.transform("$"));
        // test in context
        assertEquals("John Smith \\& Sons",transformer.transform("John Smith & Sons"));
        assertEquals("what is \\$delta",transformer.transform("what is $delta"));

        verify(textConverterFactory,times(4)).createConverter(transforms);
    }

    /**
     * Test the FloattoTextConverter transformer
     */
    @Test
    public void floatConverterTest() {
        List<String> transforms = Arrays.asList("reals");
        transformer.setTransforms(transforms);

        assertEquals("zero ", transformer.transform("0"));
        assertEquals("minus two tenths ", transformer.transform("-0.2"));
        assertEquals("one thousandths ", transformer.transform("0.001"));
        assertEquals("five and twenty three hundredths ", transformer.transform("5.23"));
        assertEquals("one hundred twenty three thousandths ", transformer.transform("0.123"));

        verify(textConverterFactory,times(5)).createConverter(transforms);
    }

    /**
     * Test the InttoTextConverter transformer
     */
    @Test
    public void intConverterTest() {
        List<String> transforms = Arrays.asList("integers");
        transformer.setTransforms(transforms);

        assertEquals("zero ", transformer.transform("0"));
        assertEquals("minus twenty three ", transformer.transform("-23"));
        assertEquals("one thousand thirty five ", transformer.transform("1035"));
        assertEquals("five ", transformer.transform("5"));
        assertEquals("one hundred twenty three ", transformer.transform("123"));

        verify(textConverterFactory,times(5)).createConverter(transforms);
    }

    /**
     * Test multiple transforms
     */
    @Test
    public void multipleTransformsTest(){
        TextTransformer textTransformer = new TextTransformer(Arrays.asList(
                "unrepeat",
                "ltx",
                "ints",
                "reals",
                "up",
                "rev_no_case"
        ));
        assertEquals(
                "$\\NO$\\ SHtDERdNUH Owt YTFIf &\\ XiS Ytfif HTIM$\\ NhOÏJ ÂÂÂ,NHâj NHoj",
                textTransformer.transform("John Jâhn,âââ Jïohn $mith 56 56 & 0.52 $on$"));
    }
}