import static java.lang.Math.*;

public class SolvingSystemByIteration implements SolvingSystem {

    private String description = "byIteration";
    private double e;
    private int number;
    private double a;
    private double b;
    private Double rootX;
    private Double rootY;
    private double x;
    private double y;

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public SolvingSystemByIteration(Decision system) {
        this.a = system.getIntervalRightBoundaryX();
        this.b = system.getIntervalRightBoundaryY();
        this.e = system.getAccuracy();
        this.number = system.getNumber();
    }

    private int iter = 0;

    public void solveSystem() {
        x = a;
        y = b;
        double prevX;
        double prevY;
        do {
            iter += 1;
            prevX = x;
            prevY = y;
            x = getValueOff1(prevX, prevY);
            y = getValueOff2(prevX, prevY);
            if (abs(getValueOfBasic2(x,y))<e && abs(getValueOfBasic1(x,y))<e){
                break;
            }
        } while (true);
        rootX = x;
        rootY = y;
        System.out.println(e);
        System.out.println(getValueOfBasic1(x,y));
        System.out.println(getValueOfBasic2(x,y));
        System.out.println(x);
        System.out.println(y);
    }

    @Override
    public int getIters() {
        return iter;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Double getRootX() {
        return rootX;
    }

    @Override
    public double getE() {
        return e;
    }

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public Double getRootY() {
        return rootY;
    }


}