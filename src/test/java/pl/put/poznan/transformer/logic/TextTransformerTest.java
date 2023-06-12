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

   
}