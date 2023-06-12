package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test the behaviour of the TextTransformer
 */
class TextTransformerTest {

    /**
     * Test the repeated remover transformer
     */
    @Test
    public void repeaterRemoverTest(){
        TextTransformer unrepeat = new TextTransformer(Arrays.asList("unrepeat"));
        // convert two repeated words to one
        assertEquals("to", unrepeat.transform("to to").strip());
        // convert any number of repeated words
        assertEquals("to", unrepeat.transform("to to to").strip());
        // replace all repetitions in test
        assertEquals("to after", unrepeat.transform("to to after after").strip());
        assertEquals("Your bread eat sticks", unrepeat.transform("Your bread bread bread eat sticks sticks").strip());
        // test for exotic UTF-8 characters
        assertEquals("Ϻ", unrepeat.transform("Ϻ Ϻ Ϻ Ϻ Ϻ Ϻ").strip());
    }

    /**
     * Test the LatexAdapter transformer
     */
    @Test
    public void latexAdapterTest(){
        TextTransformer latex = new TextTransformer(Arrays.asList("latex"));
        // test & -> \&
        assertEquals("\\&",latex.transform("&"));
        // test $ -> \$
        assertEquals("\\$",latex.transform("$"));
        // test in context
        assertEquals("John Smith \\& Sons",latex.transform("John Smith & Sons"));
        assertEquals("what is \\$delta",latex.transform("what is $delta"));
    }

    /**
     * Test the FloattoTextConverter transformer
     */
    @Test
    public void floatConverterTest() {
        TextTransformer floatConverter = new TextTransformer(Arrays.asList("reals"));
        assertEquals("zero ", floatConverter.transform("0"));
        assertEquals("minus two tenths ", floatConverter.transform("-0.2"));
        assertEquals("one thousandths ", floatConverter.transform("0.001"));
        assertEquals("five and twenty three hundredths ", floatConverter.transform("5.23"));
        assertEquals("one hundred twenty three thousandths ", floatConverter.transform("0.123"));
    }

    /**
     * Test the InttoTextConverter transformer
     */
    @Test
    public void intConverterTest() {
        TextTransformer intConverter = new TextTransformer(Arrays.asList("integers"));
        assertEquals("zero ", intConverter.transform("0"));
        assertEquals("minus twenty three ", intConverter.transform("-23"));
        assertEquals("one thousand thirty five ", intConverter.transform("1035"));
        assertEquals("five ", intConverter.transform("5"));
        assertEquals("one hundred twenty three ", intConverter.transform("123"));
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