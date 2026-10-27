package hh.bhy880.bookstore;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import hh.bhy880.bookstore.web.BookController;

@SpringBootTest
class BookstoreApplicationTests {

	private final BookController controller;

	@Autowired 
	public BookstoreApplicationTests(BookController controller) {
		this.controller = controller;
	}

	 @Test
    public void controllerLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

	@Test
	void contextLoads() {
	}

}
