package com.basket.cosf.Repository;

import com.basket.cosf.Model.Standing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StandingRepository extends JpaRepository<Standing, Integer> {

    /**
     * Find all standings by season ID Order by point Asc.
     * @param seasonId the ID of the season
     * @return a list of standings for the specified season
     */
    @Query("SELECT s FROM Standing s Where seasonId = :seasonId ORDER BY s.points DESC, s.pointDifference DESC, s.pointScored DESC")
    List<Standing> findAllBySeasonId(Integer seasonId);
}
