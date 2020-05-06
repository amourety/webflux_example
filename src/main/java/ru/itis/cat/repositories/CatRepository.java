package ru.itis.cat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.cat.entries.Cat;

@Repository
public interface CatRepository extends JpaRepository<Cat, Long> {
}
