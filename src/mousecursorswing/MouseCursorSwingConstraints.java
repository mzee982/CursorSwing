package mousecursorswing;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class MouseCursorSwingConstraints extends Properties {
    
    private static final String CONSTRAINTS_FILE_NAME = "MouseCursorSwing-constraints.properties";
    
    private static final String KEY_REFRESH_INTERVAL_MIN = "KEY_REFRESH_INTERVAL_MIN";
    private static final String KEY_REFRESH_INTERVAL_MAX = "KEY_REFRESH_INTERVAL_MAX";
    private static final String KEY_SPEED_MIN = "KEY_SPEED_MIN";
    private static final String KEY_SPEED_MAX = "KEY_SPEED_MAX";
    private static final String KEY_DIVERSITY_MIN = "KEY_DIVERSITY_MIN";
    private static final String KEY_DIVERSITY_MAX = "KEY_DIVERSITY_MAX";
    private static final String KEY_CURSOR_RADIUS_MIN = "KEY_CURSOR_RADIUS_MIN";
    private static final String KEY_CURSOR_RADIUS_MAX = "KEY_CURSOR_RADIUS_MAX";
    
    public MouseCursorSwingConstraints() {

        //
        defaults = getDefaults();
        
        //
        load();
        
    }
    
    private Properties getDefaults() {
        Properties defaultProperties = new Properties();
        
        defaultProperties.setProperty(KEY_REFRESH_INTERVAL_MIN, String.valueOf(5));
        defaultProperties.setProperty(KEY_REFRESH_INTERVAL_MAX, String.valueOf(50));
        defaultProperties.setProperty(KEY_SPEED_MIN, String.valueOf(1.0d));
        defaultProperties.setProperty(KEY_SPEED_MAX, String.valueOf(5.0d));
        defaultProperties.setProperty(KEY_DIVERSITY_MIN, String.valueOf(2.0d));
        defaultProperties.setProperty(KEY_DIVERSITY_MAX, String.valueOf(10.0d));
        defaultProperties.setProperty(KEY_CURSOR_RADIUS_MIN, String.valueOf(1));
        defaultProperties.setProperty(KEY_CURSOR_RADIUS_MAX, String.valueOf(200));
        
        //store();
        
        return defaultProperties;
    }
    
    private void load() {
        InputStream input = null;
        
        try {
            input = new FileInputStream(CONSTRAINTS_FILE_NAME);
            load(input);
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
    
    private void store() {
        OutputStream output = null;
        
        try {
            output = new FileOutputStream(CONSTRAINTS_FILE_NAME);
            store(output, null);
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
    
    public int getRefreshIntervalMin() {
        return Integer.parseInt(getProperty(KEY_REFRESH_INTERVAL_MIN));
    }

    public int getRefreshIntervalMax() {
        return Integer.parseInt(getProperty(KEY_REFRESH_INTERVAL_MAX));
    }
    
    public double getSpeedMin() {
        return Double.parseDouble(getProperty(KEY_SPEED_MIN));
    }
    
    public double getSpeedMax() {
        return Double.parseDouble(getProperty(KEY_SPEED_MAX));
    }
    
    public double getDiversityMin() {
        return Double.parseDouble(getProperty(KEY_DIVERSITY_MIN));
    }
    
    public double getDiversityMax() {
        return Double.parseDouble(getProperty(KEY_DIVERSITY_MAX));
    }
    
    public int getCursorRadiusMin() {
        return Integer.parseInt(getProperty(KEY_CURSOR_RADIUS_MIN));
    }

    public int getCursorRadiusMax() {
        return Integer.parseInt(getProperty(KEY_CURSOR_RADIUS_MAX));
    }

}
