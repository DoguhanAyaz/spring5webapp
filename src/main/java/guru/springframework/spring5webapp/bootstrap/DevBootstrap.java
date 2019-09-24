package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    private void initData() {
        Publisher beyazdis = new Publisher("Beyaz Dis", "Bakırköy");
        publisherRepository.save(beyazdis);

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("DDD", "123,", beyazdis);

        //Eric
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric);
        bookRepository.save(ddd);

        //Rod

        Publisher NT = new Publisher("NT", "Beyazıt");
        publisherRepository.save(NT);

        Author dogu = new Author("Rod", "Wesley");
        Book noJEB = new Book("j2ee Development", "2312", NT);

        dogu.getBooks().add(noJEB);
        noJEB.getAuthors().add(dogu);

        authorRepository.save(dogu);
        bookRepository.save(noJEB);


    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }
}
