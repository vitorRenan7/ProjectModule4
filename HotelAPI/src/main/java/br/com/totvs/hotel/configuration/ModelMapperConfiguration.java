package br.com.totvs.hotel.configuration;

import org.modelmapper.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class ModelMapperConfiguration {
    Provider<LocalDate> localDateProvider = new AbstractProvider<LocalDate>() {
        @Override
        protected LocalDate get() {
            return LocalDate.now();
        }
    };

    Converter<String, LocalDate> localDateConverter = new AbstractConverter<String, LocalDate>() {
        @Override
        protected LocalDate convert(String s) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return LocalDate.parse(s, dateTimeFormatter);
        }
    };

    Provider<LocalDateTime> localDateTimeProvider = new AbstractProvider<LocalDateTime>() {
        @Override
        protected LocalDateTime get() {
            return LocalDateTime.now();
        }
    };

    Converter<String, LocalDateTime> localDateTimeConverter = new AbstractConverter<String, LocalDateTime>() {
        @Override
        protected LocalDateTime convert(String s) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            return LocalDateTime.parse(s, dateTimeFormatter);
        }
    };

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());

        modelMapper.createTypeMap(String.class, LocalDate.class);
        modelMapper.addConverter(localDateConverter);
        modelMapper.getTypeMap(String.class, LocalDate.class).setProvider(localDateProvider);

        modelMapper.createTypeMap(String.class, LocalDateTime.class);
        modelMapper.addConverter(localDateTimeConverter);
        modelMapper.getTypeMap(String.class, LocalDateTime.class).setProvider(localDateTimeProvider);

        return modelMapper;
    }

}
