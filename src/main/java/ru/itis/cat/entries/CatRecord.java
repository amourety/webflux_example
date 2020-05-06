package ru.itis.cat.entries;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;



@Data
public class CatRecord {

        @JsonProperty("Id")
        private String id;
        @JsonProperty("url")
        private String url;
}
