package ru.itis.cat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.itis.cat.entries.Cat;
import ru.itis.cat.service.CatService;

@RestController
@RequestMapping("/cat")
public class CatController {

    @Autowired
    private CatService catService;

    @GetMapping(value = "/records", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Cat> getAll() {
        return catService.getAll();
    }

    @GetMapping(value = "/set_db")
    public void setDB(){
        catService.saveAll();
    }
}
