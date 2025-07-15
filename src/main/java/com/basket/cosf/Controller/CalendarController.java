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
     * Show all calendars for a specific season.
     */
     @GetMapping("/{seasonId}/calendars")
    public List<Calendar> getCalendarsBySeasonId(@PathVariable Integer seasonId) {
        return calendarService.getCalendarsBySeasonId(seasonId);
    }

    /**
     * Show a specific calendar by its ID.
     */
    @GetMapping("/{calendarId}")
    public Calendar getCalendarById(@PathVariable Integer calendarId) {
        return calendarService.getCalendarById(calendarId);
    }

    /**
     * Show all calendars.
     */
    @GetMapping("/all")
    public List<Calendar> getAllCalendars() {
        return calendarService.getAllCalendars();
    }

}
