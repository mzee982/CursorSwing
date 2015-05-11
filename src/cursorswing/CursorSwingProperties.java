package cursorswing;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import javafx.geometry.Rectangle2D;
import javafx.scene.paint.Color;
import javafx.stage.Screen;

public class CursorSwingProperties extends Properties {
    
    private static final String PROPERTIES_FILE_NAME = "MouseCursorSwing.properties";
    private static final String DEFAULT_PROPERTIES_FILE_NAME = "MouseCursorSwing-defaults.properties";
    
    private static final String KEY_REFRESH_INTERVAL = "KEY_REFRESH_INTERVAL";
    private static final String KEY_MIN_SPEED_X = "KEY_MIN_SPEED_X";
    private static final String KEY_MAX_SPEED_X = "KEY_MAX_SPEED_X";
    private static final String KEY_MIN_SPEED_Y = "KEY_MIN_SPEED_Y";
    private static final String KEY_MAX_SPEED_Y = "KEY_MAX_SPEED_Y";
    private static final String KEY_MIN_X = "KEY_MIN_X";
    private static final String KEY_MIN_Y = "KEY_MIN_Y";
    private static final String KEY_MAX_X = "KEY_MAX_X";
    private static final String KEY_MAX_Y = "KEY_MAX_Y";
    private static final String KEY_DIVERSITY_X = "KEY_DIVERSITY_X";
    private static final String KEY_DIVERSITY_Y = "KEY_DIVERSITY_Y";
    private static final String KEY_CURSOR_RADIUS = "KEY_CURSOR_RADIUS";
    private static final String KEY_CURSOR_GRADIENT_STOP_OFFSET_0 = "KEY_CURSOR_GRADIENT_STOP_OFFSET_0";
    private static final String KEY_CURSOR_GRADIENT_STOP_OFFSET_1 = "KEY_CURSOR_GRADIENT_STOP_OFFSET_1";
    private static final String KEY_CURSOR_GRADIENT_STOP_OFFSET_2 = "KEY_CURSOR_GRADIENT_STOP_OFFSET_2";
    private static final String KEY_CURSOR_GRADIENT_STOP_OFFSET_3 = "KEY_CURSOR_GRADIENT_STOP_OFFSET_3";
    private static final String KEY_CURSOR_GRADIENT_STOP_COLOR_0 = "KEY_CURSOR_GRADIENT_STOP_COLOR_0";
    private static final String KEY_CURSOR_GRADIENT_STOP_COLOR_1 = "KEY_CURSOR_GRADIENT_STOP_COLOR_1";
    private static final String KEY_CURSOR_GRADIENT_STOP_COLOR_2 = "KEY_CURSOR_GRADIENT_STOP_COLOR_2";
    private static final String KEY_CURSOR_GRADIENT_STOP_COLOR_3 = "KEY_CURSOR_GRADIENT_STOP_COLOR_3";
    
    private enum SwingKeys {
        KEY_REFRESH_INTERVAL, 
        KEY_MIN_SPEED_X, 
        KEY_MAX_SPEED_X, 
        KEY_MIN_SPEED_Y, 
        KEY_MAX_SPEED_Y, 
        KEY_MIN_X, 
        KEY_MIN_Y, 
        KEY_MAX_X, 
        KEY_MAX_Y, 
        KEY_DIVERSITY_X, 
        KEY_DIVERSITY_Y
    };
    
    private enum CursorKeys {
        KEY_CURSOR_RADIUS, 
        KEY_CURSOR_GRADIENT_STOP_OFFSET_0, 
        KEY_CURSOR_GRADIENT_STOP_OFFSET_1, 
        KEY_CURSOR_GRADIENT_STOP_OFFSET_2, 
        KEY_CURSOR_GRADIENT_STOP_OFFSET_3, 
        KEY_CURSOR_GRADIENT_STOP_COLOR_0, 
        KEY_CURSOR_GRADIENT_STOP_COLOR_1, 
        KEY_CURSOR_GRADIENT_STOP_COLOR_2, 
        KEY_CURSOR_GRADIENT_STOP_COLOR_3
    };
    
    private final int screenWidth;
    private final int screenHeight;
    
    public CursorSwingProperties() {

        // Screen dimensions
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getBounds();
        screenWidth = (int) Math.round(primaryScreenBounds.getWidth());
        screenHeight = (int) Math.round(primaryScreenBounds.getHeight());
        
        //
        defaults = getDefaults();
        
        //
        load();
        
    }
    
