package ru.itis.cat.service;

import reactor.core.publisher.Flux;
import ru.itis.cat.entries.Cat;


public interface CatService {
    Flux<Cat> getAll();
    void saveAll();

}
