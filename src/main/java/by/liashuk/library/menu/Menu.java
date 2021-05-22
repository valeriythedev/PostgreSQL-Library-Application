package by.liashuk.library.menu;

import by.liashuk.library.model.Book;
import by.liashuk.library.repository.BookDataBaseRepository;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Menu {

    BookDataBaseRepository bookDataBaseRepository;

    public Menu(BookDataBaseRepository bookDataBaseRepository) {
        this.bookDataBaseRepository = bookDataBaseRepository;
    }

    Scanner scanner=new Scanner(System.in);

    public void menu() {
        boolean active=true;
        while(active){
            System.out.println("\nChoose operation: \n1.Add Book \n2.Find Book \n3.View Books \n4.Delete Book \n5.Exit the program");
            switch (getUserChoice()) {
                case 1 -> addingInSQL();
                case 2 -> searchForBook();
                case 3 -> viewAllBooks(bookDataBaseRepository.view());
                case 4 -> deleteBook();
                case 5 -> active = false;
                default -> System.out.println("You choose wrong operation");
            }
        }
    }

    public void addingInSQL(){
        Book book = fillBookData();
        bookDataBaseRepository.add(book);
        System.out.println("Book successfully added.");
    }

    public Book fillBookData() {
        Book addedBook=new Book();
        System.out.println("Write name of the book: ");
        scanner.nextLine();
        addedBook.setName(setBookName());
        scanner.nextLine();
        System.out.println("Write author of the book: ");
        addedBook.setAuthor(setBookAuthor());
        scanner.nextLine();
        System.out.println("Write genre of the book: ");
        addedBook.setGenre(setBookGenre());
        scanner.nextLine();
        System.out.println("Write price of the book: ");
        addedBook.setPrice(setBookPrice());
        return addedBook;
    }

    public void searchForBook(){
        System.out.println("Write name of the book which you want to find: ");
        scanner.nextLine();
        String bookName=setBookName();
        ArrayList<Book> searchedBook=bookDataBaseRepository.find(bookName);
        viewAllBooks(searchedBook);
    }

    public void viewAllBooks(ArrayList<Book> arrayList){
        for (Book book : arrayList) {
            System.out.println(arrayList.indexOf(book)+1 + ". " + book.toString());
        }
    }

    public void deleteBook(){
        Book book=deletingBook();
        bookDataBaseRepository.delete(book);
        System.out.println("Book successfully deleted.");
    }

    public Book deletingBook(){
        Book deletedBook=new Book();
        viewAllBooks(bookDataBaseRepository.view());
        System.out.println("Write name of the book which you want to delete: ");
        scanner.nextLine();
        deletedBook.setName(setBookName());
        return deletedBook;
    }

    public int getUserChoice() { return scanner.nextInt(); }

    public String setBookName() { return scanner.nextLine().toUpperCase(Locale.ROOT); }

    public String setBookAuthor() { return scanner.nextLine().toUpperCase(Locale.ROOT); }

    public String setBookGenre() { return scanner.nextLine().toUpperCase(Locale.ROOT); }

    public int setBookPrice() { return scanner.nextInt(); }
}
