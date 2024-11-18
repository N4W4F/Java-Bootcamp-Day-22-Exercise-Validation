package com.example.eventmanager.Controller;

import com.example.eventmanager.ApiResponse.ApiResponse;
import com.example.eventmanager.Model.Event;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/event")
public class EventController {

    ArrayList<Event> events = new ArrayList<>();

    @PostMapping("/create-event")
    public ResponseEntity createEvent(@RequestBody @Valid Event event, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        events.add(event);
        return ResponseEntity.status(200).body(new ApiResponse("Event has been created successfully"));
    }

    @GetMapping("/display-events")
    public ResponseEntity displayEvents() {
        return ResponseEntity.status(200).body(events);
    }

    @PutMapping("/update-event/{index}")
    public ResponseEntity updateEvent(@PathVariable int index, @RequestBody @Valid Event event, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        events.set(index, event);
        return ResponseEntity.status(200).body(new ApiResponse("Event at index " + index + " has been updated successfully"));
    }

    @DeleteMapping("/delete-event/{index}")
    public ResponseEntity deleteEvent(@PathVariable int index) {
        events.remove(index);
        return ResponseEntity.status(200).body(new ApiResponse("Event at index " + index + " has been deleted successfully"));
    }

    @PutMapping("/change-capacity/{index}")
    public ResponseEntity changeCapacity (@PathVariable int index, @RequestBody @Valid int newCapacity, Errors errors) {
        if(errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        events.get(index).setCapacity(events.get(index).getCapacity() - newCapacity);
        return ResponseEntity.status(200).body(new ApiResponse("Event capacity at index " + index + " has been changed successfully"));
    }

    @GetMapping("/get-event/{id}")
    public ResponseEntity getEventById(@PathVariable String id) {
        for (Event event : events)
            if (event.getId().equals(id))
                return ResponseEntity.status(200).body(event);

        return ResponseEntity.status(404).body(new ApiResponse("Event not found"));
    }
}
