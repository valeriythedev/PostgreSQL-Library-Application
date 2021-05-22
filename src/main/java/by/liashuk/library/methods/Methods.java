package by.liashuk.library.methods;

import by.liashuk.library.model.Book;
import java.util.ArrayList;

public interface Methods {

    void add(Book book);

    ArrayList<Book> find(String name);

    ArrayList<Book> view();

    void delete(Book book);
}
