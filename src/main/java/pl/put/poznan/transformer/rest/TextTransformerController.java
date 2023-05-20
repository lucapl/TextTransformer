package pl.put.poznan.transformer.rest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.TextTransformer;

import java.util.*;


@RestController
@RequestMapping("/api")
/**
 * Controls the REST api calls and returns for the application
 */
public class TextTransformerController {

    /**
     * Name of the JSON argument corresponding to input text
     */
    private final String text_arg = "text";
    /**
     * Name of the JSON argument corresponding to transformations to be performed
     */
    private final String transforms_arg = "transforms";
    /**
     * for loggin information
     */
    private static final Logger logger = LoggerFactory.getLogger(TextTransformerController.class);

    /**
     * The GET request method that inputs and outputs a JSON
     * @param payload containing the "text" and "transforms" to be performed
     * @return a json containing the converted text and used transformations
     * @throws JsonProcessingException when converting the output to JSON failed
     */
    @RequestMapping(
            value = "/convert",
            method = RequestMethod.GET,
            produces = "application/json")
    public String get(@RequestBody JsonNode payload) throws JsonProcessingException {
        JsonNode text = payload.get(text_arg);
        ArrayNode transformsNode = (ArrayNode) payload.get(transforms_arg);
        List<String> transforms = new ArrayList<>();
        for (JsonNode transform : transformsNode){
            transforms.add(transform.asText());
        }
        // log the parameters
        logger.debug(text.toPrettyString());
        logger.debug(transforms.toString());


        ObjectMapper mapper = new ObjectMapper();
        TextTransformer textTransformer = new TextTransformer(transforms);
        String output = textTransformer.transform(text.asText());
        return mapper.writeValueAsString(packOutput(text.asText(),output,transformsNode));
    }

    /**
     * Pack the output into a map
     * @param input the initial input
     * @param output the converted text
     * @param usedTransforms transformations used to convert the text
     * @return the mapping
     */
    private Map<String,Object> packOutput(String input,String output,JsonNode usedTransforms){
        Map<String,Object> map = new HashMap<>();
        map.put("used_transforms",usedTransforms);
        map.put("output","Hello " + output);
        map.put("input",input);
        return map;
    }
}


