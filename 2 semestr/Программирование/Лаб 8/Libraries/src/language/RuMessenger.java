package language;

public class RuMessenger implements Messenger {

    @Override
    public String autorization() {
        return "Авторизация";
    }

    @Override
    public String askLogin() {
        return "Логин";
    }

    @Override
    public String askPassword() {
        return "Пароль";
    }

    @Override
    public String registration() {
        return "Регистрация";
    }
    @Override
    public String visual() {
        return "Визуализация";
    }

    @Override
    public String table() {
        return "Таблица";
    }

    @Override
    public String inputfield() {
        return "Поле ввода";
    }

    @Override
    public String outputfield() {
        return "Поле вывода";
    }

    @Override
    public String exitt() {
        return "Выход";
    }

    @Override
    public String ready() {
        return "Ввести текст";
    }

    @Override
    public String commandss() {
        return "Команды";
    }

    @Override
    public String userf() {
        return "Пользователь";

    }

    @Override
    public String delete() {
        return "Удалить";
    }

    @Override
    public String update() {
        return "Обновить";
    }

}
