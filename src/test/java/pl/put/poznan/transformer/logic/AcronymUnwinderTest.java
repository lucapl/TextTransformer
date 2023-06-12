package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.put.poznan.transformer.logic.decorator.TextDecorator;

class AcronymUnwinderTest {
    private AcronymUnwinder unwinder;

    @BeforeEach
    public void setup() {
        unwinder = new AcronymUnwinder(null);
    }

    @Test
    public void testConvert() {
        AcronymManager mockedManager = Mockito.mock(AcronymManager.class);

        // Set up the desired behavior of the mock
        Mockito.when(mockedManager.unwindAcronyms("Doc.")).thenReturn("Doctor");

        unwinder.setAcronymManager(mockedManager);

        // Call the trueConvert method with a sample input
        String result = unwinder.convert("Doc.");

        // Verify that the mocked method was called with the correct input
        Mockito.verify(mockedManager).unwindAcronyms("Doc.");

        // Assert the result
        Assertions.assertEquals("Doctor", result);
    }

    @Test
    public void  testConvertsWithWrapees() {
        AcronymManager mockedManager = Mockito.mock(AcronymManager.class);

        // Set up the desired behavior of the mock
        Mockito.when(mockedManager.unwindAcronyms("Doc.")).thenReturn("Doctor");

        // Create an instance of Acronymizer with the mocked AcronymManager
        unwinder = new AcronymUnwinder(null);

        TextDecorator wrapee = Mockito.mock(TextDecorator.class);
        Mockito.when(wrapee.convert("Doctor")).thenReturn("Nice.");

        unwinder = new AcronymUnwinder(wrapee);
        unwinder.setAcronymManager(mockedManager);
        String result = unwinder.convert("Doc.");

        Mockito.verify(mockedManager).unwindAcronyms("Doc.");

        Mockito.verify(wrapee).convert("Doctor");

        Assertions.assertEquals("Nice.", result);
    }
}