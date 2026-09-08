package hh.bhy880.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.bhy880.bookstore.domain.Book;
import hh.bhy880.bookstore.domain.BookRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}



@Bean
public CommandLineRunner alustaTietokantta(BookRepository bookRepository) {
    return args -> {
        Book book1 = new Book();
        book1.setTitle("Oppenheimer");
        book1.setAuthor("Kai Bird");
        book1.setPublicationYear(2005);
        book1.setIsbn(9780375412023L);
        book1.setPrice(29.99);

        Book book2 = new Book();
        book2.setTitle("The Hobbit");
        book2.setAuthor("J.R.R. Tolkien");
        book2.setPublicationYear(1937);
        book2.setIsbn(9780261102217L);
        book2.setPrice(19.99);

        Book book3 = new Book();
        book3.setTitle("1984");
        book3.setAuthor("George Orwell");
        book3.setPublicationYear(1949);
        book3.setIsbn(9780451524935L);
        book3.setPrice(14.99);

        Book book4 = new Book();
        book4.setTitle("Pride and Prejudice");
        book4.setAuthor("Jane Austen");
        book4.setPublicationYear(1813);
        book4.setIsbn(9780141439518L);
        book4.setPrice(12.99);

        Book book5 = new Book();
        book5.setTitle("Dune");
        book5.setAuthor("Frank Herbert");
        book5.setPublicationYear(1965);
        book5.setIsbn(9780441172719L);
        book5.setPrice(24.99);

        bookRepository.saveAll(
            java.util.List.of(book1, book2, book3, book4, book5)
        );
    };
}

}
