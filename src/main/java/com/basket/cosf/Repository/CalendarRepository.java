package com.basket.cosf.Repository;

import com.basket.cosf.Model.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CalendarRepository extends JpaRepository<Calendar, Integer> {

    /**
     *show all calendars for a specific season.
     */
    List<Calendar> findAllBySeasonId(Integer seasonId);
}
