import static java.lang.Math.*;

public interface SolvingEquation {

    public void solve ( );

    public double getE();

    public String getDescription();

    public Double getRoot();

    public int getNumber();

    public Double getAnswer();
    public int getIter();
    public default double getValueOfFunction (double x) {
        switch (getNumber()) {
            case (1):
                return 1.73d * pow(x, 3) - 3.21d * pow(x, 2) + x - 0.025d;
            case (2):
                return 2.24 * x + pow(x, 2) + 57 - pow(Math.E, -1 * x);
            case (3):
                return 4.31 * cos(x) + 0.5 * log(x) - 5;
        }

        return 0;
    }


}

