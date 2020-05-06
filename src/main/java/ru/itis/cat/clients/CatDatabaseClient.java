package ru.itis.cat.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import ru.itis.cat.entries.Cat;
import ru.itis.cat.repositories.CatRepository;

import java.util.List;

@Component
public class CatDatabaseClient implements CatClient {

    @Autowired
    private CatRepository repository;


    @Override
    public Flux<Cat> getAll() {
        List<Cat> records = repository.findAll();
        return Flux.fromIterable(records);
    }
}
