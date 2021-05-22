package by.liashuk.library.repository;

import by.liashuk.library.methods.Methods;
import by.liashuk.library.model.Book;

import java.sql.*;
import java.util.ArrayList;

public class BookDataBaseRepository implements Methods {

    private static final String url="jdbc:postgresql://localhost:5432/bookdb";
    private static final String user="postgres";
    private static final String password="123";

    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    @Override
    public void add(Book book) {
        String insertInTable="INSERT INTO BOOKS(name,author,genre,price)" + "VALUES(?,?,?,?)";
        try(Connection con= DriverManager.getConnection(url,user,password)){
            PreparedStatement preparedStatement= con.prepareStatement(insertInTable, statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,book.getName());
            preparedStatement.setString(2,book.getAuthor());
            preparedStatement.setString(3,book.getGenre());
            preparedStatement.setInt(4,book.getPrice());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ArrayList<Book> find(String name) {
        ArrayList<Book> bookArrayList=new ArrayList<>();
        String findFromTable="SELECT * FROM BOOKS WHERE name = ?";
        try(Connection con2=DriverManager.getConnection(url,user,password)){
            PreparedStatement preparedStatement=con2.prepareStatement(findFromTable);
            preparedStatement.setString(1,name);
            resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                bookArrayList.add(ResultSetBookConverter.bookConverter(resultSet));
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return bookArrayList;
    }

    @Override
    public ArrayList<Book> view() {
        ArrayList<Book> bookArrayList =new ArrayList<>();
        String selectFromTable="SELECT * FROM BOOKS";
        try(Connection con3=DriverManager.getConnection(url,user,password)){
            statement=con3.createStatement();
            resultSet=statement.executeQuery(selectFromTable);
            while (resultSet.next()){
                bookArrayList.add(ResultSetBookConverter.bookConverter(resultSet));
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return bookArrayList;
    }

    @Override
    public void delete(Book book) {
        String deleteFromTable="DELETE FROM BOOKS WHERE name = ?";
        try(Connection con4=DriverManager.getConnection(url,user,password)){
            PreparedStatement preparedStatement=con4.prepareStatement(deleteFromTable);
            preparedStatement.setString(1,book.getName());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
