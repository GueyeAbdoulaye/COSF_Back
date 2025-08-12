package com.basket.cosf.Service;

import com.basket.cosf.Dto.UserDto;
import com.basket.cosf.Model.Calendar;
import com.basket.cosf.Repository.CalendarRepository;
import com.basket.cosf.Service.Interface.AbstractService;
import com.basket.cosf.Validators.ObjectsValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CalendarService implements AbstractService<Calendar> {

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

    /**
     * Saves a Calendar entity.
     * but no implimentation yet.
     * @param dto
     * @return
     */
    @Override
    public Integer save(Calendar dto) {
        return 0;
    }

    /**
     * Finds all Calendar entities.
     * but no implimentation yet.
     * @return
     */
    @Override
    public List<Calendar> findAll() {
        return List.of();
    }

    /**
     * Finds a Calendar entity by its ID.
     * but no implimentation yet.
     * @param id
     * @return
     */
    @Override
    public Calendar findById(Integer id) {
        return null;
    }

    /**
     * Deletes a Calendar entity by its ID.
     * but no implimentation yet.
     * @param id
     */
    @Override
    public void delete(Integer id) {
    }
}
