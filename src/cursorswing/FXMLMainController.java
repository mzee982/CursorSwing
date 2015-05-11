package cursorswing;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class FXMLMainController implements Initializable {
    
    private MouseCursorSwingApp mouseCursorSwingApp;
    private MouseCursorSwingProperties mouseCursorSwingProperties;
    private MouseCursorSwingConstraints mouseCursorSwingConstraints;
    private Timer mouseCursorShapePreviewTimer;
    private TimerTask mouseCursorShapePreviewTimerTask;
    
    @FXML
    private Tab tabCursor;
    @FXML
    private VBox vBoxControls;
    @FXML
    private Button buttonPreview;
    @FXML
    private Button buttonResetSwing;
    @FXML
    private Button buttonResetCursor;
    @FXML
    private Button buttonStart;
    @FXML
    private Button buttonStop;
    @FXML
    private Label labelRefreshIntervalValue;
    @FXML
    private Slider sliderRefreshInterval;
    @FXML
    private Label labelMinSpeedXValue;
    @FXML
    private Label labelMaxSpeedXValue;
    @FXML
    private Slider sliderMinSpeedX;
    @FXML
    private Slider sliderMaxSpeedX;
    @FXML
    private Label labelMinSpeedYValue;
    @FXML
    private Label labelMaxSpeedYValue;
    @FXML
    private Slider sliderMinSpeedY;
    @FXML
    private Slider sliderMaxSpeedY;
    @FXML
    private Label labelMinXValue;
    @FXML
    private Label labelMaxXValue;
    @FXML
    private Slider sliderMinX;
    @FXML
    private Slider sliderMaxX;
    @FXML
    private Label labelMinYValue;
    @FXML
    private Label labelMaxYValue;
    @FXML
    private Slider sliderMinY;
    @FXML
    private Slider sliderMaxY;
    @FXML
    private Label labelDiversityXValue;
    @FXML
    private Label labelDiversityYValue;
    @FXML
    private Slider sliderDiversityX;
    @FXML
    private Slider sliderDiversityY;
    @FXML
    private Label labelCursorRadiusValue;
    @FXML
    private Slider sliderCursorRadius;
    @FXML
    private Label labelCursorGradientStopOffset0Value;
    @FXML
    private Slider sliderCursorGradientStopOffset0;
    @FXML
    private Label labelCursorGradientStopOffset1Value;
    @FXML
    private Slider sliderCursorGradientStopOffset1;
    @FXML
    private Label labelCursorGradientStopOffset2Value;
    @FXML
    private Slider sliderCursorGradientStopOffset2;
    @FXML
    private Label labelCursorGradientStopOffset3Value;
    @FXML
    private Slider sliderCursorGradientStopOffset3;
    @FXML
    private ColorPicker colorPickerCursorGradientStopColor0;
    @FXML
    private Label labelCursorGradientStopColor0Value;
    @FXML
    private ColorPicker colorPickerCursorGradientStopColor1;
    @FXML
    private Label labelCursorGradientStopColor1Value;
    @FXML
    private ColorPicker colorPickerCursorGradientStopColor2;
    @FXML
    private Label labelCursorGradientStopColor2Value;
    @FXML
    private ColorPicker colorPickerCursorGradientStopColor3;
    @FXML
    private Label labelCursorGradientStopColor3Value;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {}
    
    public void initializeView(MouseCursorSwingApp app, MouseCursorSwingProperties properties, MouseCursorSwingConstraints constraints) {

        //
        this.mouseCursorSwingApp = app;
        this.mouseCursorSwingProperties = properties;
        this.mouseCursorSwingConstraints = constraints;
        this.mouseCursorShapePreviewTimer = new Timer(true);
        
        //
        initializeValues();
        
        //
        sliderRefreshInterval.valueProperty().addListener(
            new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> obsValue, Number oldValue, Number newValue) {
                    if (!vBoxControls.isDisabled()) handleSliderRefreshIntervalChange(newValue);
                }
            }
        );
        
        sliderMinSpeedX.valueProperty().addListener(
            new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> obsValue, Number oldValue, Number newValue) {
                    if (!vBoxControls.isDisabled()) handleSliderMinSpeedXChange(newValue);
                }
            }
        );
        
        sliderMaxSpeedX.valueProperty().addListener(
            new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> obsValue, Number oldValue, Number newValue) {
                    if (!vBoxControls.isDisabled()) handleSliderMaxSpeedXChange(newValue);
                }
            }
        );
        
        sliderMinSpeedY.valueProperty().addListener(
            new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> obsValue, Number oldValue, Number newValue) {
                    if (!vBoxControls.isDisabled()) handleSliderMinSpeedYChange(newValue);
                }
            }
        );
        
        sliderMaxSpeedY.valueProperty().addListener(
            new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> obsValue, Number oldValue, Number newValue) {
                    if (!vBoxControls.isDisabled()) handleSliderMaxSpeedYChange(newValue);
                }
            }
        );
        
        sliderMinX.valueProperty().addListener(
            new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> obsValue, Number oldValue, Number newValue) {
                    if (!vBoxControls.isDisabled()) handleSliderMinXChange(newValue);
                }
            }
        );
        
        sliderMaxX.valueProperty().addListener(
            new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> obsValue, Number oldValue, Number newValue) {
                    if (!vBoxControls.isDisabled()) handleSliderMaxXChange(newValue);
                }
            }
        );

        sliderMinY.valueProperty().addListener(
            new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> obsValue, Number oldValue, Number newValue) {
                    if (!vBoxControls.isDisabled()) handleSliderMinYChange(newValue);
                }
            }
        );
        
        sliderMaxY.valueProperty().addListener(
            new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> obsValue, Number oldValue, Number newValue) {
                    if (!vBoxControls.isDisabled()) handleSliderMaxYChange(newValue);
                }
            }
        );
        
        sliderDiversityX.valueProperty().addListener(
            new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> obsValue, Number oldValue, Number newValue) {
                    if (!vBoxControls.isDisabled()) handleDiversityXChange(newValue);
                }
            }
        );
        
        sliderDiversityY.valueProperty().addListener(
            new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> obsValue, Number oldValue, Number newValue) {
                    if (!vBoxControls.isDisabled()) handleDiversityYChange(newValue);
                }
            }
        );
        
        sliderCursorRadius.valueProperty().addListener(
            new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> obsValue, Number oldValue, Number newValue) {
                    if (!vBoxControls.isDisabled()) handleSliderCursorRadiusChange(newValue);
                }
            }
        );
        
        sliderCursorGradientStopOffset0.valueProperty().addListener(
            new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> obsValue, Number oldValue, Number newValue) {
                    if (!vBoxControls.isDisabled()) handleSliderCursorGradientStopOffset0Change(newValue);
                }
            }
        );
        
        sliderCursorGradientStopOffset1.valueProperty().addListener(
            new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> obsValue, Number oldValue, Number newValue) {
                    if (!vBoxControls.isDisabled()) handleSliderCursorGradientStopOffset1Change(newValue);
                }
            }
        );
        
        sliderCursorGradientStopOffset2.valueProperty().addListener(
            new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> obsValue, Number oldValue, Number newValue) {
                    if (!vBoxControls.isDisabled()) handleSliderCursorGradientStopOffset2Change(newValue);
                }
            }
        );
        
        sliderCursorGradientStopOffset3.valueProperty().addListener(
            new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> obsValue, Number oldValue, Number newValue) {
                    if (!vBoxControls.isDisabled()) handleSliderCursorGradientStopOffset3Change(newValue);
                }
            }
        );
        
    }
    
    private void initializeValues() {
        
        sliderRefreshInterval.setMin(mouseCursorSwingConstraints.getRefreshIntervalMin());
        sliderRefreshInterval.setMax(mouseCursorSwingConstraints.getRefreshIntervalMax());
        sliderRefreshInterval.setValue(mouseCursorSwingProperties.getRefreshInterval());
        labelRefreshIntervalValue.setText(String.valueOf(mouseCursorSwingProperties.getRefreshInterval()));
        
        sliderMinSpeedX.setMin(mouseCursorSwingConstraints.getSpeedMin());
        sliderMinSpeedX.setMax(mouseCursorSwingProperties.getMaxSpeedX());
        sliderMinSpeedX.setValue(mouseCursorSwingProperties.getMinSpeedX());
        labelMinSpeedXValue.setText(String.format("%.1f", mouseCursorSwingProperties.getMinSpeedX()));
        
        sliderMaxSpeedX.setMin(mouseCursorSwingProperties.getMinSpeedX());
        sliderMaxSpeedX.setMax(mouseCursorSwingConstraints.getSpeedMax());
        sliderMaxSpeedX.setValue(mouseCursorSwingProperties.getMaxSpeedX());
        labelMaxSpeedXValue.setText(String.format("%.1f", mouseCursorSwingProperties.getMaxSpeedX()));
        
        sliderMinSpeedY.setMin(mouseCursorSwingConstraints.getSpeedMin());
        sliderMinSpeedY.setMax(mouseCursorSwingProperties.getMaxSpeedY());
        sliderMinSpeedY.setValue(mouseCursorSwingProperties.getMinSpeedY());
        labelMinSpeedYValue.setText(String.format("%.1f", mouseCursorSwingProperties.getMinSpeedY()));
        
        sliderMaxSpeedY.setMin(mouseCursorSwingProperties.getMinSpeedY());
        sliderMaxSpeedY.setMax(mouseCursorSwingConstraints.getSpeedMax());
        sliderMaxSpeedY.setValue(mouseCursorSwingProperties.getMaxSpeedY());
        labelMaxSpeedYValue.setText(String.format("%.1f", mouseCursorSwingProperties.getMaxSpeedY()));
        
        sliderMinX.setMin(0);
        sliderMinX.setMax(mouseCursorSwingProperties.getMaxX());
        sliderMinX.setValue(mouseCursorSwingProperties.getMinX());
        labelMinXValue.setText(String.valueOf(mouseCursorSwingProperties.getMinX()));
        
        sliderMaxX.setMin(mouseCursorSwingProperties.getMinX());
        sliderMaxX.setMax(mouseCursorSwingProperties.getScreenWidth());
        sliderMaxX.setValue(mouseCursorSwingProperties.getMaxX());
        labelMaxXValue.setText(String.valueOf(mouseCursorSwingProperties.getMaxX()));
        
        sliderMinY.setMin(0);
        sliderMinY.setMax(mouseCursorSwingProperties.getMaxY());
        sliderMinY.setValue(mouseCursorSwingProperties.getMinY());
        labelMinYValue.setText(String.valueOf(mouseCursorSwingProperties.getMinY()));
        
        sliderMaxY.setMin(mouseCursorSwingProperties.getMinY());
        sliderMaxY.setMax(mouseCursorSwingProperties.getScreenHeight());
        sliderMaxY.setValue(mouseCursorSwingProperties.getMaxY());
        labelMaxYValue.setText(String.valueOf(mouseCursorSwingProperties.getMaxY()));
        
        sliderDiversityX.setMin(mouseCursorSwingConstraints.getDiversityMin());
        sliderDiversityX.setMax(mouseCursorSwingConstraints.getDiversityMax());
        sliderDiversityX.setValue(mouseCursorSwingProperties.getDiversityX());
        labelDiversityXValue.setText(String.format("%.1f", mouseCursorSwingProperties.getDiversityX()));
        
        sliderDiversityY.setMin(mouseCursorSwingConstraints.getDiversityMin());
        sliderDiversityY.setMax(mouseCursorSwingConstraints.getDiversityMax());
        sliderDiversityY.setValue(mouseCursorSwingProperties.getDiversityY());
        labelDiversityYValue.setText(String.format("%.1f", mouseCursorSwingProperties.getDiversityY()));
        
        sliderCursorRadius.setMin(mouseCursorSwingConstraints.getCursorRadiusMin());
        sliderCursorRadius.setMax(mouseCursorSwingConstraints.getCursorRadiusMax());
        sliderCursorRadius.setValue(mouseCursorSwingProperties.getCursorRadius());
        labelCursorRadiusValue.setText(String.valueOf(mouseCursorSwingProperties.getCursorRadius()));
        
        sliderCursorGradientStopOffset0.setMin(0);
        sliderCursorGradientStopOffset0.setMax(mouseCursorSwingProperties.getCursorGradientStopOffset1());
        sliderCursorGradientStopOffset0.setValue(mouseCursorSwingProperties.getCursorGradientStopOffset0());
        labelCursorGradientStopOffset0Value.setText(String.valueOf(mouseCursorSwingProperties.getCursorGradientStopOffset0()));
        
        sliderCursorGradientStopOffset1.setMin(mouseCursorSwingProperties.getCursorGradientStopOffset0());
        sliderCursorGradientStopOffset1.setMax(mouseCursorSwingProperties.getCursorGradientStopOffset2());
        sliderCursorGradientStopOffset1.setValue(mouseCursorSwingProperties.getCursorGradientStopOffset1());
        labelCursorGradientStopOffset1Value.setText(String.valueOf(mouseCursorSwingProperties.getCursorGradientStopOffset1()));
        
        sliderCursorGradientStopOffset2.setMin(mouseCursorSwingProperties.getCursorGradientStopOffset1());
        sliderCursorGradientStopOffset2.setMax(mouseCursorSwingProperties.getCursorGradientStopOffset3());
        sliderCursorGradientStopOffset2.setValue(mouseCursorSwingProperties.getCursorGradientStopOffset2());
        labelCursorGradientStopOffset2Value.setText(String.valueOf(mouseCursorSwingProperties.getCursorGradientStopOffset2()));
        
        sliderCursorGradientStopOffset3.setMin(mouseCursorSwingProperties.getCursorGradientStopOffset2());
        sliderCursorGradientStopOffset3.setMax(mouseCursorSwingProperties.getCursorRadius());
        sliderCursorGradientStopOffset3.setValue(mouseCursorSwingProperties.getCursorGradientStopOffset3());
        labelCursorGradientStopOffset3Value.setText(String.valueOf(mouseCursorSwingProperties.getCursorGradientStopOffset3()));
        
        colorPickerCursorGradientStopColor0.setValue(mouseCursorSwingProperties.getCursorGradientStopColor0());
        colorPickerCursorGradientStopColor0.fireEvent(new ActionEvent());
        labelCursorGradientStopColor0Value.setText(Utils.colorToHexString(mouseCursorSwingProperties.getCursorGradientStopColor0()));
        
        colorPickerCursorGradientStopColor1.setValue(mouseCursorSwingProperties.getCursorGradientStopColor1());
        colorPickerCursorGradientStopColor1.fireEvent(new ActionEvent());
        labelCursorGradientStopColor1Value.setText(Utils.colorToHexString(mouseCursorSwingProperties.getCursorGradientStopColor1()));
        
        colorPickerCursorGradientStopColor2.setValue(mouseCursorSwingProperties.getCursorGradientStopColor2());
        colorPickerCursorGradientStopColor2.fireEvent(new ActionEvent());
        labelCursorGradientStopColor2Value.setText(Utils.colorToHexString(mouseCursorSwingProperties.getCursorGradientStopColor2()));
        
        colorPickerCursorGradientStopColor3.setValue(mouseCursorSwingProperties.getCursorGradientStopColor3());
        colorPickerCursorGradientStopColor3.fireEvent(new ActionEvent());
        labelCursorGradientStopColor3Value.setText(Utils.colorToHexString(mouseCursorSwingProperties.getCursorGradientStopColor3()));
        
    }
    
    private void updateMouseCursorSwingPreview() {
        if (mouseCursorShapePreviewTimerTask != null) {
            mouseCursorShapePreviewTimerTask.cancel();
            mouseCursorShapePreviewTimerTask = null;
        }
        
        mouseCursorShapePreviewTimerTask = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        mouseCursorSwingApp.updateMouseCursorShapePreview();
                    }
                });
            }
        };
        
        mouseCursorShapePreviewTimer.schedule(mouseCursorShapePreviewTimerTask, 500);
    }
    
    private void startMouseCursorSwingTask() {
        vBoxControls.setDisable(true);
        mouseCursorSwingApp.startMouseCursorSwingTask();
    }
    
    public void stopMouseCursorSwingTask() {
        mouseCursorSwingApp.cancelMouseCursorSwingTask();
        vBoxControls.setDisable(false);
    }
    
    @FXML
    private void handleKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ESCAPE) {
            stopMouseCursorSwingTask();
        }
    }
    
    @FXML
    private void handleTabCursorSelectionChanged(Event event) {
        if (tabCursor.isSelected()) {
            mouseCursorSwingApp.showMouseCursorShapePreview();
        }
        else {
            mouseCursorSwingApp.hideMouseCursorShapePreview();
        }
    }
    
    @FXML
    private void handleButtonPreviewAction(ActionEvent event) {
        mouseCursorSwingApp.showMouseCursorShapePreview();
    }
    
    @FXML
    private void handleButtonResetSwingAction(ActionEvent event) {
        boolean isDisabled = vBoxControls.isDisabled();
        
        vBoxControls.setDisable(true);
        mouseCursorSwingProperties.resetToDefaultsSwing();
        initializeValues();
        vBoxControls.setDisable(isDisabled);
    }

    @FXML
    private void handleButtonResetCursorAction(ActionEvent event) {
        boolean isDisabled = vBoxControls.isDisabled();
        
        vBoxControls.setDisable(true);
        mouseCursorSwingProperties.resetToDefaultsCursor();
        initializeValues();
        vBoxControls.setDisable(isDisabled);
    }

    @FXML
    private void handleButtonStartAction(ActionEvent event) {
        startMouseCursorSwingTask();
    }
    
    @FXML
    private void handleButtonStopAction(ActionEvent event) {
        stopMouseCursorSwingTask();
    }
    
    private void handleSliderRefreshIntervalChange(Number value) {
        int refreshInterval = value.intValue();
        mouseCursorSwingProperties.setRefreshInterval(refreshInterval);
        labelRefreshIntervalValue.setText(String.valueOf(refreshInterval));
    }
    
    private void handleSliderMinSpeedXChange(Number value) {
        double doubleValue = new BigDecimal(value.doubleValue()).setScale(1, RoundingMode.HALF_UP).doubleValue();
        sliderMaxSpeedX.setMin(doubleValue);
        mouseCursorSwingProperties.setMinSpeedX(doubleValue);
        labelMinSpeedXValue.setText(String.valueOf(doubleValue));
    }
    
    private void handleSliderMaxSpeedXChange(Number value) {
        double doubleValue = new BigDecimal(value.doubleValue()).setScale(1, RoundingMode.HALF_UP).doubleValue();
        sliderMinSpeedX.setMax(doubleValue);
        mouseCursorSwingProperties.setMaxSpeedX(doubleValue);
        labelMaxSpeedXValue.setText(String.valueOf(doubleValue));
    }
    
    private void handleSliderMinSpeedYChange(Number value) {
        double doubleValue = new BigDecimal(value.doubleValue()).setScale(1, RoundingMode.HALF_UP).doubleValue();
        sliderMaxSpeedY.setMin(doubleValue);
        mouseCursorSwingProperties.setMinSpeedY(doubleValue);
        labelMinSpeedYValue.setText(String.valueOf(doubleValue));
    }
    
    private void handleSliderMaxSpeedYChange(Number value) {
        double doubleValue = new BigDecimal(value.doubleValue()).setScale(1, RoundingMode.HALF_UP).doubleValue();
        sliderMinSpeedY.setMax(doubleValue);
        mouseCursorSwingProperties.setMaxSpeedY(doubleValue);
        labelMaxSpeedYValue.setText(String.valueOf(doubleValue));
    }
    
    private void handleSliderMinXChange(Number value) {
        int intValue = value.intValue();
        sliderMaxX.setMin(intValue);
        mouseCursorSwingProperties.setMinX(intValue);
        labelMinXValue.setText(String.valueOf(intValue));
    }
    
    private void handleSliderMaxXChange(Number value) {
        int intValue = value.intValue();
        sliderMinX.setMax(intValue);
        mouseCursorSwingProperties.setMaxX(intValue);
        labelMaxXValue.setText(String.valueOf(intValue));
    }
    
    private void handleSliderMinYChange(Number value) {
        int intValue = value.intValue();
        sliderMaxY.setMin(intValue);
        mouseCursorSwingProperties.setMinY(intValue);
        labelMinYValue.setText(String.valueOf(intValue));
    }
    
    private void handleSliderMaxYChange(Number value) {
        int intValue = value.intValue();
        sliderMinY.setMax(intValue);
        mouseCursorSwingProperties.setMaxY(intValue);
        labelMaxYValue.setText(String.valueOf(intValue));
    }
    
    private void handleDiversityXChange(Number value) {
        double doubleValue = new BigDecimal(value.doubleValue()).setScale(1, RoundingMode.HALF_UP).doubleValue();
        mouseCursorSwingProperties.setDiversityX(doubleValue);
        labelDiversityXValue.setText(String.valueOf(doubleValue));
    }
    
    private void handleDiversityYChange(Number value) {
        double doubleValue = new BigDecimal(value.doubleValue()).setScale(1, RoundingMode.HALF_UP).doubleValue();
        mouseCursorSwingProperties.setDiversityY(doubleValue);
        labelDiversityYValue.setText(String.valueOf(doubleValue));
    }
    
    private void handleSliderCursorRadiusChange(Number value) {
        int cursorRadius = value.intValue();
        mouseCursorSwingProperties.setCursorRadius(cursorRadius);
        labelCursorRadiusValue.setText(String.valueOf(cursorRadius));
        updateMouseCursorSwingPreview();
    }
    
    private void handleSliderCursorGradientStopOffset0Change(Number value) {
        int cursorGradientStopOffset0 = value.intValue();
        sliderCursorGradientStopOffset1.setMin(cursorGradientStopOffset0);
        mouseCursorSwingProperties.setCursorGradientStopOffset0(cursorGradientStopOffset0);
        labelCursorGradientStopOffset0Value.setText(String.valueOf(cursorGradientStopOffset0));
        updateMouseCursorSwingPreview();
    }
    
    private void handleSliderCursorGradientStopOffset1Change(Number value) {
        int cursorGradientStopOffset1 = value.intValue();
        sliderCursorGradientStopOffset0.setMax(cursorGradientStopOffset1);
        sliderCursorGradientStopOffset2.setMin(cursorGradientStopOffset1);
        mouseCursorSwingProperties.setCursorGradientStopOffset1(cursorGradientStopOffset1);
        labelCursorGradientStopOffset1Value.setText(String.valueOf(cursorGradientStopOffset1));
        updateMouseCursorSwingPreview();
    }
    
    private void handleSliderCursorGradientStopOffset2Change(Number value) {
        int cursorGradientStopOffset2 = value.intValue();
        sliderCursorGradientStopOffset1.setMax(cursorGradientStopOffset2);
        sliderCursorGradientStopOffset3.setMin(cursorGradientStopOffset2);
        mouseCursorSwingProperties.setCursorGradientStopOffset2(cursorGradientStopOffset2);
        labelCursorGradientStopOffset2Value.setText(String.valueOf(cursorGradientStopOffset2));
        updateMouseCursorSwingPreview();
    }
    
    private void handleSliderCursorGradientStopOffset3Change(Number value) {
        int cursorGradientStopOffset3 = value.intValue();
        sliderCursorGradientStopOffset2.setMax(cursorGradientStopOffset3);
        mouseCursorSwingProperties.setCursorGradientStopOffset3(cursorGradientStopOffset3);
        labelCursorGradientStopOffset3Value.setText(String.valueOf(cursorGradientStopOffset3));
        updateMouseCursorSwingPreview();
    }
    
    @FXML
    private void handleColorPickerCursorGradientStopColor0Action(ActionEvent event) {
        Color color = colorPickerCursorGradientStopColor0.getValue();
        mouseCursorSwingProperties.setCursorGradientStopColor0(color);
        labelCursorGradientStopColor0Value.setText(Utils.colorToHexString(color));
        updateMouseCursorSwingPreview();
    }
    
    @FXML
    private void handleColorPickerCursorGradientStopColor1Action(ActionEvent event) {
        Color color = colorPickerCursorGradientStopColor1.getValue();
        mouseCursorSwingProperties.setCursorGradientStopColor1(color);
        labelCursorGradientStopColor1Value.setText(Utils.colorToHexString(color));
        updateMouseCursorSwingPreview();
    }
    
    @FXML
    private void handleColorPickerCursorGradientStopColor2Action(ActionEvent event) {
        Color color = colorPickerCursorGradientStopColor2.getValue();
        mouseCursorSwingProperties.setCursorGradientStopColor2(color);
        labelCursorGradientStopColor2Value.setText(Utils.colorToHexString(color));
        updateMouseCursorSwingPreview();
    }
    
    @FXML
    private void handleColorPickerCursorGradientStopColor3Action(ActionEvent event) {
        Color color = colorPickerCursorGradientStopColor3.getValue();
        mouseCursorSwingProperties.setCursorGradientStopColor3(color);
        labelCursorGradientStopColor3Value.setText(Utils.colorToHexString(color));
        updateMouseCursorSwingPreview();
    }
    
}
