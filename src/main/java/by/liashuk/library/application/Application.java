package by.liashuk.library.application;

import by.liashuk.library.menu.Menu;
import by.liashuk.library.repository.BookDataBaseRepository;

public class Application {
    public static void main(String[] args)  {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Menu start=new Menu(new BookDataBaseRepository());
        start.menu();
    }
}
