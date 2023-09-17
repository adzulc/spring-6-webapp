package io.dzul.spring6webapp.boot;

import io.dzul.spring6webapp.domain.Author;
import io.dzul.spring6webapp.domain.Book;
import io.dzul.spring6webapp.domain.Publisher;
import io.dzul.spring6webapp.repositories.AuthorRepository;
import io.dzul.spring6webapp.repositories.BookRepository;
import io.dzul.spring6webapp.repositories.PublisherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    @Override
    public void run(String... args) throws Exception {

        var publisher = new Publisher();
        publisher.setAddress("123 Fake");
        publisher.setCity("Pitts");
        publisher.setState("PA");
        publisher.setZipcode("15206");

        publisherRepository.save(publisher);

//      first

        var eric = new Author();
        eric.setFirstName("Evan");
        eric.setLastName("Eric");

        var ddd = new Book();
        ddd.setTitle("Domain Driven Design");
        ddd.setIsbn("123345");
        ddd.setPublisher(publisher);

        var ericSaved = authorRepository.save(eric);
        var dddSaved = bookRepository.save(ddd);

        ericSaved.getBooks().add(ddd);
        dddSaved.getAuthors().add(ericSaved);

        ericSaved = authorRepository.save(eric);
        dddSaved = bookRepository.save(ddd);

//      second

        var rod = new Author();
        rod.setFirstName("Rod");
        rod.setLastName("Johnson");

        var noEJB = new Book();
        noEJB.setTitle("J2EE Development without EJB");
        noEJB.setIsbn("987564123");
        noEJB.setPublisher(publisher);

        var rodSaved = authorRepository.save(rod);
        var noEJBSaved = bookRepository.save(noEJB);

        rodSaved.getBooks().add(noEJBSaved);
        noEJB.getAuthors().add(rodSaved);

        rodSaved = authorRepository.save(rod);
        noEJBSaved = bookRepository.save(noEJB);

        log.info("Author: {}", authorRepository.count());
        log.info("Books: {}", bookRepository.count());
        log.info("Publishers: {}", publisherRepository.count());


    }
}
