/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsfpages;


import java.awt.Color;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author parsa
 */
@FacesConverter(forClass = Color.class)
public class ColorConvert implements Converter {
    @Override
    public Object getAsObject(FacesContext context, UIComponent  component, String value) {
        if (value == null || value.length() == 0) return null;
        return new Color(Integer.parseInt(value.substring(1, 3)),
                         Integer.parseInt(value.substring(3, 5)),
                         Integer.parseInt(value.substring(5, 7)));
    }
    
    @Override
    public String getAsString(FacesContext context, UIComponent  component, Object value) {
        if (value == null) return null;
        if (value instanceof Color) {
            return String.format("#%02X%02X%02X",
                    ((Color)value).getRed(),
                    ((Color)value).getGreen(),
                    ((Color)value).getBlue());
        } else {
            throw new IllegalArgumentException("Value " + value + " is of type " + value.getClass().getName() + "; Expected type: " + Color.class.getName());
        }
    }
}