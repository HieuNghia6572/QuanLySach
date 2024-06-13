package com.example.QuanLySach.Controller;

import com.example.QuanLySach.model.Book;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class BookController {
    @Autowired
    private List<Book> listBook;

    @GetMapping("/")
    public String showAllBooks(Model model) {
        model.addAttribute("listBook", listBook);
        model.addAttribute("title", "Danh sách cuốn sách");
        return "book/list";
    }

    @GetMapping("/add")
    public String addBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "book/add";
    }

    @PostMapping("/add")
    public String addBook(@Valid @ModelAttribute("book") Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "book/add";
        }
        listBook.add(book);
        return "redirect:/";
    }
    @GetMapping("/edit/{id}")
    public String editBookForm(@PathVariable("id") int id, Model model) {
        // tìm ID
        Book editBook = null;
        for (Book book : listBook) {
            if (book.getId() == id) {
                editBook = book;
                break;
            }
        }
        if (editBook == null) {
            // ktra nếu null thì trở về index
            return "redirect:/";
        }

        // cập nhật lại thông tin sách
        model.addAttribute("book", editBook);
        return "book/edit";
    }

    @PostMapping("/edit/{id}")
    public String editBook(@PathVariable("id") int id,
                           @Valid @ModelAttribute("book") Book updatedBook,
                           BindingResult result) {
        if (result.hasErrors()) {
            return "book/edit";
        }

        // Tìm ID cuốn sách để update
        for (Book book : listBook) {
            if (book.getId() == id) {
                book.setTieude(updatedBook.getTieude());
                book.setTentg(updatedBook.getTentg());
                book.setPrice(updatedBook.getPrice());
                book.setCategory(updatedBook.getCategory());
                break;
            }
        }
        // Redirect to the list of books after editing
        return "redirect:/";
    }
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") long id){
        listBook.removeIf(p->p.getId()==id);
        return "redirect:/";
    }
}