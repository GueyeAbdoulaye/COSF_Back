package com.basket.cosf.Service;

import com.basket.cosf.Model.Calendar;
import com.basket.cosf.Repository.CalendarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CalendarService {

    private final CalendarRepository calendarRepository;


    public List<Calendar> getAllCalendars() {
        return calendarRepository.findAllByOrderByMatchDateDesc();
    }

    public List<Calendar> getCalendarsBySeasonId(Integer seasonId) {
        return calendarRepository.findAllBySeasonId(seasonId);
    }

    /**
     * Finds the Last matches.
     * @return Optional<Calendar> containing the last played match
     */
    public Optional<Calendar> getLastPlayedMatch() {
        return calendarRepository.findAllPlayedMatch(PageRequest.of(0, 50)).stream().findFirst();
    }

    /**
     * Finds all played matches.
     * @return List<Calendar> containing all played matches
     */
    public List<Calendar> getAllPlayedMatch() {
        return calendarRepository.findAllPlayedMatch(PageRequest.of(0, 50));
    }

    /**
     * Finds the Next matches.
     * @return Optional<Calendar> containing the next match
     */
    public Optional<Calendar> getnextPlayedMatch() {
        return calendarRepository.findAllMatchesComing(PageRequest.of(0, 1)).stream().findFirst();
    }

    /**
     * Finds all next matches.
     * @return List<Calendar> containing all next matches
     */
    public List<Calendar> getAllnextPlayedMatch() {
        return calendarRepository.findAllMatchesComing(PageRequest.of(0, 1));
    }


}
