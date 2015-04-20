package mousecursorswing;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class MouseCursorSwingProperties extends Properties {
    
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
    
    private final int screenWidth;
    private final int screenHeight;
    
    public MouseCursorSwingProperties() {

        // Screen dimensions
        //TODO Dimensions from javafx.stage.Screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = screenSize.width;
        screenHeight = screenSize.height;
        
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
    
    public void resetToDefaults() {
        clear();
        putAll(defaults);
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

}
