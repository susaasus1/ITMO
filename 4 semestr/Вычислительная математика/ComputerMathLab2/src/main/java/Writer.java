import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.*;


public class Writer {

    List<SolvingEquation> solvingEquations;
    List<SolvingSystem> solvingSystems;
    FileWriter writer = new FileWriter("answer.txt", false);

    public Writer() throws IOException {
        solvingEquations = new ArrayList<>();
        solvingSystems = new ArrayList<>();
    }

    public void setSolvingEquations(SolvingEquation... solvingEquations) {
        for (SolvingEquation solvingEquation : solvingEquations) {
            this.solvingEquations.add(solvingEquation);
        }
    }

    public void setSolvingSystems(SolvingSystem... solvingSystems) {
        for (SolvingSystem solvingSystem : solvingSystems) {
            this.solvingSystems.add(solvingSystem);
        }
    }

    public void writeEquationsRoots() throws IOException {
        for (SolvingEquation solvingEquation : solvingEquations) {
            double acc = solvingEquation.getE();
            String[] splitter = String.valueOf(acc).split("\\.");
            Integer i = splitter[1].length();
            i += 2;

            if (solvingEquation.getDescription().equals("byBisection") && solvingEquation.getRoot() == null)
                System.out.println("root " + solvingEquation.getDescription() + " oops, вы выбрали неподходящий интервал (данный метод не находит на нем корни)");
            else if (solvingEquation.getDescription().equals("bySimpleIteration") && solvingEquation.getRoot() == null)
                System.out.println("root " + solvingEquation.getDescription() + " oops, нарушено условие сходимости, вам следует выбрать другой интервал");
            else {
                writer.write("root " + solvingEquation.getDescription() + i.toString() + +solvingEquation.getRoot() + "\n");
                writer.flush();
                writer.write("Answer=" + solvingEquation.getAnswer() + ", Iterations=" + solvingEquation.getIter() + "\n");
                writer.flush();
                System.out.printf("root " + solvingEquation.getDescription() + ": %." + i.toString() + "f %n", solvingEquation.getRoot());
                System.out.println("Answer=" + solvingEquation.getAnswer() + ", Iterations=" + solvingEquation.getIter());
            }
        }
    }


    public void writeSystemsRoots() throws IOException {
        for (SolvingSystem solvingSystem : solvingSystems) {
            double acc = solvingSystem.getE();
            String[] splitter = String.valueOf(acc).split("\\.");
            Integer i = splitter[1].length();
            i += 2;
            if (solvingSystem.getRootX().isNaN() || solvingSystem.getRootX().isInfinite() || solvingSystem.getRootY().isNaN() || solvingSystem.getRootY().isInfinite()) {
                System.out.println("На данных отрезках корня нет");
            } else {

                writer.write("rootX " + solvingSystem.getDescription() + i.toString() + solvingSystem.getRootX() + "\n");
                writer.flush();
                writer.write("rootY " + solvingSystem.getDescription() + i.toString() + solvingSystem.getRootY() + "\n");
                writer.flush();
                writer.write("Iterations=" + solvingSystem.getIters());
                writer.flush();
                System.out.printf("rootX " + solvingSystem.getDescription() + ": %." + i.toString() + "f %n", solvingSystem.getRootX());
                System.out.printf("rootY " + solvingSystem.getDescription() + ": %." + i.toString() + "f %n", solvingSystem.getRootY());
                System.out.println("Iterations=" + solvingSystem.getIters());
            }
        }
    }
}