package uz.fazo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.fazo.payload.EventDto;
import uz.fazo.service.EventService;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public ResponseEntity<List<EventDto>> getAllEvents() {
        List<EventDto> events = eventService.getAll();
        return ResponseEntity.ok(events);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<EventDto>> getAllEventsByUserId(@PathVariable int id) {
        List<EventDto> events = eventService.getAllByUserId(id);
        return ResponseEntity.ok(events);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDto> getEventById(@PathVariable long id) {
        EventDto event = eventService.getById(id);
        return ResponseEntity.ok(event);
    }

    @PostMapping
    public ResponseEntity<EventDto> createEvent(@RequestBody EventDto eventDto) {
        EventDto createdEvent = eventService.create(eventDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEvent);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EventDto> updateEvent(@PathVariable int id, @RequestBody EventDto eventDto) {
        EventDto update = eventService.update(id, eventDto);
        return ResponseEntity.ok(update);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable long id) {
        eventService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
