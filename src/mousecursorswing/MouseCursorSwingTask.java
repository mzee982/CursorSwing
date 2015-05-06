package mousecursorswing;

import java.util.Date;
import java.util.Random;
import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class MouseCursorSwingTask extends Task<Void> {
    private final MouseCursorSwingProperties properties;
    private Random random;
    private Stage stage;
    private MouseCursorShape cursorShape;
    private AnimationLock animationLock;
    
    private class AnimationLock {
        public boolean animateX;
        public boolean animateY;
        
        public AnimationLock() {
            animateX = false;
            animateY = false;
        }
    }
    
    public MouseCursorSwingTask(MouseCursorSwingProperties properties, Stage ownerStage) {
        
        //
        this.properties = properties;
        animationLock = new AnimationLock();
        
        //
        random = new Random((new Date()).getTime());
        
        //
        createStage(ownerStage);
        
    }
    
    @Override
    protected Void call() throws Exception {
        if (!isCancelled()) doSwing();
        
        return null;
    }

    @Override
    protected void succeeded() {
        super.succeeded();
        destroyStage();
    }

    @Override
    protected void cancelled() {
        super.cancelled();
        destroyStage();
    }

    @Override
    protected void failed() {
        super.failed();
        destroyStage();
    }
    
    private void createStage(Stage ownerStage) {
        Group root = new Group();
        cursorShape = new MouseCursorShape(properties, false);
        cursorShape.setVisible(false);
        root.getChildren().add(cursorShape);
        
        Scene scene = new Scene(root, Color.TRANSPARENT);
        
        stage = new Stage(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.setTitle(Constants.APP_TITLE);
        stage.setResizable(false);
        stage.setFullScreen(true);
        stage.initModality(Modality.NONE);
        stage.initOwner(ownerStage);
        
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ESCAPE) {
                    cancel();
                }
            }
        });
        stage.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> obsValue, Boolean oldValue, Boolean newValue) {
                if (!newValue) cancel();
            }
        });
        
        ((Stage) stage.getOwner()).setIconified(true);
        stage.show();
    }
    
    private void destroyStage() {
        if (stage != null) {
            ((Stage) stage.getOwner()).setIconified(false);
            stage.hide();
        }
    }
    
    private void doSwing() {
            
        // Initialize pass parameters
        int xa = properties.getMinX();
        int xb = properties.getMaxX();
        int ya = properties.getMinY();
        int yb = properties.getMaxY();
        long xn = Math.round((xb - xa) / properties.getMaxSpeedX());
        long yn = Math.round((yb - ya) / properties.getMaxSpeedY());
        
        // Initialize pass control variables
        int xdirection = 1;
        int ydirection = 1;
        boolean needToAnimateX = (xa != xb);
        boolean needToAnimateY = (ya != yb);

        // Initialize position
        cursorShape.setTranslateX(xa);
        cursorShape.setTranslateY(ya);
        cursorShape.setVisible(true);
        
        // Animation
        TranslateTransition transitionX = null;
        TranslateTransition transitionY = null;

        while (!isCancelled() && (needToAnimateX || needToAnimateY)) {

            // Animate
            synchronized (animationLock) {
                
                // Animate X
                if (!animationLock.animateX && needToAnimateX) {
                    transitionX = new TranslateTransition(Duration.millis(properties.getRefreshInterval() * xn), cursorShape);
                    transitionX.setFromX(xa);
                    transitionX.setToX(xb);
                    transitionX.setCycleCount(1);
                    transitionX.setAutoReverse(false);
                    transitionX.setOnFinished(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent t) {
                            synchronized (animationLock) {
                                animationLock.animateX = false;
                                animationLock.notify();
                            }
                        }
                    });
                    animationLock.animateX = true;
                    transitionX.play();
                }
                
                // Animate Y
                if (!animationLock.animateY && needToAnimateY) {
                    transitionY = new TranslateTransition(Duration.millis(properties.getRefreshInterval() * yn), cursorShape);
                    transitionY.setFromY(ya);
                    transitionY.setToY(yb);
                    transitionY.setCycleCount(1);
                    transitionY.setAutoReverse(false);
                    transitionY.setOnFinished(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent t) {
                            synchronized (animationLock) {
                                animationLock.animateY = false;
                                animationLock.notify();
                            }
                        }
                    });
                    animationLock.animateY = true;
                    transitionY.play();
                }
                
            }
            
            synchronized (animationLock) {
                while ((animationLock.animateX || !needToAnimateX) && (animationLock.animateY || !needToAnimateY)) {
                    try {
                        animationLock.wait();
                    }
                    catch (InterruptedException e) {
                        if (isCancelled()) break;
                    }
                }
                    
                // X
                if (!animationLock.animateX && needToAnimateX) {
                    xdirection *= -1;
                    xa = xb;
                    int xr = (int) Math.round((double) (properties.getMaxX() - properties.getMinX()) / properties.getDiversityX() * random.nextDouble());
                    xb = (xdirection == 1) ? properties.getMaxX() - xr : properties.getMinX() + xr;
                    double xspeed = properties.getMinSpeedX() + (properties.getMaxSpeedX() - properties.getMinSpeedX()) * random.nextDouble();
                    xn = Math.round(Math.abs(xb - xa) / xspeed);
                }

                // Y
                if (!animationLock.animateY && needToAnimateY) {
                    ydirection *= -1;
                    ya = yb;
                    int yr = (int) Math.round((double) (properties.getMaxY() - properties.getMinY()) / properties.getDiversityY() * random.nextDouble());
                    yb = (ydirection == 1) ? properties.getMaxY() - yr : properties.getMinY() + yr;
                    double yspeed = properties.getMinSpeedY() + (properties.getMaxSpeedY() - properties.getMinSpeedY()) * random.nextDouble();
                    yn = Math.round(Math.abs(yb - ya) / yspeed);
                }
                    
            }
            
        }
        
        //
        if ((transitionX != null) && (transitionX.getStatus() != Animation.Status.STOPPED)) transitionX.stop();
        if ((transitionY != null) && (transitionY.getStatus() != Animation.Status.STOPPED)) transitionY.stop();
        
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
