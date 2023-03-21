package language;

public class AlbMessenger implements Messenger{
    @Override
    public String autorization() {
        return  "Autorizimi";
    }

    @Override
    public String askLogin() {
        return "Hyrja";
    }

    @Override
    public String askPassword() {
        return "Fjalëkalimi";
    }

    @Override
    public String registration() {
        return "Kontrolloni";
    }

    @Override
    public String visual() {
        return "Vizualizimi";
    }

    @Override
    public String table() {
        return "Tabela";
    }

    @Override
    public String inputfield() {
        return "Fusha e hyrjes";
    }

    @Override
    public String outputfield() {
        return "Fusha e daljes";
    }

    @Override
    public String exitt() {
        return "Produkti";
    }

    @Override
    public String ready() {
        return "Vendosni tekstin";
    }

    @Override
    public String commandss() {
        return "Komandat";
    }

    @Override
    public String userf() {
        return "Përdorues";
    }

    @Override
    public String delete() {
        return "Fshij";
    }

    @Override
    public String update() {
        return "Rifresko";
    }
}
