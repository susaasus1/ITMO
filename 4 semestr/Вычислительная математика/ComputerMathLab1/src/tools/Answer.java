package tools;

import java.text.DecimalFormat;

public class Answer {
    private Matrix main;
    private Matrix deviation;
    private int iterations;

    public Answer(Matrix main, Matrix deviation, int iterations) {
        this.main = main;
        this.deviation = deviation;
        this.iterations = iterations;
    }

    public String toString() {
        String answer = "Приближенное решение задачи:\n";
        for (int i = 0; i < main.getRow(); ++i) {
            String dou = new DecimalFormat("#0.00000000000000000000").format(main.get(i, 0));
            answer += "x" + i + "=" + dou + "\n";
        }
        for (int i = 0; i < main.getRow(); ++i) {
            answer += "Погрешность x" + i + " = ";
            answer += new DecimalFormat("#0.00000000000000000000").format(deviation.get(i, 0)) + "\n";
        }
        answer += "Количество произведенных итераций: ";
        answer += iterations;
        return answer;
    }

}
