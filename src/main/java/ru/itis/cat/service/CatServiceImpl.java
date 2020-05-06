package ru.itis.cat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import ru.itis.cat.clients.CatApiClient;
import ru.itis.cat.clients.CatClient;
import ru.itis.cat.entries.Cat;
import ru.itis.cat.repositories.CatRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CatServiceImpl implements CatService {

    @Autowired
    private List<CatClient> clients;

    @Autowired
    private CatRepository repository;

    @Override
    public Flux<Cat> getAll() {
        List<Flux<Cat>> fluxes = clients.stream().map(this::getAll).collect(Collectors.toList());
        return Flux.merge(fluxes);
    }

    private Flux<Cat> getAll(CatClient client) {
        return client.getAll().subscribeOn(Schedulers.elastic());
    }

    @Override
    public void saveAll(){
        List<Cat> cats = new ArrayList<>();

        for (long i = 0; i < 100000; i++) {
            cats.add(new Cat(i, "https://cdn2.thecatapi.com/images/ed3.gif", "Bad", "database"));
        }
        repository.saveAll(cats);
    }
}
