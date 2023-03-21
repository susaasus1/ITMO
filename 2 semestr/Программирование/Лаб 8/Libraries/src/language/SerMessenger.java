package language;

public class SerMessenger implements Messenger {


    @Override
    public String autorization() {
        return "Овлашћење";
    }

    @Override
    public String askLogin() {
        return "Пријавите се";
    }

    @Override
    public String askPassword() {
        return "Лозинка";
    }

    @Override
    public String registration() {
        return "Пријавити";
    }

    @Override
    public String visual() {
        return "Визуализација";
    }

    @Override
    public String table() {
        return "Сто";
    }

    @Override
    public String inputfield() {
        return "Поље за унос";
    }

    @Override
    public String outputfield() {
        return "Излазно поље";
    }

    @Override
    public String exitt() {
        return "Оутпут";
    }

    @Override
    public String ready() {
        return "Унесите текст";
    }

    @Override
    public String commandss() {
        return "Команде";
    }

    @Override
    public String userf() {
        return "Корисник";
    }

    @Override
    public String delete() {
        return "Избриши";
    }

    @Override
    public String update() {
        return "Освјежи";
    }
}
