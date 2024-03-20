package mk.ukim.finki.emt.lab.listeners;

import mk.ukim.finki.emt.lab.model.Book;
import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;

public class BookEventListener {
    public void onCreate(Book book) {
        System.out.println("Book created: " + book.getName());
    }

    public void onEdit(Book book) {
        System.out.println("Book edited: " + book.getName());
    }

    public void onDelete(Book book) {
        System.out.println("Book deleted: " + book.getName());
    }
}
