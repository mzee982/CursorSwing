package cursorswing;

import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;

public class MouseCursorShape extends Circle {
    
    public MouseCursorShape(MouseCursorSwingProperties properties, boolean aligned) {
        super(properties.getCursorRadius());
        
        if (aligned) {
            setCenterX(properties.getCursorRadius());
            setCenterY(properties.getCursorRadius());
        }
        else {
            setCenterX(0);
            setCenterY(0);
        }
        
        double stopOffset0 = (double) properties.getCursorGradientStopOffset0() / properties.getCursorRadius();
        double stopOffset1 = (double) properties.getCursorGradientStopOffset1() / properties.getCursorRadius();
        double stopOffset2 = (double) properties.getCursorGradientStopOffset2() / properties.getCursorRadius();
        double stopOffset3 = (double) properties.getCursorGradientStopOffset3() / properties.getCursorRadius();
        
        RadialGradient gradient = new RadialGradient(0, 0, 0.5d, 0.5d, 0.5d, true, CycleMethod.NO_CYCLE, 
                                    new Stop(stopOffset0, properties.getCursorGradientStopColor0()), 
                                    new Stop(stopOffset1, properties.getCursorGradientStopColor1()), 
                                    new Stop(stopOffset2, properties.getCursorGradientStopColor2()), 
                                    new Stop(stopOffset3, properties.getCursorGradientStopColor3()));
        
        setFill(gradient);
    }
    
}
