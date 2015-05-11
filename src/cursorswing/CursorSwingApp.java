package cursorswing;

import javafx.application.Application;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CursorSwingApp extends Application {

    private Stage topLevelStage;
    private FXMLMainController controller;
    private CursorSwingTask mouseCursorSwingTask;
    private CursorSwingProperties mouseCursorSwingProperties;
    private CursorSwingConstraints mouseCursorSwingConstraints;
    private CursorShapePreview mouseCursorShapePreview;
    
    @Override
    public void init() throws Exception {
        super.init();
        
        mouseCursorSwingTask = null;
        mouseCursorSwingProperties = new CursorSwingProperties();
        mouseCursorSwingConstraints = new CursorSwingConstraints();
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        topLevelStage = stage;
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLMain.fxml"));
        Parent root = (Parent) loader.load();
        
        controller = loader.getController();
        controller.initializeView(this, mouseCursorSwingProperties, mouseCursorSwingConstraints);
        
        Scene scene = new Scene(root);
        
        topLevelStage.setTitle(Constants.APP_TITLE);
        topLevelStage.setResizable(false);
        topLevelStage.setScene(scene);
        topLevelStage.show();
    }

    @Override
    public void stop() throws Exception {
        cancelMouseCursorSwingTask();
        
        mouseCursorSwingProperties.store();
        
        super.stop();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    public void startMouseCursorSwingTask() {
        
        //
        cancelMouseCursorSwingTask();

        //
        if (mouseCursorSwingTask == null) {
            mouseCursorSwingTask = new CursorSwingTask(mouseCursorSwingProperties, topLevelStage);
            
            mouseCursorSwingTask.addEventHandler(WorkerStateEvent.ANY, new EventHandler<WorkerStateEvent>() {
                @Override
                public void handle(WorkerStateEvent event) {
                    handleMouseCursorSwingTaskEvents(event);
                }
            });
            
            Thread backgroundThread = new Thread(mouseCursorSwingTask);
            backgroundThread.setDaemon(true);
            backgroundThread.start();
        }
    }
    
    public void cancelMouseCursorSwingTask() {
        
        //
        if ((mouseCursorSwingTask != null) && !mouseCursorSwingTask.isDone()) {
            mouseCursorSwingTask.cancel();
        }
        
        //
        mouseCursorSwingTask = null;
        
    }
    
    private void handleMouseCursorSwingTaskEvents(WorkerStateEvent event) {
        if ((event.getEventType() == WorkerStateEvent.WORKER_STATE_SUCCEEDED)
            || (event.getEventType() == WorkerStateEvent.WORKER_STATE_FAILED)
            || (event.getEventType() == WorkerStateEvent.WORKER_STATE_CANCELLED)) {
            
            controller.stopMouseCursorSwingTask();
            
        }
    }
    
    public void showMouseCursorShapePreview() {
        if (mouseCursorShapePreview == null) {
            mouseCursorShapePreview = new CursorShapePreview(topLevelStage, mouseCursorSwingProperties);
        }
        else {
            mouseCursorShapePreview.update(mouseCursorSwingProperties);
        }
        
        mouseCursorShapePreview.show();
        topLevelStage.toFront();
    }
    
    public void updateMouseCursorShapePreview() {
        if ((mouseCursorShapePreview != null) && (mouseCursorShapePreview.isShowing())) {
            mouseCursorShapePreview.update(mouseCursorSwingProperties);
        }
    }
    
    public void hideMouseCursorShapePreview() {
        if ((mouseCursorShapePreview != null) && (mouseCursorShapePreview.isShowing())) {
            mouseCursorShapePreview.hide();
        }
    }

}
