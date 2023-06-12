package pl.put.poznan.transformer.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TextTransformerControllerTest {

    private TextTransformerController rest;

    private JsonNode payload;
    private JsonNode text;
    private ArrayNode args;
    private JsonNode arg1;

    @BeforeEach
    public void setup(){
        rest = new TextTransformerController();
        text = mock(JsonNode.class);
        when(text.asText()).thenReturn("conversion test");
        arg1 = mock(JsonNode.class);
        when(arg1.asText()).thenReturn("up");
        args = mock(ArrayNode.class);
        when(args.elements()).thenReturn(Collections.singletonList(arg1).iterator());
        payload = mock(JsonNode.class);
        when(payload.get("text")).thenReturn(text);
        when(payload.get("transforms")).thenReturn(args);
    }

    @Test
    public void testPayloadGetting() throws JsonProcessingException {
        assertEquals("{\"output\":\"CONVERSION TEST\",\"input\":\"conversion test\",\"used_transforms\":[\"up\"]}",rest.get(payload));
        // test if "text" field read from payload
        verify(payload).get("text");
        // test if "transforms" field read from payload
        verify(payload).get("transforms");
    }

    @Test
    public void testPayloadArgumentConversion() throws JsonProcessingException {
        assertEquals("{\"output\":\"CONVERSION TEST\",\"input\":\"conversion test\",\"used_transforms\":[\"up\"]}",rest.get(payload));
        // test if text field converted to string
        verify(text,atLeast(1)).asText();
        // test if transforms field was iterated over
        verify(args,atLeast(1)).elements();
        // test if transforms arguments converted to text
        verify(arg1,times(1)).asText();
    }
}