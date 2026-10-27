package hh.bhy880.bookstore;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import hh.bhy880.bookstore.domain.Book;
import hh.bhy880.bookstore.domain.BookRepository;
import hh.bhy880.bookstore.domain.Category;
import hh.bhy880.bookstore.domain.CategoryRepository;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest 
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void addNewBook() {
        Category category = new Category("Religion");
        categoryRepository.save(category);
        Book book = new Book("Bhagavad Gita", "Unknown", 0, 122384484933L, 22.99, category);
        bookRepository.save(book);
        assertThat(book.getId()).isNotNull();
    }

    @Test
    public void deleteNewBook() {
        List<Book> books = bookRepository.findByAuthor("George Orwell");
        Book book = books.get(0);
        bookRepository.delete(book);
        List<Book> newBooks = bookRepository.findByAuthor("George Orwell");
        assertThat(newBooks).hasSize(0);
    }

    @Test
    public void updateNewBook(){
        List<Book> books = bookRepository.findByAuthor("George Orwell");
        Book book = books.get(0);
        book.setAuthor("Kim Jong-Un");
        book.setPublicationYear(2000);
        bookRepository.save(book);

        assertThat(bookRepository.findByAuthor("George Orwell")).hasSize(0);
        List<Book> updatedBooks = bookRepository.findByAuthor("Kim Jong-Un");
        assertThat(updatedBooks).hasSize(1);
        assertThat(updatedBooks.get(0).getId()).isEqualTo(book.getId());
        assertThat(updatedBooks.get(0).getPublicationYear()).isEqualTo(2000);
    }
}
