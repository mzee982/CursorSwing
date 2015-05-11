package cursorswing;

import javafx.scene.paint.Color;

public class Utils {
    
    public static String colorToHexString(Color color) {
        return String.format( "0x%02X%02X%02X%02X",
            (int) (color.getRed() * 255),
            (int) (color.getGreen() * 255),
            (int) (color.getBlue() * 255),
            (int) (color.getOpacity() * 255));
    }
    
}
