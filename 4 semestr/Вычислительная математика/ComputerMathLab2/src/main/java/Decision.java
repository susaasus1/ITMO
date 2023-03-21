public class Decision {

    private int number;
    private Double accuracy;
    private Double intervalLeftBoundary;
    private Double intervalRightBoundary;
    private Double intervalLeftBoundaryX;
    private Double intervalRightBoundaryX;
    private Double intervalLeftBoundaryY;
    private Double intervalRightBoundaryY;
    private Double x0;
    private Double y0;

    public Double getX0 ( ) {
        return x0;
    }

    public Double getY0 ( ) {
        return y0;
    }

    public void setX0 (Double x0) {
        this.x0 = x0;
    }

    public void setY0 (Double y0) {
        this.y0 = y0;
    }



    public Decision (int number, Double accuracy, Double intervalLeftBoundary, Double intervalRightBoundary) {
        this.number = number;
        this.accuracy = accuracy;
        this.intervalLeftBoundary = intervalLeftBoundary;
        this.intervalRightBoundary = intervalRightBoundary;
    }
    public Decision (int number, Double accuracy, Double intervalLeftBoundaryX, Double intervalRightBoundaryX,Double intervalLeftBoundaryY, Double intervalRightBoundaryY) {
        this.number = number;
        this.accuracy = accuracy;
        this.intervalLeftBoundaryX = intervalLeftBoundaryX;
        this.intervalRightBoundaryX = intervalRightBoundaryX;
        this.intervalLeftBoundaryY=intervalLeftBoundaryY;
        this.intervalRightBoundaryY=intervalRightBoundaryY;
    }

    public int getNumber ( ) {
        return number;
    }

    public void setNumber (int number) {
        this.number = number;
    }

    public Double getAccuracy ( ) {
        return accuracy;
    }

    public void setAccuracy (double accuracy) {
        this.accuracy = accuracy;
    }

    public Double getIntervalLeftBoundary ( ) {
        return intervalLeftBoundary;
    }

    public void setIntervalLeftBoundary (Double intervalLeftBoundary) {
        this.intervalLeftBoundary = intervalLeftBoundary;
    }

    public Double getIntervalRightBoundary ( ) {
        return intervalRightBoundary;
    }

    public Double getIntervalLeftBoundaryX() {
        return intervalLeftBoundaryX;
    }

    public Double getIntervalRightBoundaryX() {
        return intervalRightBoundaryX;
    }

    public Double getIntervalLeftBoundaryY() {
        return intervalLeftBoundaryY;
    }

    public Double getIntervalRightBoundaryY() {
        return intervalRightBoundaryY;
    }

    public void setIntervalRightBoundary (double intervalRightBoundary) {
        this.intervalRightBoundary = intervalRightBoundary;
    }
}