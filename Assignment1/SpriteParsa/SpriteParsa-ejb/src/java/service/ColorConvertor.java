
package service;

import java.awt.Color;
import java.util.Scanner;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "service")
public class ColorConvertor implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        
        int R = hexToR("#" + value);
        int G = hexToG("#" + value);
        int B = hexToB("#" + value);

        Color color = new Color(R, G, B);
        return color;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Color color = (Color) value;
        return toHex(color.getRed()) + "" + toHex(color.getGreen()) + "" + toHex(color.getBlue());
    }

    private String toHex(int n) {
        n = Math.max(0, Math.min(n, 255));
        return "0123456789ABCDEF".charAt((n - n % 16) / 16) + "" + "0123456789ABCDEF".charAt(n % 16) + "";
    }

    private int hexToR(String h) {
        return Integer.parseInt((cutHex(h)).substring(0, 2), 16);
    }

    private int hexToG(String h) {
        return Integer.parseInt((cutHex(h)).substring(2, 4), 16);
    }

    private int hexToB(String h) {
        return Integer.parseInt((cutHex(h)).substring(4, 6), 16);
    }

    private String cutHex(String h) {
        return (h.charAt(0) == '#') ? h.substring(1, 7) : h;
    }
}
