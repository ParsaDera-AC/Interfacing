
package jsfpages;


import java.awt.Color;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 * This is my main class for Converting the Color objects @FacesConverter("convertorParsa") determines the link with the JSF pages
 * The overall logic if the class is to investigate the [r= xxx, g= xxx, b= xxx] format...if the user enters the color in form of [r...
 * If the user decides to pass a hexadecimal value for the color, he/she has to specify that with a '#' at the beginning and send the color in form of #XXXXXX 
 * 
 * @author parsa
 */
@FacesConverter("convertorParsa")
    public class ColorConvert implements Converter {
    /**
     * 
     * The whole logic of this class is based on converting a string of form [r= xxx, g= xxx, b= xxx] and #XXXXXX  to a COLOR object
     * 
     * @param facesContext FacesContext contains all of the per-request state information related to the processing of a single JavaServer Faces request, and the rendering of the corresponding response
     * @param component UIComponent is the base class for all user interface components in JavaServer Faces
     * @param param the value that user enters
     * @return 
     */
        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String param) { 
            //check if the user enters empty or not
            if (param == null || param.length() == 0) {return null;}
            param = param.replaceAll("\\s", "");
            param = param.toLowerCase();
            
            if (param.charAt(0) != '#' && param.charAt(0) != '[') { return null;}
            
            if (param.charAt(0) == '[') {
                
                param = param.replaceAll(",", "");
                StringBuilder strObj = new StringBuilder();
                int i = 1;
                if (param.charAt(i) != 'r' || param.charAt(i+1) != '='){return null;} //must have the format of " [r=  " 
                i+=2;
                do
                {
                    strObj.append(param.charAt(i));
                    i++;
                } while (Character.isDigit(param.charAt(i)));
                
                int r = Integer.valueOf(strObj.toString());
                if (param.charAt(i) != 'g'|| param.charAt(i+1) != '='){return null;}
                i+=2;
                strObj = new StringBuilder();
                do
                {
                    strObj.append(param.charAt(i));
                    i++;
                }while (Character.isDigit(param.charAt(i)));               
                int g = Integer.valueOf(strObj.toString());
                if (param.charAt(i) != 'b'|| param.charAt(i+1) != '='){return null;}
                i+=2;
                strObj = new StringBuilder();
                do
                {
                    strObj.append(param.charAt(i));
                    i++;
                }while (Character.isDigit(param.charAt(i)));              
                int b = Integer.valueOf(strObj.toString());
                return new Color(r, g, b);
            }
            else if (param.charAt(0) == '#')
            {
                param = param.replaceAll("#", "");
                if (param.length() != 6) {  return null;    }
                String[] hexaDecimalArray = new String[3];
                hexaDecimalArray[0] = param.substring(0, 2);
                hexaDecimalArray[1] = param.substring(2, 4);
                hexaDecimalArray[2] = param.substring(4, 6);
                int r = Integer.parseInt(hexaDecimalArray[0], 16);
                int g = Integer.parseInt(hexaDecimalArray[1], 16);
                int b = Integer.parseInt(hexaDecimalArray[2], 16);
                
                return new Color(r, g, b);
            }
            return null;
        }

        /**
         * This method is responsible for receiving  a color object and turning it to a string of [r= xxx, g= xxx, b= xxx]
         * @param param a Color Object 
         * @return a string in form of [r= xxx, g= xxx, b= xxx]
         */
        String getDisplayColor(Color param) {
            return "[r=" + param.getRed() + ", g=" + param.getGreen() + ", b=" + param.getBlue() + "]";
        }
        
        
        
     /**
      * This method is responsible for doing tasks such as making sure the passed on object in not null and conversion between color object and string and etc
      * @param facesContext FacesContext contains all of the per-request state information related to the processing of a single JavaServer Faces request, and the rendering of the corresponding response
      * @param component UIComponent is the base class for all user interface components in JavaServer Faces
      * @param object the object that is being passed on
      * @return 
      */

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Color) {
                Color color = (Color)object;
                return getDisplayColor(color);
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Color.class.getName());
            }
        }
    }
