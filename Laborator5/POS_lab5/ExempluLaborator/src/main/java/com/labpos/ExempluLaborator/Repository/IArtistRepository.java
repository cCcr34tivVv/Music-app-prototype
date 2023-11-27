package com.labpos.ExempluLaborator.Repository;

import com.labpos.ExempluLaborator.Entity.Artist;
import com.labpos.ExempluLaborator.Entity.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IArtistRepository extends JpaRepository<Artist, String> {
    @Query(value = "SELECT * FROM artists WHERE uuid = ?1", nativeQuery = true)
    List<Artist> getByUuid(String uuid);

    @Query(value = "SELECT * FROM artists WHERE name LIKE %?1%", nativeQuery = true)
    List<Artist> getByNamePartial(String name);

    @Query(value = "SELECT * FROM artists WHERE name = ?1", nativeQuery = true)
    List<Artist> getByNameStrict(String name);

    @Query(value = "SELECT * FROM artists", nativeQuery = true)
    List<Artist> getAll();

    @Query(value = "SELECT * FROM artists WHERE active = ?1", nativeQuery = true)
    Artist getByActive(boolean active);

    @Query(value = "UPDATE artists SET name = ?1 WHERE uuid = ?2", nativeQuery = true)
    void updateName(String name, String uuid);

    @Query(value = "UPDATE artists SET active = ?1 WHERE uuid = ?2", nativeQuery = true)
    void updateActive(boolean active_ceva, String uuid);
}
