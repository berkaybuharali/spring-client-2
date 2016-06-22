package org.pim;

import org.pim.config.ApplicationConfig;
import org.pim.model.Books;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        RestTemplate template = context.getBean(RestTemplate.class);

        HttpHeaders headers = new HttpHeaders();
        //headers.setAccept(Arrays.asList(MediaType.APPLICATION_XML));
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        Books books = template.exchange("http://localhost:8080/spring/bookList", HttpMethod.GET, entity, Books.class).getBody();

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
