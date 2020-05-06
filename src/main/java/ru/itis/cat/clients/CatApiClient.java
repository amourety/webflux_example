package ru.itis.cat.clients;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import ru.itis.cat.entries.Cat;
import ru.itis.cat.entries.CatRecord;

import java.util.Arrays;

@Component
public class CatApiClient implements CatClient{

    private WebClient client;

    public CatApiClient(@Value("${dog.api.url}") String url) {
        client = WebClient.builder()
                .exchangeStrategies(ExchangeStrategies.builder()
                        .codecs(clientCodecConfigurer -> clientCodecConfigurer.defaultCodecs().maxInMemorySize(100 * 1024 * 1024))
                        .build())
                .baseUrl(url)
                .build();
    }

    @Override
    public Flux<Cat> getAll() {
        return client.get()
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .flatMap(clientResponse -> clientResponse.bodyToMono(CatRecord[].class)) // преобразуем данные со стороннего сервреа в Publisher
                .flatMapIterable(Arrays::asList)
                .map(record ->
                        Cat.builder()
                                .img(record.getUrl())
                                .status("Good")
                                .home("api.thecatapi.com")
                                .build());
    }
}
