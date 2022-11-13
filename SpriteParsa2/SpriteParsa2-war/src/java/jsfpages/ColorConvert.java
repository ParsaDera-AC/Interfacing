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
@FacesConverter("convertorParsa")
    public class ColorConvert implements Converter {
        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) { 
            if (value == null || value.length() == 0) {
                return null;
            }
            
            // Remove all whitespace and make letters lowercase
            value = value.replaceAll("\\s", "");
            value = value.toLowerCase();
            
            if (value.charAt(0) != '#' && value.charAt(0) != '[') {
                return null;
            }
            
            // Hexadecimal value
            if (value.charAt(0) == '#') {
                value = value.replaceAll("#", "");
                
                if (value.length() != 6) {
                    return null;
                }
                
                String[] arr = new String[3];
                arr[0] = value.substring(0, 2);
                arr[1] = value.substring(2, 4);
                arr[2] = value.substring(4, 6);
                
                int r = Integer.parseInt(arr[0], 16);
                int g = Integer.parseInt(arr[1], 16);
                int b = Integer.parseInt(arr[2], 16);
                
                return new Color(r, g, b);
            }
            
            // RGB value
            else if (value.charAt(0) == '[')
            {
                StringBuilder sb;
                int i = 1;
             
                value = value.replaceAll(",", "");
                
                System.out.println("DEBUG Value is " + value);
                
                if (value.charAt(i) != 'r'
                 || value.charAt(i+1) != '='
                   )
                {
                    return null;
                }
                
                i+=2;
                sb = new StringBuilder();
                while (Character.isDigit(value.charAt(i)))
                {
                    sb.append(value.charAt(i));
                    i++;
                }
                
                int r = Integer.valueOf(sb.toString());
                
                if (value.charAt(i) != 'g'
                 || value.charAt(i+1) != '='
                   )
                {
                    return null;
                }
                
                i+=2;
                sb = new StringBuilder();
                while (Character.isDigit(value.charAt(i)))
                {
                    sb.append(value.charAt(i));
                    i++;
                }
                                
                int g = Integer.valueOf(sb.toString());
                
                if (value.charAt(i) != 'b'
                 || value.charAt(i+1) != '='
                   )
                {
                    return null;
                }
                
                i+=2;
                sb = new StringBuilder();
                while (Character.isDigit(value.charAt(i)))
                {
                    sb.append(value.charAt(i));
                    i++;
                }
                                
                int b = Integer.valueOf(sb.toString());
                
                System.out.println("DEBUG color is " + r + " " + g + " " + b);
                
                return new Color(r, g, b);
            }
            
            return null;
        }

        String getColorHex(Color value) {
            return "[r=" + value.getRed() 
                    + ", g=" + value.getGreen() 
                    + ", b=" + value.getBlue() + "]";
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Color) {
                Color color = (Color)object;
                return getColorHex(color);
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Color.class.getName());
            }
        }
}