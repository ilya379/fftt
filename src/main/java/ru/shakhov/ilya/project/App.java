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
import ru.shakhov.ilya.project.example.BarChartSample;
import ru.shakhov.ilya.project.example.DataProvider;
import ru.shakhov.ilya.project.example.FunctionDataProvider;
import ru.shakhov.ilya.project.example.LineChartSample;

import java.lang.Math;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

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
        stage.setTitle("Bar Chart Sample");

        FastFourierTransformer transformer = new FastFourierTransformer(DftNormalization.STANDARD);
        FunctionDataProvider dataProvider = new FunctionDataProvider(aDouble -> Math.sin(1*aDouble+1)+Math.sin(6*aDouble));
        Double[] x = new Double[] {0.0, Math.PI/15, 2*Math.PI/15, 3*Math.PI/15, 4*Math.PI/15, 5*Math.PI/15, 6*Math.PI/15, 7*Math.PI/15, 8*Math.PI/15, 9*Math.PI/15,
                10*Math.PI/15, 11*Math.PI/15, 12*Math.PI/15, 13*Math.PI/15, 14*Math.PI/15, 15*Math.PI/15};
        double[] xprim = new double[x.length];
        for(int i = 0; i<x.length; i++) {
            xprim[i] = x[i].doubleValue();
        }
        dataProvider.setXData(x);

        LineChartSample lineChartSample = new LineChartSample(x,dataProvider.getData());
        BarChartSample barChartSample = new BarChartSample(transformer.transform(xprim, FORWARD));


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
