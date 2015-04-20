package mousecursorswing;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

public class FXMLMainController implements Initializable {
    
    private MouseCursorSwingApp mouseCursorSwingApp;
    private MouseCursorSwingProperties mouseCursorSwingProperties;
    private MouseCursorSwingConstraints mouseCursorSwingConstraints;
    
    @FXML
    private VBox vBoxControls;
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
    private Button buttonReset;
    @FXML
    private Button buttonStart;
    @FXML
    private Button buttonStop;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {}
    
    public void initializeView(MouseCursorSwingApp app, MouseCursorSwingProperties properties, MouseCursorSwingConstraints constraints) {

        //
        this.mouseCursorSwingApp = app;
        this.mouseCursorSwingProperties = properties;
        this.mouseCursorSwingConstraints = constraints;
        
        //
        initializeValues();
        
        //
        sliderRefreshInterval.valueProperty().addListener(
            new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> obsValue, Number oldValue, Number newValue) {
                    handleSliderRefreshIntervalChange(newValue);
                }
            }
        );
        
        sliderMinSpeedX.valueProperty().addListener(
            new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> obsValue, Number oldValue, Number newValue) {
                    handleSliderMinSpeedXChange(newValue);
                }
            }
        );
        
        sliderMaxSpeedX.valueProperty().addListener(
            new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> obsValue, Number oldValue, Number newValue) {
                    handleSliderMaxSpeedXChange(newValue);
                }
            }
        );
        
        sliderMinSpeedY.valueProperty().addListener(
            new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> obsValue, Number oldValue, Number newValue) {
                    handleSliderMinSpeedYChange(newValue);
                }
            }
        );
        
        sliderMaxSpeedY.valueProperty().addListener(
            new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> obsValue, Number oldValue, Number newValue) {
                    handleSliderMaxSpeedYChange(newValue);
                }
            }
        );
        
        sliderMinX.valueProperty().addListener(
            new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> obsValue, Number oldValue, Number newValue) {
                    handleSliderMinXChange(newValue);
                }
            }
        );
        
        sliderMaxX.valueProperty().addListener(
            new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> obsValue, Number oldValue, Number newValue) {
                    handleSliderMaxXChange(newValue);
                }
            }
        );

        sliderMinY.valueProperty().addListener(
            new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> obsValue, Number oldValue, Number newValue) {
                    handleSliderMinYChange(newValue);
                }
            }
        );
        
        sliderMaxY.valueProperty().addListener(
            new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> obsValue, Number oldValue, Number newValue) {
                    handleSliderMaxYChange(newValue);
                }
            }
        );
        
        sliderDiversityX.valueProperty().addListener(
            new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> obsValue, Number oldValue, Number newValue) {
                    handleDiversityXChange(newValue);
                }
            }
        );
        
        sliderDiversityY.valueProperty().addListener(
            new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> obsValue, Number oldValue, Number newValue) {
                    handleDiversityYChange(newValue);
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
        
    }
    
    public void startMouseCursorSwingTask() {
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
    private void handleButtonResetAction(ActionEvent event) {
        boolean isDisabled = vBoxControls.isDisabled();
        
        vBoxControls.setDisable(true);
        mouseCursorSwingProperties.resetToDefaults();
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
        double minSpeedX = value.doubleValue();
        sliderMaxSpeedX.setMin(minSpeedX);
        mouseCursorSwingProperties.setMinSpeedX(new BigDecimal(minSpeedX).setScale(1, RoundingMode.HALF_UP).doubleValue());
        labelMinSpeedXValue.setText(String.format("%.1f", minSpeedX));
    }
    
    private void handleSliderMaxSpeedXChange(Number value) {
        double maxSpeedX = value.doubleValue();
        sliderMinSpeedX.setMax(maxSpeedX);
        mouseCursorSwingProperties.setMaxSpeedX(new BigDecimal(maxSpeedX).setScale(1, RoundingMode.HALF_UP).doubleValue());
        labelMaxSpeedXValue.setText(String.format("%.1f", maxSpeedX));
    }
    
    private void handleSliderMinSpeedYChange(Number value) {
        double minSpeedY = value.doubleValue();
        sliderMaxSpeedY.setMin(minSpeedY);
        mouseCursorSwingProperties.setMinSpeedY(new BigDecimal(minSpeedY).setScale(1, RoundingMode.HALF_UP).doubleValue());
        labelMinSpeedYValue.setText(String.format("%.1f", minSpeedY));
    }
    
    private void handleSliderMaxSpeedYChange(Number value) {
        double maxSpeedY = value.doubleValue();
        sliderMinSpeedY.setMax(maxSpeedY);
        mouseCursorSwingProperties.setMaxSpeedY(new BigDecimal(maxSpeedY).setScale(1, RoundingMode.HALF_UP).doubleValue());
        labelMaxSpeedYValue.setText(String.format("%.1f", maxSpeedY));
    }
    
    private void handleSliderMinXChange(Number value) {
        int minX = value.intValue();
        sliderMaxX.setMin(minX);
        mouseCursorSwingProperties.setMinX(minX);
        labelMinXValue.setText(String.valueOf(minX));
    }
    
    private void handleSliderMaxXChange(Number value) {
        int maxX = value.intValue();
        sliderMinX.setMax(maxX);
        mouseCursorSwingProperties.setMaxX(maxX);
        labelMaxXValue.setText(String.valueOf(maxX));
    }
    
    private void handleSliderMinYChange(Number value) {
        int minY = value.intValue();
        sliderMaxY.setMin(minY);
        mouseCursorSwingProperties.setMinY(minY);
        labelMinYValue.setText(String.valueOf(minY));
    }
    
    private void handleSliderMaxYChange(Number value) {
        int maxY = value.intValue();
        sliderMinY.setMax(maxY);
        mouseCursorSwingProperties.setMaxY(maxY);
        labelMaxYValue.setText(String.valueOf(maxY));
    }
    
    private void handleDiversityXChange(Number value) {
        double diversityX = value.doubleValue();
        mouseCursorSwingProperties.setDiversityX(new BigDecimal(diversityX).setScale(1, RoundingMode.HALF_UP).doubleValue());
        labelDiversityXValue.setText(String.format("%.1f", diversityX));
    }
    
    private void handleDiversityYChange(Number value) {
        double diversityY = value.doubleValue();
        mouseCursorSwingProperties.setDiversityY(new BigDecimal(diversityY).setScale(1, RoundingMode.HALF_UP).doubleValue());
        labelDiversityYValue.setText(String.format("%.1f", diversityY));
    }
    
}
