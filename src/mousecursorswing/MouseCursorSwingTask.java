package mousecursorswing;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.Date;
import java.util.Random;
import javafx.concurrent.Task;

public class MouseCursorSwingTask extends Task<Void> {
    private final MouseCursorSwingProperties properties;
    private Robot robot;
    private Random random;
    
    public MouseCursorSwingTask(MouseCursorSwingProperties properties) {
        this.properties = properties;
        
        init();
    }
    
    private void init() {
        
        try {
            robot = new Robot();
        }
        catch (AWTException e) {
            e.printStackTrace();
        }
        
        random = new Random((new Date()).getTime());
        
    }
    
    @Override
    protected Void call() throws Exception {
        if (!isCancelled()) doSwing();
        
        return null;
    }
    
    private void doSwing() {
            
            // Initialize pass control variables
            int xi = 0;
            int yi = 0;
            int xdirection = 1;
            int ydirection = 1;
            
            // Initial pass parameters
            int xa = properties.getMinX();
            int xb = properties.getMaxX();
            int ya = properties.getMinY();
            int yb = properties.getMaxY();
            long xn = Math.round((xb - xa) / properties.getMaxSpeedX());
            long yn = Math.round((yb - ya) / properties.getMaxSpeedY());
            
            while (!isCancelled()) {

            	// Move mouse cursor
            	long x = interpolate(xi, xn, xa, xb);
            	long y = interpolate(yi, yn, ya, yb);
            	robot.mouseMove((int) x, (int) y);
            	
            	// X
            	if (xi < xn){
                    xi++;
            	}
            	else if (xn > 0) {
                    xi = 0;
                    xdirection *= -1;
                    xa = xb;
                    int xr = (int) Math.round((double) (properties.getMaxX() - properties.getMinX()) / properties.getDiversityX() * random.nextDouble());
                    xb = (xdirection == 1) ? properties.getMaxX() - xr : properties.getMinX() + xr;
                    double xspeed = properties.getMinSpeedX() + (properties.getMaxSpeedX() - properties.getMinSpeedX()) * random.nextDouble();
                    xn = Math.round(Math.abs(xb - xa) / xspeed);
            	}
            	
            	// Y
            	if (yi < yn){
                    yi++;
            	}
            	else if (yn > 0) {
                    yi = 0;
                    ydirection *= -1;
                    ya = yb;
                    int yr = (int) Math.round((double) (properties.getMaxY() - properties.getMinY()) / properties.getDiversityY() * random.nextDouble());
                    yb = (ydirection == 1) ? properties.getMaxY() - yr : properties.getMinY() + yr;
                    double yspeed = properties.getMinSpeedY() + (properties.getMaxSpeedY() - properties.getMinSpeedY()) * random.nextDouble();
                    yn = Math.round(Math.abs(yb - ya) / yspeed);
            	}
            	
            	// Sleep
            	try {
                    Thread.sleep(properties.getRefreshInterval());
                }
                catch (InterruptedException e) {
                    if (isCancelled()) break;
                }
                
            }
        
    }
    
    private long interpolate(int i, long n, int a, int b) {
            double v = (n > 0) ? (double) i / n : 0;
            v = interpolationSmoothstep(v);
            double x = a * (1 - v) + b * v;

            return Math.round(x);
    }

    private double interpolationSmoothstep(double x) {
            return x * x * (3 - 2 * x);
    }
    
}
