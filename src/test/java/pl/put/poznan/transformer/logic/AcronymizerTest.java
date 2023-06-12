package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.put.poznan.transformer.logic.decorator.TextDecorator;

class AcronymizerTest {
    private Acronymizer acronymizer;

    @BeforeEach
    public void setup() {
        acronymizer = new Acronymizer(null);
    }

    @Test
    public void testConvert() {
        AcronymManager mockedManager = Mockito.mock(AcronymManager.class);

        // Set up the desired behavior of the mock
        Mockito.when(mockedManager.acronymize("For example")).thenReturn("I.e.");

        // Create an instance of Acronymizer with the mocked AcronymManager
        acronymizer = new Acronymizer(null);
        acronymizer.setAcronymManager(mockedManager);

        // Call the trueConvert method with a sample input
        String result = acronymizer.convert("For example");

        // Verify that the mocked method was called with the correct input
        Mockito.verify(mockedManager).acronymize("For example");

        // Assert the result
        Assertions.assertEquals("I.e.", result);
    }

    @Test
    public void  testConvertsWithWrapees() {
        AcronymManager mockedManager = Mockito.mock(AcronymManager.class);

        // Set up the desired behavior of the mock
        Mockito.when(mockedManager.acronymize("For example")).thenReturn("I.e.");

        // Create an instance of Acronymizer with the mocked AcronymManager
        acronymizer = new Acronymizer(null);

        TextDecorator wrapee = Mockito.mock(TextDecorator.class);
        Mockito.when(wrapee.convert("I.e.")).thenReturn("Nice.");

        acronymizer = new Acronymizer(wrapee);
        acronymizer.setAcronymManager(mockedManager);
        String result = acronymizer.convert("For example");

        Mockito.verify(mockedManager).acronymize("For example");

        Mockito.verify(wrapee).convert("I.e.");

        Assertions.assertEquals("Nice.", result);
    }
}