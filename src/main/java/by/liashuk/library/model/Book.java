package by.liashuk.library.model;

public class Book {
    private String name;
    private String author;
    private String genre;
    private int price;

    public Book(){}

    public void setName(String name){ this.name=name; }

    public void setAuthor(String author){ this.author=author; }

    public void setGenre(String genre){ this.genre=genre; }

    public void setPrice(int price){ this.price=price; }

    public String getName(){ return this.name; }

    public String getAuthor(){ return this.author; }

    public String getGenre(){ return this.genre; }

    public int getPrice(){ return this.price; }

    @Override
    public String toString() { return getName()+","+getAuthor()+","+getGenre()+","+getPrice()+"$"; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return price == book.price && name.equals(book.name) && author.equals(book.author) && genre.equals(book.genre);
    }
}
