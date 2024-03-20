package mk.ukim.finki.emt.lab.config;

import mk.ukim.finki.emt.lab.listeners.BookEventListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public BookEventListener bookEventListener() {
        return new BookEventListener();
    }
}
