package org.pim;

import org.pim.config.ApplicationConfig;
import org.pim.model.Books;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.client.RestTemplate;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        RestTemplate template = context.getBean(RestTemplate.class);

        Books books = template.getForObject("http://localhost:8080/spring/bookList", Books.class);

        System.out.println("Berkay Bookstore");
        for (org.pim.model.Book book : books.getBooks()) {
            System.out.println(book.getTitle() + " " + book.getAuthor());
        }

//        BookList bookList = template.getForObject("http://192.168.78.20:8080/spring/example/booksXml", BookList.class);
//        System.out.println("Guido Bookstore");
//        for (org.pim.guido.model.Book book : bookList.getBook()) {
//            System.out.println(book.getTitle() + " " + book.getAuthor());
//        }
    }
}
