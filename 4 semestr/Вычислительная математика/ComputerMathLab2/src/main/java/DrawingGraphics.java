import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

import javax.swing.*;

import java.io.IOException;

import static java.lang.Math.*;

public class DrawingGraphics {

    Decision equation;
    Decision system;
    SolvingSystem solvingSystem;
    SolvingEquation solvingEquation;


    public DrawingGraphics (Decision equation, Decision system, SolvingEquation solvingEquation, SolvingSystem solvingSystem) {
        this.equation = equation;
        this.system = system;
        this.solvingEquation = solvingEquation;
        this.solvingSystem = solvingSystem;
    }

    public void drawFunction ( ) throws IOException {

        double[] xData = new double[100];
        double[] yData = new double[100];
        double[] y0 = new double[100];

        double diff = (equation.getIntervalRightBoundary( ) - equation.getIntervalLeftBoundary( )) / 100;
        double x = equation.getIntervalLeftBoundary( );
        xData[0] = x;
        yData[0] = solvingEquation.getValueOfFunction(x);
        y0[0] = 0;
        for (int i = 1; i < 100; i++) {
            x += diff;
            xData[i] = x;
            yData[i] = solvingEquation.getValueOfFunction(x);
            y0[i] = 0;
        }


// Create Chart
        XYChart chart = QuickChart.getChart("Sample Chart", "X", "Y", "y(x)", xData, yData);
        chart.addSeries("y(0)", xData, y0);

// Show it
        new SwingWrapper(chart).displayChart( );

// Save it
        BitmapEncoder.saveBitmap(chart, "./Sample_Chart", BitmapEncoder.BitmapFormat.PNG);

// or save it in high-res
        BitmapEncoder.saveBitmapWithDPI(chart, "./Sample_Chart_300_DPI", BitmapEncoder.BitmapFormat.PNG, 300);

    }

    public void drawSystemOfFunctions ( ) throws IOException {

        double[] xData = new double[100];
        double[] yData = new double[100];
        double[] yData2 = new double[100];

        double diffX = 0.1;
        double x = system.getX0( ) - 5;
        if (system.getX0().isInfinite() || system.getX0().isNaN()){
            return;
        }
        xData[0] = x;
        yData[0] = solvingSystem.getYOff1(x);
        yData2[0] = solvingSystem.getYOff2(x);

        for (int i = 1; i < 100; i++) {
            x += diffX;
            xData[i] = x;
            yData[i] = solvingSystem.getYOff1(x);
            yData2[i] = solvingSystem.getYOff2(x);
        }

// Create Chart
        XYChart chart = QuickChart.getChart("Sample Chart", "X", "Y", "y1(x)", xData, yData);
        chart.addSeries("y2(x)", xData, yData2);

// Show it
        new SwingWrapper(chart).displayChart( );



    }

}