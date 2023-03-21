import java.io.IOException;
import java.util.Scanner;

public class Reader {

    private Scanner scanner;
    private String[] equations;
    private String[] systems;
    private Decision equation;
    private Decision system;

    public Decision getEquation ( ) {
        return equation;
    }

    public void setEquation (Decision equation) {
        this.equation = equation;
    }

    public Decision getSystem ( ) {
        return system;
    }

    public void setSystem (Decision system) {
        this.system = system;
    }

    public Reader (Scanner scanner, String[] equations, String[] systems) {
        this.scanner = scanner;
        this.equations = equations;
        this.systems = systems;
    }

    public Reader (Scanner scanner) {
        this.scanner = scanner;
    }

    public Scanner getScanner ( ) {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public void read() throws IOException {
        ReaderFromConsole readerFromConsole = new ReaderFromConsole(scanner, this);
        readerFromConsole.readFromConsole( );
    }

    public String[] getEquations ( ) {
        return equations;
    }

    public String[] getSystems ( ) {
        return systems;
    }
}