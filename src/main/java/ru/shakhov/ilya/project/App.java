package ru.shakhov.ilya.project;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.apache.commons.math3.transform.DftNormalization;
import org.apache.commons.math3.transform.FastFourierTransformer;
import org.apache.commons.math3.transform.TransformType;
import ru.shakhov.ilya.project.example.*;


import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static java.lang.Math.*;
import static org.apache.commons.math3.transform.TransformType.*;

public class App extends Application {
    public static String getHello() {
        return "Hello";
    }
    private Stage stage;

    public static void main(String[] args) {
            launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        stage.setTitle("FFT");

        FastFourierTransformer transformer = new FastFourierTransformer(DftNormalization.STANDARD);
        FunctionDataProvider dataProvider = new FunctionDataProvider(aDouble -> 230*sin(aDouble)+130*cos(5*aDouble)+1);
        double[] x = PointDataGenerator.getData(0.0+2, 2 * PI+2, new Double(pow(2.0, 5.0)).intValue());
        dataProvider.setXData(x);
        double[] y = dataProvider.getData();

        System.out.println();


        LineChartSample lineChartSample = new LineChartSample(x,y);
        BarChartSample barChartSample = new BarChartSample(transformer.transform(y, FORWARD));


        Scene scene  = new Scene(lineChartSample.getNode(),800,600);
        final boolean[] a = {true};
        scene.setOnKeyPressed(event -> {
            if (a[0]) {
                stage.getScene().setRoot(barChartSample.getNode());
                a[0] = false;
            } else {
                stage.getScene().setRoot(lineChartSample.getNode());
                a[0] = true;
            }
        });
        stage.setScene(scene);
        stage.show();

    }
}
