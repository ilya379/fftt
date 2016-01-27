package ru.shakhov.ilya.project.example;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import org.apache.commons.math3.complex.Complex;

public class BarChartSample {
    private Complex[] data;

    public BarChartSample(Complex[] complexes) {
        data = complexes;
    }

    public Parent getNode() {
        //System.out.println(data.length);
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc =
                new BarChart<>(xAxis,yAxis);
        bc.setTitle("FFT");
        xAxis.setLabel("frequency");
        yAxis.setLabel("Amplitude");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("real");
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("imaginary");
        for (int i = 0; i<data.length/2+1; i++) {
            series1.getData().add(new XYChart.Data(String.valueOf(i), i==0?data[i].getReal():data[i].getReal()*2));
            series2.getData().add(new XYChart.Data(String.valueOf(i),  i==0?data[i].getImaginary():data[i].getImaginary()*2));
        }
        bc.getData().addAll(series1, series2);
        return bc;
    }


}