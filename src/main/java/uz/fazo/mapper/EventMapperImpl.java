package uz.fazo.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import uz.fazo.entity.Event;
import uz.fazo.payload.EventDto;
import uz.fazo.user.User;
import uz.fazo.user.UserRepository;

@Component
@AllArgsConstructor
public class EventMapperImpl implements EventMapper {


    UserRepository userRepository;


    @Override
    public EventDto eventToEventDto(Event event) {
        if (event == null) {
            return null;
        }
        return EventDto.builder().id(event.getId())
                .attendeeCount(event.getAttendeeCount())
                .photo(event.getPhoto())
                .userId(event.getUser().getId())
                .status(event.getStatus())
                .userId(event.getUser().getId())
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
        User user = userRepository.findById(eventDto.getUserId()).orElseThrow(NullPointerException::new);

        return Event.builder().id(eventDto.getId())
                .attendeeCount(eventDto.getAttendeeCount())
                .photo(eventDto.getPhoto())
                .user(user)
                .status(eventDto.getStatus())
                .name(eventDto.getName())
                .comment(eventDto.getComment())
                .type(eventDto.getType())
                .build();
    }

    @Override
    public Event updateEventDtoToEvent(Event event, EventDto eventDto) {
        if (event == null || eventDto == null) {
            return null;
        }
        if (eventDto.getAttendeeCount() != 0) {
            event.setAttendeeCount(eventDto.getAttendeeCount());
        }
        if (eventDto.getComment() != null) {
            event.setAttendeeCount(eventDto.getAttendeeCount());
        }
        if (eventDto.getStatus() != null) {
            event.setStatus(eventDto.getStatus());
        }
        if (eventDto.getName() != null) {
            event.setName(eventDto.getName());
        }
        if (eventDto.getPhoto() != null) {
            event.setPhoto(eventDto.getPhoto());
        }
        if (eventDto.getType() != null) {
            event.setType(event.getType());
        }
        return event;
    }


}
