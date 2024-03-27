package uz.fazo.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import uz.fazo.entity.Event;
import uz.fazo.exceptions.NotFoundException;
import uz.fazo.mapper.EventMapper;
import uz.fazo.payload.EventDto;
import uz.fazo.repository.EventRepository;
import uz.fazo.service.EventService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    @Override
    public List<EventDto> getAll() {
        return eventRepository.findAll().stream().map(eventMapper::eventToEventDto).collect(Collectors.toList());
    }

    @Override
    public EventDto getById(long id) {
        return eventMapper.eventToEventDto(eventRepository.findById(id).orElseThrow(NotFoundException::new));
    }

    @Override
    public EventDto create(EventDto eventDto) {
        Event event = eventMapper.eventDtoEvent(eventDto);
        Event savedEvent = eventRepository.save(event);
        return eventMapper.eventToEventDto(savedEvent);
    }

    @Override
    public EventDto update(long id, EventDto eventDto) {
        Event event = eventRepository.findById(id).orElseThrow(NullPointerException::new);
        Event event1 = eventMapper.updateEventDtoToEvent(event, eventDto);
        return eventMapper.eventToEventDto(eventRepository.save(event1));
    }

    @Override
    public void delete(long id) {
        eventRepository.deleteById(id);
    }
}
