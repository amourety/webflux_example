package ru.itis.cat.entries;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Cat {

    @Id
    @GeneratedValue
    private Long id;

    private String img;
    private String status;

    private String home;
}
