import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class ReaderFromConsole extends Reader {
    private Reader reader;

    public ReaderFromConsole (Scanner scanner, Reader reader) {
        super(scanner);
        this.reader = reader;
    }

    public void readFromConsole() throws IOException {
        System.out.println("Введите хотите считать данные из файла или консоли:1-файл, 2-консоль");
        String strChoice = getScanner( ).nextLine( );
        if (strChoice.equals("1")) {
            System.out.println("Введите путь до файла: ");
            String str=getScanner().nextLine();
            getScanner().close();
            Path path= Paths.get(str);
            Scanner scan =new Scanner(path);
            scan.useDelimiter(System.getProperty("line.separator"));
            setScanner(scan);
        }
            int numberOfEquation = readNumber(reader.getEquations(),
                    "Выберите одно из предложенных нелинейных уравнений (введите номер понравившегося)",
                    "Ну вы чего, уравнения под таким номером нет",
                    "Номер должен быть целым числом"
            );


            double[] intervalOfEquation = readInterval();
            double accuracyOfEquation = readAccuracy(intervalOfEquation[1] - intervalOfEquation[0]);

            Decision equation = new Decision(numberOfEquation, accuracyOfEquation, intervalOfEquation[0], intervalOfEquation[1]);


            int numberOfSystem = readNumber(reader.getSystems(),
                    "Выберите одну из предложенных систем нелинейных уравнений (введите номер понравившейся)",
                    "Ну вы чего, системы уравнений под таким номером нет",
                    "Номер должен быть целым числом"
            );

            double[] intervalOfEquation2 = readIntervalSystem();
            double accuracyOfSystem = readAccuracy(Double.MAX_VALUE);

            Decision system = new Decision(numberOfSystem, accuracyOfSystem, intervalOfEquation2[0], intervalOfEquation2[1],intervalOfEquation2[2],intervalOfEquation2[3]);

        reader.setEquation(equation);
        reader.setSystem(system);
    }

    public int readNumber(String[] array, String startMessage, String boundaryError, String formatError) {
        int decision = 0;

        System.out.println(startMessage);
        for (Integer i=1; i<=array.length; i++) {
            System.out.println(i + ") " + array[i-1]);
        }
        while (true) {
            try {
                String strDecision = getScanner( ).nextLine( );
                decision = Integer.parseInt(strDecision);
                if (decision < 1 || decision > array.length) {
                    System.out.println(boundaryError);
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println(formatError);
                continue;
            }
        }

        return decision;
    }

    public double readAccuracy(double diff) {
        double accuracy = 0;
        System.out.println("Введите предпочитаемую точность вычисления корней:");
        while (true) {
            try {
                String strDecision = getScanner( ).nextLine( );
                accuracy = Double.parseDouble(strDecision);
                if (accuracy <= 0) {
                    System.out.println("Точность должна быть положительной");
                    continue;
                }
                if (accuracy >= diff) {
                    System.out.println("Точность не должна превосходить длинну интервала");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Точность - это число");
                continue;
            }
        }

        return accuracy;
    }

    public double[] readInterval(){
        double left = 0;
        System.out.println("Введите левую границу интервала, на котором производится поиск корней");
        while (true) {
            try {
                String strDecision = getScanner( ).nextLine( );
                left = Double.parseDouble(strDecision);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Граница интервала - это число");
                continue;
            }
        }

        double right = 0;
        System.out.println("Введите правую границу интервала, на котором производится поиск корней");
        while (true) {
            try {
                String strDecision = getScanner( ).nextLine( );
                right = Double.parseDouble(strDecision);
                if (right <= left) {
                    System.out.println("Правая граница должна быть больше левой");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Граница интервала - это число");
                continue;
            }
        }

        double[] result = {left, right};

        return result;
    }
    public double[] readIntervalSystem(){
        double left = 0;
        System.out.println("Введите левую границу интервала для икса, на котором производится поиск корней");
        while (true) {
            try {
                String strDecision = getScanner( ).nextLine( );
                left = Double.parseDouble(strDecision);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Граница интервала - это число");
                continue;
            }
        }

        double right = 0;
        System.out.println("Введите правую границу интервала для икса, на котором производится поиск корней");
        while (true) {
            try {
                String strDecision = getScanner( ).nextLine( );
                right = Double.parseDouble(strDecision);
                if (right <= left) {
                    System.out.println("Правая граница должна быть больше левой");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Граница интервала - это число");
                continue;
            }
        }
        double leftY = 0;
        System.out.println("Введите левую границу интервала для икса, на котором производится поиск корней");
        while (true) {
            try {
                String strDecision = getScanner( ).nextLine( );
                leftY = Double.parseDouble(strDecision);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Граница интервала - это число");
                continue;
            }
        }

        double rightY = 0;
        System.out.println("Введите правую границу интервала для икса, на котором производится поиск корней");
        while (true) {
            try {
                String strDecision = getScanner( ).nextLine( );
                rightY = Double.parseDouble(strDecision);
                if (rightY <= leftY) {
                    System.out.println("Правая граница должна быть больше левой");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Граница интервала - это число");
                continue;
            }
        }

        double[] result = {left, right,leftY,rightY};

        return result;
    }

}