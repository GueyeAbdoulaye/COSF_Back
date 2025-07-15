package com.basket.cosf.Service;

import com.basket.cosf.Model.Calendar;
import com.basket.cosf.Repository.CalendarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalendarService {

    private final CalendarRepository calendarRepository;

    public CalendarService(CalendarRepository calendarRepository) {
        this.calendarRepository = calendarRepository;
    }

    public List<Calendar> getCalendarsBySeasonId(Integer seasonId) {
        return calendarRepository.findAllBySeasonId(seasonId);
    }

    public Calendar getCalendarById(Integer calendarId) {
        return calendarRepository.findById(calendarId)
                .orElseThrow(() -> new RuntimeException("Calendar not found with id: " + calendarId));
    }

//    public Calendar saveCalendar(Calendar calendar) {
//        return calendarRepository.save(calendar);
//    }
//
//    public void deleteCalendar(Integer calendarId) {
//        if (!calendarRepository.existsById(calendarId)) {
//            throw new RuntimeException("Calendar not found with id: " + calendarId);
//        }
//        calendarRepository.deleteById(calendarId);
//    }

    public List<Calendar> getAllCalendars() {
        return calendarRepository.findAll();
    }

}
