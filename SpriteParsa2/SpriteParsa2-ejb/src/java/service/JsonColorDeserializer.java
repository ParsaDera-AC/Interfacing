
package service;

import java.awt.Color;
import javax.json.bind.serializer.JsonbDeserializer;
import javax.json.stream.JsonParser;
import static javax.json.stream.JsonParser.Event.KEY_NAME;
import static javax.json.stream.JsonParser.Event.VALUE_NUMBER;

/**
 * This class is responsible for deserializing the JSON for incoming Http request
 * @author parsa
 */
public class JsonColorDeserializer implements JsonbDeserializer<Color> {
    /**
     * The only method of the JsonColorDeserializer which is responsible for the actual deserilizing. 
     * It parses the incoming JSON value and creates events based on the parsed value which then is seperated by KEY-VALUE 
     * @param parser of the stream JSONParser class
     * @param ctx
     * @param rtType of Java lang class
     * @return a Color object with rgb value
     */
    @Override
    public Color deserialize(javax.json.stream.JsonParser parser, javax.json.bind.serializer.DeserializationContext ctx, java.lang.reflect.Type rtType) {
        String keyname = "";  
        int value = 0; 
        int red = 0; 
        int green = 0; 
        int blue = 0;
        while (parser.hasNext()) {
            JsonParser.Event event = parser.next();
            switch (event) {
                case KEY_NAME: {
                    keyname = parser.getString();
                    break;
                }
                case VALUE_NUMBER: {
                    value = parser.getInt();
                    if (keyname.equals("red")) red = value;
                    else if (keyname.equals("green")) green = value;
                    else if (keyname.equals("blue")) blue = value; 
                    break;
                }
            }
        }  
        return new Color(red,green,blue);
    }
}