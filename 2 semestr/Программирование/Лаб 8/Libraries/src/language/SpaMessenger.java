package language;

public class SpaMessenger implements Messenger {
    @Override
    public String autorization() {
        return "Autorización";
    }

    @Override
    public String askLogin() {
        return "Acceso";
    }

    @Override
    public String askPassword() {
        return "Contraseña";
    }

    @Override
    public String registration() {
        return "registrarse";
    }

    @Override
    public String visual() {
        return "Visualización";
    }

    @Override
    public String table() {
        return "Mesa";
    }

    @Override
    public String inputfield() {
        return "Campo de entrada";
    }

    @Override
    public String outputfield() {
        return "Campo de salida";
    }

    @Override
    public String exitt() {
        return "Producción";
    }

    @Override
    public String ready() {
        return "Ingrese texto";
    }

    @Override
    public String commandss() {
        return "Comandos";
    }

    @Override
    public String userf() {
        return "Usuario";
    }

    @Override
    public String delete() {
        return "Borrar";
    }

    @Override
    public String update() {
        return "Actualizar";
    }
}
