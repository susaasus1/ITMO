import static java.lang.Math.*;

public class SolvingEquationByBisection implements SolvingEquation {

    private String description = "byBisection";
    private double a;
    private double b;
    private double e;
    private int number;
    private int iter = 0;
    private Double root;

    @Override
    public double getE() {
        return e;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public SolvingEquationByBisection(Decision equation) {
        this.a = equation.getIntervalLeftBoundary();
        this.b = equation.getIntervalRightBoundary();
        this.e = equation.getAccuracy();
        this.number = equation.getNumber();
    }

    public void solve() {
        solveInCycle(a, b, e);
    }


    @Override
    public Double getRoot() {
        return root;
    }

    @Override
    public Double getAnswer() {
        return getValueOfFunction(getRoot());
    }

    @Override
    public int getIter() {
        return iter;
    }

    public void solveInCycle(double a, double b, double e) {
        double x = 0;
        while ((b - a > e)&& (Math.abs(getValueOfFunction(x))>e)) {
            iter += 1;
            x = (a + b) / 2;
            if (getValueOfFunction(a) * getValueOfFunction(x) < 0d) b = x;
            else if (getValueOfFunction(b) * getValueOfFunction(x) < 0d) a = x;
            else {
                root = null;
                return;
            }
        }
        root = x;
    }

    public int getNumber() {
        return number;
    }


}