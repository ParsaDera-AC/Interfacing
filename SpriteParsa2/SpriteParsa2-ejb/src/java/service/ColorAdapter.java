
package service;

import java.awt.Color;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * This class which extends XmlAdapter itself is responsible for marshaling and unmarshaling  whcih takes Color object and returns a new color object
 * @author parsa
 */
public class ColorAdapter extends XmlAdapter<ColorAdapter.ColorValueType, Color> {
    /**
     * requires marshal method
     * @param v accepts a ColorValuType that is defined below
     * @return a Color object 
     * @throws Exception 
     */
    @Override
    public Color unmarshal(ColorValueType v) throws Exception {
        return new Color(v.red, v.green, v.blue);
    }
    /**
     * requires unmarshal method
     * @param v accepts a Color object
     * @return A colorValueType that is defined below
     * @throws Exception 
     */
    @Override
    public ColorValueType marshal(Color v) throws Exception {
        return new ColorValueType(v.getRed(), v.getRed(), v.getBlue());
    }
    /**
     * Static class that an instance of it is passed on to the unmarshal and marshal methods
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class ColorValueType {
        private int red;
        private int green;
        private int blue;
        /**
         * Default constructor of ColorValueType
         */
        public ColorValueType() {}
        /**
         * parameterized constructor of ColorValueType
         * @param red
         * @param green
         * @param blue 
         */
        public ColorValueType(int red, int green, int blue) {
            this.red = red;
            this.green = green;
            this.blue = blue;
        }
    } 
}