package hh.bhy880.bookstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import hh.bhy880.bookstore.domain.Book;
import hh.bhy880.bookstore.domain.BookRepository;

@Controller
public class BookController {
    private BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping (value = "/delete/{id}")
    public String deleteStudent(@PathVariable("id") Long id, Model model) {
	bookRepository.deleteById(id);
	return "redirect:/booklist";
    }
    
    @RequestMapping(value = "/edit/{id}")
    public String showModStu(@PathVariable("id") Long id, Model model){
	model.addAttribute("book", bookRepository.findById(id).orElseThrow());
	
	return "editbooks" ;
    }


    @RequestMapping(value = "/add")
    public String addStudent(Model model){
    model.addAttribute("book", new Book());
    return "addbooks";
}

    @PostMapping(value = "/save")
    public String save(Book book){
        bookRepository.save(book);
        return "redirect:/booklist";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/booklist")
    public String bookList(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "booklist";
    }
}
