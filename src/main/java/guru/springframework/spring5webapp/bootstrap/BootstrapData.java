package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component
public class BootstrapData implements CommandLineRunner {

  private final AuthorRepository authorRepository;

  private final BookRepository bookRepository;

  private final PublisherRepository publisherRepository;

  @Override
  public void run(String... args) {

    Author eric = new Author("Eric", "Evans");
    Book ddd = new Book("Domain Driven Design", "123123");
    eric.getBooks().add(ddd);
    ddd.getAuthors().add(eric);

    authorRepository.save(eric);
    bookRepository.save(ddd);

    Author rod = new Author("Rod", "Kohnson");
    Book noEJB = new Book("J2EE Development without EJB", "2346341");
    eric.getBooks().add(noEJB);
    ddd.getAuthors().add(rod);

    authorRepository.save(rod);
    bookRepository.save(noEJB);

    Publisher publisher = new Publisher("First Books", "", "Muster Straße 1", "01111", "Musterstadt");

    publisherRepository.save(publisher);

    ddd.setPublisher(publisher);
    publisher.getBooks().add(ddd);

    noEJB.setPublisher(publisher);
    publisher.getBooks().add(noEJB);

    publisherRepository.save(publisher);
    bookRepository.save(ddd);
    bookRepository.save(noEJB);

    log.info("Started in Bootstrap");
    log.info("Number of Books: " + bookRepository.count());
    log.info("Number of Authors: " + authorRepository.count());
    log.info("Number of Publishers: " + publisherRepository.count());
    log.info("Number of books assigned to Publisher: " + publisher.getBooks().size());
  }
}
