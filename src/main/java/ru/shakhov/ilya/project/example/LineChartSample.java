package ru.shakhov.ilya.project.example;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import org.apache.commons.math3.complex.Complex;


public class LineChartSample {
    private Double[] data;
    private Double[] x;

    public LineChartSample(Double[] x, Double[] y) {
        data = y;
        this.x = x;

    }

    public Parent getNode() {
        System.out.println(data.length);
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Number of Month");
        //creating the chart
        final LineChart<Number,Number> lineChart =
                new LineChart<Number,Number>(xAxis,yAxis);

        lineChart.setTitle("Stock Monitoring, 2010");
        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("My portfolio");
        //populating the series with data
        for (int i=0; i< data.length; i++) {
            series.getData().add(new XYChart.Data(x[i], data[i]));
        }


        lineChart.getData().add(series);
        return lineChart;
    }

}