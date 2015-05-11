package cursorswing;

import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class MouseCursorShapePreview extends Stage {
    
    public MouseCursorShapePreview(Stage ownerStage, MouseCursorSwingProperties properties) {
        super(StageStyle.UTILITY);
        
        Group root = new Group();
        MouseCursorShape cursorShape = new MouseCursorShape(properties, true);
        root.getChildren().add(cursorShape);
        
        Scene scene = new Scene(root, Color.WHITE);
        
        setScene(scene);
        setTitle(Constants.PREVIEW_TITLE);
        setResizable(false);
        initModality(Modality.NONE);
        
        initOwner(ownerStage);
        
        setOnShowing(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                setX(getOwner().getX() + getOwner().getWidth() + 20);
                setY(getOwner().getY());
            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ESCAPE) {
                    hide();
                }
            }
        });
    }
    
    public void update(MouseCursorSwingProperties properties) {
        Group root = ((Group) getScene().getRoot());
        root.getChildren().clear();
        MouseCursorShape cursorShape = new MouseCursorShape(properties, true);
        root.getChildren().add(cursorShape);
        
        sizeToScene();
    }
    
}
