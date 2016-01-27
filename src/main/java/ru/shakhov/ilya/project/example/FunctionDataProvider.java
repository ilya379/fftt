package ru.shakhov.ilya.project.example;

import java.util.function.Function;

/**
 * Created by Светлана on 21.01.2016.
 */
public class FunctionDataProvider implements DataProvider{
    private final Function<Double, Double> function;
    private double[] xData = new double[0];
    public FunctionDataProvider(Function<Double, Double> function) {
        this.function = function;
    }

    public void setXData(double[] data) {
        xData = data;
    }



    @Override
    public double[] getData() {
        double[] result = new double[xData.length];
        for (int i = 0; i<xData.length; i++) {
            //result[i] = i<xData.length-4?function.apply(xData[i]):0;
            result[i] = function.apply(xData[i]);
        }
        return result;
    }
}
