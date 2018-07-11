package me.markakis.demo.datadiff.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.markakis.demo.datadiff.domain.models.Diff;

/**
 * This interface is used to automatically create the implementation of the
 * DiffRepository (DAO) and includes the CRUD operations inherited by
 * JpaRepository as well as custom defined queries.
 * 
 * @author marqui
 */
@Repository
public interface DiffRepository extends JpaRepository<Diff, Integer> {

}
