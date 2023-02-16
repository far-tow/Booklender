package se.lexicon.G4.Booklender.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Book Lender Group 4", version = "0.1", description = "Book Lender API Information"))
public class appConfig {

    @Bean
    public ModelMapper modelMapper() {

        return new ModelMapper();
    }
}
