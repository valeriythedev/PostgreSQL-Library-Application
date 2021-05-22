package by.liashuk.library.repository;

import by.liashuk.library.model.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetBookConverter {
    public static Book bookConverter(ResultSet resultSet){
        Book book=new Book();
        try{
            book.setName(resultSet.getString("name"));
            book.setAuthor(resultSet.getString("author"));
            book.setGenre(resultSet.getString("genre"));
            book.setPrice(resultSet.getInt("price"));
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return book;
    }
}
