package com.basket.cosf.Repository;

import com.basket.cosf.Model.Calendar;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CalendarRepository extends JpaRepository<Calendar, Integer> {

    /**
     * Finds all calendars by season ID.
     * @param seasonId
     * @return
     */
    List<Calendar> findAllBySeasonId(Integer seasonId);

    /**
     * Finds all played matches in Desc.
     * @return
     */
    @Query("SELECT c FROM Calendar c  ORDER BY c.matchDate DESC")

    List<Calendar> findAllByOrderByMatchDateDesc();
    /**
     * Finds all played match.
     * @param pageable
     * @return
     */
    @Query("SELECT c FROM Calendar c WHERE c.matchDate < CURRENT_DATE ORDER BY c.matchDate DESC")
    List<Calendar> findAllPlayedMatch(Pageable pageable);

    @Query("SELECT c FROM Calendar c WHERE c.matchDate > CURRENT_DATE ORDER BY c.matchDate ASC")
    List<Calendar> findAllMatchesComing(Pageable pageable);
}
