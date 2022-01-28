package com.backbase.technical.challenge.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    Optional<Movie> findByTitle(String title);
    @Transactional
    @Modifying
    @Query("delete from Movie m where m.boxOffice=:wrongBoxOffice")
    void deleteInvalidData(String wrongBoxOffice);
}
