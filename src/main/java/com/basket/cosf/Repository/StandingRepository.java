package com.basket.cosf.Repository;

import com.basket.cosf.Model.Standing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StandingRepository extends JpaRepository<Standing, Integer> {
    List<Standing> findAllBySeasonId(Integer seasonId);
}
