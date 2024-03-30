package uz.fazo.service;

import uz.fazo.payload.EventDto;

import java.util.List;

public interface EventService {

    List<EventDto> getAll();


    List<EventDto> getAllByUserId(int id);

    EventDto getById(long id);

    EventDto create(EventDto eventDto);

    EventDto update(long id, EventDto eventDto);

    void delete(long id);
}
