package mousecursorswing;

import javafx.application.Application;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MouseCursorSwingApp extends Application {

    private Stage topLevelStage;
    private FXMLMainController controller;
    private MouseCursorSwingTask mouseCursorSwingTask;
    private MouseCursorSwingProperties mouseCursorSwingProperties;
    private MouseCursorSwingConstraints mouseCursorSwingConstraints;
    
    @Override
    public void init() throws Exception {
        super.init();
        
        mouseCursorSwingTask = null;
        mouseCursorSwingProperties = new MouseCursorSwingProperties();
        mouseCursorSwingConstraints = new MouseCursorSwingConstraints();
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
            mouseCursorSwingTask = new MouseCursorSwingTask(mouseCursorSwingProperties, topLevelStage);
            
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

}
