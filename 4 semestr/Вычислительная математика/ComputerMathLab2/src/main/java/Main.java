import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main (String[] args) throws IOException {

        String[] equations = {"1.73x^3-3.21x^2+x-0.025 = 0", "2.24x+x^2+57-ℯ^(-x) = 0", "4.31cos(x)+0.5ln(x)-5 = 0"};
        String[] systems = {"0.1x^2+x+0.2y^2-0.3 = 0\n   0.2x^2+y-0.1xy-0.7 = 0", "y+5*x^2-13 = 0\n   sin(x+0.5)-y-1=0"};


        //Считываем данные
        Reader reader = new Reader(new Scanner(System.in), equations, systems);
        reader.read( );

        //Инициализируем решаторы
        SolvingEquationByBisection solvingEquationByBisection = new SolvingEquationByBisection(reader.getEquation());
        SolvingEquationBySimpleIteration solvingEquationBySimpleIteration = new SolvingEquationBySimpleIteration(reader.getEquation());
        SolvingSystemByIteration solvingSystemByNewton = new SolvingSystemByIteration(reader.getSystem());

        //решаем
        solvingEquationByBisection.solve();
        solvingEquationBySimpleIteration.solve();
        solvingSystemByNewton.solveSystem();


        Writer writer = new Writer();
        //выводим на экран
        writer.setSolvingEquations(solvingEquationByBisection, solvingEquationBySimpleIteration);
        writer.setSolvingSystems(solvingSystemByNewton);
        writer.writeEquationsRoots();
        writer.writeSystemsRoots();

        Decision system = reader.getSystem();
        system.setX0(solvingSystemByNewton.getX());
        system.setY0(solvingSystemByNewton.getY());
        reader.setSystem(system);
        DrawingGraphics drawingGraphics = new DrawingGraphics(reader.getEquation(), reader.getSystem(), solvingEquationByBisection, solvingSystemByNewton);
        drawingGraphics.drawFunction();
        drawingGraphics.drawSystemOfFunctions();

    }
}