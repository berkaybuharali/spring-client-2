package org.pim.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ApplicationConfig {

    @Bean
    RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        MarshallingHttpMessageConverter bookConverter = new MarshallingHttpMessageConverter();
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();

        marshaller.setClassesToBeBound(org.pim.model.Book.class, org.pim.model.Books.class);
        // Run Guido's add org.pim.guido.model.BookList and org.pim.guido.model.Book
        bookConverter.setMarshaller(marshaller);
        bookConverter.setUnmarshaller(marshaller);
        List<HttpMessageConverter<?>> converterList = new ArrayList<HttpMessageConverter<?>>();
        converterList.add(bookConverter);
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        converterList.add(jsonConverter);
        restTemplate.setMessageConverters(converterList);
        return restTemplate;
    }
}
