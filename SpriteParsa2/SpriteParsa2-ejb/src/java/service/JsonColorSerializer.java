/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;
import java.awt.Color;
import javax.json.bind.serializer.JsonbSerializer;
import javax.json.bind.serializer.SerializationContext;
import javax.json.stream.JsonGenerator;

/**
 * Serializes the JSON. 
 * @author parsa
 */
public class JsonColorSerializer implements JsonbSerializer<Color>  {
    /**
     * Writes JSON data to an output source in a streaming way. The class Json contains methods to create generators for character or output streams 
     * @param c color object
     * @param jg of the class, explained above
     * @param ctx Provides JSONB internals for custom serializers
     */
    @Override
    public void serialize(Color c, JsonGenerator jg, SerializationContext ctx) {
        jg.writeStartObject();
        jg.write("red", c.getRed());
        jg.write("green", c.getGreen());
        jg.write("blue", c.getBlue());
        jg.writeEnd();
    }
}