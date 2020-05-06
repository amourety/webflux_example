package ru.itis.cat.clients;

import reactor.core.publisher.Flux;
import ru.itis.cat.entries.Cat;

public interface CatClient {
    Flux<Cat> getAll();
}
