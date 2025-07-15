package com.basket.cosf.Repository;

import com.basket.cosf.Model.Teams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamsRepository extends JpaRepository<Teams,Integer> {

    /**
     * Trouve toutes les équipes.
     *
     * @return une liste de toutes les équipes
     */
    List<Teams> findAll();
}