    private Properties getDefaults() {
        Properties defaultProperties = new Properties();
        loadDefaults(defaultProperties);
        
/*
        defaultProperties.setProperty(KEY_REFRESH_INTERVAL, String.valueOf(5));
        defaultProperties.setProperty(KEY_MIN_SPEED_X, String.valueOf(1.0d));
        defaultProperties.setProperty(KEY_MAX_SPEED_X, String.valueOf(2.0d));
        defaultProperties.setProperty(KEY_MIN_SPEED_Y, String.valueOf(1.0d));
        defaultProperties.setProperty(KEY_MAX_SPEED_Y, String.valueOf(2.0d));
        defaultProperties.setProperty(KEY_MIN_X, String.valueOf(0));
        defaultProperties.setProperty(KEY_MIN_Y, String.valueOf(0));
        defaultProperties.setProperty(KEY_MAX_X, String.valueOf(screenWidth));
        defaultProperties.setProperty(KEY_MAX_Y, String.valueOf(screenHeight));
        defaultProperties.setProperty(KEY_DIVERSITY_X, String.valueOf(3.0d));
        defaultProperties.setProperty(KEY_DIVERSITY_Y, String.valueOf(3.0d));
        
        storeDefaults(defaultProperties);
*/
        
        return defaultProperties;
    }
    
    private void load() {
        load(this, PROPERTIES_FILE_NAME);
    }
    
    private void loadDefaults(Properties defaultProperties) {
        load(defaultProperties, DEFAULT_PROPERTIES_FILE_NAME);
    }
    
