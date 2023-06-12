package pl.put.poznan.transformer.logic;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AcronymManagerTest {

    private AcronymManager manager;

    @BeforeEach
    public void setup() {
        manager = new AcronymManager();
    }

    @Test
    public void testForExample() {
        // Test input text with "for example" acronym
        String inputText = "Please bring some essential items for the trip, like a toothbrush, for example.";

        // Expected output text after acronymization
        String expectedOutput = "Please bring some essential items for the trip, like a toothbrush, e.g..";

        // Perform the acronymization using the AcronymManager
        String outputText = manager.acronymize(inputText);

        // Assert that the output matches the expected result
        Assertions.assertEquals(expectedOutput, outputText);
    }

    @Test
    public void testAmongOthers() {
        // Test input text with "among others" acronym
        String inputText = "There are various job opportunities in the IT industry, such as software development, among others.";

        // Expected output text after acronymization
        String expectedOutput = "There are various job opportunities in the IT industry, such as software development, i.a..";

        // Perform the acronymization using the AcronymManager
        String outputText = manager.acronymize(inputText);

        // Assert that the output matches the expected result
        Assertions.assertEquals(expectedOutput, outputText);
    }

    @Test
    public void testAndSoOn() {
        // Test input text with "and so on" acronym
        String inputText = "The conference will cover topics related to technology, business, and so on.";

        // Expected output text after acronymization
        String expectedOutput = "The conference will cover topics related to technology, business, aso.";

        // Perform the acronymization using the AcronymManager
        String outputText = manager.acronymize(inputText);

        // Assert that the output matches the expected result
        Assertions.assertEquals(expectedOutput, outputText);
    }

    @Test
    public void testProfessor() {
        // Test input text with "professor" acronym
        String inputText = "Professor Johnson is known for his expertise in computer science.";

        // Expected output text after acronymization
        String expectedOutput = "Prof. Johnson is known for his expertise in computer science.";

        // Perform the acronymization using the AcronymManager
        String outputText = manager.acronymize(inputText);

        // Assert that the output matches the expected result
        Assertions.assertEquals(expectedOutput, outputText);
    }

    @Test
    public void testDoctor() {
        // Test input text with "doctor" acronym
        String inputText = "Doctor Smith specializes in pediatric medicine and has a thriving practice.";

        // Expected output text after acronymization
        String expectedOutput = "Dr. Smith specializes in pediatric medicine and has a thriving practice.";

        // Perform the acronymization using the AcronymManager
        String outputText = manager.acronymize(inputText);

        // Assert that the output matches the expected result
        Assertions.assertEquals(expectedOutput, outputText);
    }


    @Test
    public void testUnwindAcronyms() {
        AcronymManager acronymManager = new AcronymManager();

        // Test input text with expanded acronyms
        String inputText = "This is an e.g. text with acronyms, e.g., i.a..";

        // Expected output text after unwinding the acronyms
        String expectedOutput = "This is an for example text with acronyms, for example, among others.";

        // Perform the unwinding of acronyms using the AcronymManager
        String outputText = acronymManager.unwindAcronyms(inputText);

        // Assert that the output matches the expected result
        Assertions.assertEquals(expectedOutput, outputText);
    }

    @Test
    public void testAllAcronyms() {
        AcronymManager acronymManager = new AcronymManager();

        // Test input text with all acronyms
        String inputText = "This is a test with acronyms: for example, among others, and so on, professor, doctor.";

        // Expected output text after acronymizing all acronyms
        String expectedOutput = "This is a test with acronyms: e.g., i.a., aso, prof., dr..";

        // Perform the acronymization using the AcronymManager
        String outputText = acronymManager.acronymize(inputText);

        // Assert that the output matches the expected result
        Assertions.assertEquals(expectedOutput, outputText);

        // Perform the unwinding of acronyms using the AcronymManager
        String unwoundText = acronymManager.unwindAcronyms(outputText);

        // Assert that the unwound text matches the original input text
        Assertions.assertEquals(inputText, unwoundText);
    }
}
