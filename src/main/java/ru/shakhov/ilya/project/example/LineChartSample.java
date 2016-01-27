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
    private double[] data;
    private double[] x;

    public LineChartSample(double[] x, double[] y) {
        data = y;
        this.x = x;

    }

    public Parent getNode() {
        //System.out.println(data.length);
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("x");
        //creating the chart
        final LineChart<Number,Number> lineChart =
                new LineChart<Number,Number>(xAxis,yAxis);

        lineChart.setTitle("chart");
        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("function");
        //populating the series with data
        for (int i=0; i< data.length; i++) {
            //System.out.print(data[i] + " ");
            series.getData().add(new XYChart.Data(x[i], data[i]));
        }

        System.out.println();
        lineChart.getData().add(series);
        return lineChart;
    }

}