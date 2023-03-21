import static java.lang.Math.*;

public interface SolvingSystem {
    public void solveSystem ( );

    public String getDescription ( );

    public Double getRootX ( );

    public Double getRootY ( );

    public double getE();

    public int getNumber();

    public int getIters();
    public default double getValueOfBasic1(double x,double y){
        switch (getNumber()){
            case(1):
                return 0.1*x*x+x+0.2*y*y-0.3;
        }
        return 0;
    }
    public default double getValueOfBasic2(double x,double y){
        switch (getNumber()){
            case(1):
                return 0.2*x*x+y+0.1*x*y-0.7;
        }
        return 0;
    }
    public default double getValueOff1 (double x, double y) {
        switch (getNumber()) {
            case (1):
                return (0.3-0.1*pow(x,2)-0.2*y*y);
            case (2):
                return (13-y)/(5*x);
        }
        return 0;

    }

    public default double getValueOff2 (double x, double y) {
        switch (getNumber()) {
            case (1):
                return (-0.2*pow(x,2)-0.1*x*y+0.7);
            case (2):
                return sin(x+0.5)-1;
        }
        return 0;

    }



    public default double getYOff1 (double x) {
        switch (getNumber()) {
            case (1):
                return pow(((0.3-0.1*pow(x,2)-x)/0.2),0.5);
            case (2):
                return sin(x+0.5)-1;
        }
        return 0;

    }

    public default double getYOff2 (double x) {
        switch (getNumber()) {
            case (1):
                return (0.7-0.2*pow(x,2))/(1-0.1*x);
            case (2):
                return -5*pow(x,2)+13;
        }
        return 0;

    }



}