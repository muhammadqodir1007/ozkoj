package uz.fazo.mapper;

import org.mapstruct.Mapper;
import uz.fazo.entity.Event;
import uz.fazo.payload.EventDto;

@Mapper
public interface EventMapper {


    EventDto eventToEventDto(Event event);

    Event eventDtoEvent(EventDto eventDto);

}