    private void load(Properties properties, String fileName) {
        InputStream input = null;
        
        try {
            input = new FileInputStream(fileName);
            properties.load(input);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (input != null) {
                try {
                    input.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
        //
        validation(properties);
        
    }
    
    public void store() {
        store(this, PROPERTIES_FILE_NAME);
    }
    
    private void storeDefaults(Properties defaultProperties) {
        store(defaultProperties, DEFAULT_PROPERTIES_FILE_NAME);
    }
    
    private void store(Properties properties, String fileName) {
        OutputStream output = null;
        
        try {
            output = new FileOutputStream(fileName);
            properties.store(output, null);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (output != null) {
                try {
                    output.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    private void validation(Properties properties) {
        int maxX = Integer.parseInt(properties.getProperty(KEY_MAX_X, String.valueOf(screenWidth)));
        int maxY = Integer.parseInt(properties.getProperty(KEY_MAX_Y, String.valueOf(screenHeight)));
        
        if (maxX > screenWidth) properties.setProperty(KEY_MAX_X, String.valueOf(screenWidth));
        if (maxY > screenHeight) properties.setProperty(KEY_MAX_Y, String.valueOf(screenHeight));
    }
    
    public void resetToDefaultsSwing() {
        for (SwingKeys keyConstant : SwingKeys.values()) {
            String keyString = keyConstant.name();
            String defaultValue = defaults.getProperty(keyString);
            setProperty(keyString, defaultValue);
        }
    }
    
    public void resetToDefaultsCursor() {
        for (CursorKeys keyConstant : CursorKeys.values()) {
            String keyString = keyConstant.name();
            String defaultValue = defaults.getProperty(keyString);
            setProperty(keyString, defaultValue);
        }
    }
    
    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }
    
    public int getRefreshInterval() {
        return Integer.parseInt(getProperty(KEY_REFRESH_INTERVAL));
    }

    public void setRefreshInterval(int refreshInterval) {
        setProperty(KEY_REFRESH_INTERVAL, String.valueOf(refreshInterval));
    }

    public double getMinSpeedX() {
        return Double.parseDouble(getProperty(KEY_MIN_SPEED_X));
    }

    public void setMinSpeedX(double minSpeedX) {
        setProperty(KEY_MIN_SPEED_X, String.valueOf(minSpeedX));
    }

    public double getMaxSpeedX() {
        return Double.parseDouble(getProperty(KEY_MAX_SPEED_X));
    }

    public void setMaxSpeedX(double maxSpeedX) {
        setProperty(KEY_MAX_SPEED_X, String.valueOf(maxSpeedX));
    }

    public double getMinSpeedY() {
        return Double.parseDouble(getProperty(KEY_MIN_SPEED_Y));
    }

    public void setMinSpeedY(double minSpeedY) {
        setProperty(KEY_MIN_SPEED_Y, String.valueOf(minSpeedY));
    }

    public double getMaxSpeedY() {
        return Double.parseDouble(getProperty(KEY_MAX_SPEED_Y));
    }

    public void setMaxSpeedY(double maxSpeedY) {
        setProperty(KEY_MAX_SPEED_Y, String.valueOf(maxSpeedY));
    }

    public int getMinX() {
        return Integer.parseInt(getProperty(KEY_MIN_X));
    }

    public void setMinX(int minX) {
        setProperty(KEY_MIN_X, String.valueOf(minX));
    }

    public int getMinY() {
        return Integer.parseInt(getProperty(KEY_MIN_Y));
    }

    public void setMinY(int minY) {
        setProperty(KEY_MIN_Y, String.valueOf(minY));
    }
    
    public int getMaxX() {
        return Integer.parseInt(getProperty(KEY_MAX_X));
    }

    public void setMaxX(int maxX) {
        setProperty(KEY_MAX_X, String.valueOf(maxX));
    }

    public int getMaxY() {
        return Integer.parseInt(getProperty(KEY_MAX_Y));
    }

    public void setMaxY(int maxY) {
        setProperty(KEY_MAX_Y, String.valueOf(maxY));
    }
    
    public double getDiversityX() {
        return Double.parseDouble(getProperty(KEY_DIVERSITY_X));
    }

    public void setDiversityX(double diversityX) {
        setProperty(KEY_DIVERSITY_X, String.valueOf(diversityX));
    }

    public double getDiversityY() {
        return Double.parseDouble(getProperty(KEY_DIVERSITY_Y));
    }

    public void setDiversityY(double diversityY) {
        setProperty(KEY_DIVERSITY_Y, String.valueOf(diversityY));
    }

    public int getCursorRadius() {
        return Integer.parseInt(getProperty(KEY_CURSOR_RADIUS));
    }

    public void setCursorRadius(int cursorRadius) {
        setProperty(KEY_CURSOR_RADIUS, String.valueOf(cursorRadius));
    }
    
    public int getCursorGradientStopOffset0() {
        return Integer.parseInt(getProperty(KEY_CURSOR_GRADIENT_STOP_OFFSET_0));
    }

    public void setCursorGradientStopOffset0(int cursorGradientStopOffset) {
        setProperty(KEY_CURSOR_GRADIENT_STOP_OFFSET_0, String.valueOf(cursorGradientStopOffset));
    }
    
    public int getCursorGradientStopOffset1() {
        return Integer.parseInt(getProperty(KEY_CURSOR_GRADIENT_STOP_OFFSET_1));
    }

    public void setCursorGradientStopOffset1(int cursorGradientStopOffset) {
        setProperty(KEY_CURSOR_GRADIENT_STOP_OFFSET_1, String.valueOf(cursorGradientStopOffset));
    }
    
    public int getCursorGradientStopOffset2() {
        return Integer.parseInt(getProperty(KEY_CURSOR_GRADIENT_STOP_OFFSET_2));
    }

    public void setCursorGradientStopOffset2(int cursorGradientStopOffset) {
        setProperty(KEY_CURSOR_GRADIENT_STOP_OFFSET_2, String.valueOf(cursorGradientStopOffset));
    }
    
    public int getCursorGradientStopOffset3() {
        return Integer.parseInt(getProperty(KEY_CURSOR_GRADIENT_STOP_OFFSET_3));
    }

    public void setCursorGradientStopOffset3(int cursorGradientStopOffset) {
        setProperty(KEY_CURSOR_GRADIENT_STOP_OFFSET_3, String.valueOf(cursorGradientStopOffset));
    }
    
    public Color getCursorGradientStopColor0() {
        return Color.web(getProperty(KEY_CURSOR_GRADIENT_STOP_COLOR_0));
    }

    public void setCursorGradientStopColor0(Color cursorGradientStopColor) {
        setProperty(KEY_CURSOR_GRADIENT_STOP_COLOR_0, Utils.colorToHexString(cursorGradientStopColor));
    }
    
    public Color getCursorGradientStopColor1() {
        return Color.web(getProperty(KEY_CURSOR_GRADIENT_STOP_COLOR_1));
    }

    public void setCursorGradientStopColor1(Color cursorGradientStopColor) {
        setProperty(KEY_CURSOR_GRADIENT_STOP_COLOR_1, Utils.colorToHexString(cursorGradientStopColor));
    }
    
    public Color getCursorGradientStopColor2() {
        return Color.web(getProperty(KEY_CURSOR_GRADIENT_STOP_COLOR_2));
    }

    public void setCursorGradientStopColor2(Color cursorGradientStopColor) {
        setProperty(KEY_CURSOR_GRADIENT_STOP_COLOR_2, Utils.colorToHexString(cursorGradientStopColor));
    }
    
    public Color getCursorGradientStopColor3() {
        return Color.web(getProperty(KEY_CURSOR_GRADIENT_STOP_COLOR_3));
    }

    public void setCursorGradientStopColor3(Color cursorGradientStopColor) {
        setProperty(KEY_CURSOR_GRADIENT_STOP_COLOR_3, Utils.colorToHexString(cursorGradientStopColor));
    }
    
}
