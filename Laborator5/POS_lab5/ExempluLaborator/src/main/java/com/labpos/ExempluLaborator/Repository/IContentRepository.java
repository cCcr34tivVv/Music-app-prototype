package com.labpos.ExempluLaborator.Repository;

import com.labpos.ExempluLaborator.Entity.Content;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IContentRepository extends JpaRepository<Content, Integer>, PagingAndSortingRepository<Content, Integer> {
    @Query(value = "SELECT * FROM content WHERE id = ?1", nativeQuery = true)
    Content getById(int id);

    @Query(value = "SELECT * FROM content WHERE name LIKE %?1%", nativeQuery = true)
    List<Content> getByNamePartial(String name);

    @Query(value = "SELECT * FROM content WHERE name = ?1", nativeQuery = true)
    List<Content> getByNameStrict(String name);

    @Query(value = "SELECT * FROM content WHERE genre = ?1", nativeQuery = true)
    List<Content> getByGenre(String genre);

    @Query(value = "SELECT * FROM content WHERE year = ?1", nativeQuery = true)
    List<Content> getByYear(int year);

    @Query(value = "SELECT * FROM content WHERE type = ?1", nativeQuery = true)
    List<Content> getByType(String type);

    @Query(value = "SELECT * FROM content WHERE name = ?1 AND genre = ?2 AND year = ?3 AND type = ?4", nativeQuery = true)
    List<Content> getByAll(String name, String genre, int year, String type);

    @Query(value = "SELECT * FROM content", nativeQuery = true)
    List<Content> getAll();

    @Query(value = "UPDATE content SET name = ?1 WHERE id = ?2", nativeQuery = true)
    void updateName(String name, int id);

    @Query(value = "UPDATE content SET genre = ?1 WHERE id = ?2", nativeQuery = true)
    void updateGenre(String genre, int id);

    @Query(value = "UPDATE content SET year = ?1 WHERE id = ?2", nativeQuery = true)
    void updateYear(Integer year, int id);

}
