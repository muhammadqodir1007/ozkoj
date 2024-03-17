package uz.fazo.mapper;

import org.springframework.stereotype.Component;
import uz.fazo.entity.Event;
import uz.fazo.payload.EventDto;

@Component
public class EventMapperImpl implements EventMapper {
    @Override
    public EventDto eventToEventDto(Event event) {
        if (event == null) {
            return null;
        }
        return EventDto.builder().id(event.getId())
                .attendeeCount(event.getAttendeeCount())
                .photo(event.getPhoto())
                .status(event.getStatus())
                .name(event.getName())
                .comment(event.getComment())
                .type(event.getType())
                .build();


    }

    @Override
    public Event eventDtoEvent(EventDto eventDto) {

        if (eventDto == null) {
            return null;
        }
        return Event.builder().id(eventDto.getId())
                .attendeeCount(eventDto.getAttendeeCount())
                .photo(eventDto.getPhoto())
                .status(eventDto.getStatus())
                .name(eventDto.getName())
                .comment(eventDto.getComment())
                .type(eventDto.getType())
                .build();
    }
}
