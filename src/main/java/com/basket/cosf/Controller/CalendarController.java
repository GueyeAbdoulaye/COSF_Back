package com.basket.cosf.Controller;

import com.basket.cosf.Model.Calendar;
import com.basket.cosf.Service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/calendar")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CalendarController {

    @Autowired
    private final CalendarService calendarService;

    /**
     * Show all calendars.
     */
    @GetMapping("/all")
    public List<Calendar> getAllCalendars() {
        return calendarService.getAllCalendars();
    }

    /**
     * Show all calendars for a specific season.
     */
     @GetMapping("/{seasonId}")
    public List<Calendar> getCalendarsBySeasonId(@PathVariable Integer seasonId) {
        return calendarService.getCalendarsBySeasonId(seasonId);
    }


    /**
     * Show the last played match.
     */
    @GetMapping("/last")
    public Calendar getLastPlayedMatch() {
        return calendarService.getLastPlayedMatch().orElse(null);
    }

    /**
     * Show the next match.
     */
    @GetMapping("/next")
    public Calendar getNextPlayedMatch() {
        return calendarService.getnextPlayedMatch().orElse(null);
    }

    /**
     * Show all next matches.
     */
    @GetMapping("/upcoming")
    public List<Calendar> getAllnextPlayedMatch() {
        return calendarService.getAllnextPlayedMatch();
    }

    /**
     * Show all played matches.
     */
    @GetMapping("/allplayed")
    public List<Calendar> getAllPlayedMatch() {
        return calendarService.getAllPlayedMatch();
    }
}
