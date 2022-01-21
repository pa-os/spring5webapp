package guru.springframework.spring5webapp.controllers;

import guru.springframework.spring5webapp.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping(path="/books")
public class BookController {

  private final BookRepository bookRepository;

  @GetMapping
  public String getBooks(Model model) {
    model.addAttribute("books", bookRepository.findAll());
    return "books";
  }

}
