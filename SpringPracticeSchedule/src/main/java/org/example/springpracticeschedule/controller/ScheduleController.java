package org.example.springpracticeschedule.controller;

import lombok.RequiredArgsConstructor;
import org.example.springpracticeschedule.dto.ScheduleRequest;
import org.example.springpracticeschedule.dto.ScheduleResponse;
import org.example.springpracticeschedule.service.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/schedules")
    public ResponseEntity<ScheduleResponse> createSchedule(
            @RequestBody ScheduleRequest request
    ){
        return ResponseEntity.ok(scheduleService.save(request));
    }

    @GetMapping("/schedules")
    public ResponseEntity<List<ScheduleResponse>> getSchedules(){
        return ResponseEntity.ok(scheduleService.findSchedules());
    }

    @GetMapping("/schedules/{scheduleId}")
    public ResponseEntity<ScheduleResponse> getSchedule(
            @PathVariable Long scheduleId
    ) {
        return ResponseEntity.ok(scheduleService.findSchedule(scheduleId));
    }


    @PutMapping("/Schedules/{scheduleId}")
    public ResponseEntity<ScheduleResponse> updateSchedule(
            @PathVariable Long scheduleId,
            @RequestBody ScheduleRequest request
    ) {
        return ResponseEntity.ok(scheduleService.updateSchedule(scheduleId, request));
    }


    @DeleteMapping("/schedules/{scheduleId}")
    public void deleteSchedule(
            @PathVariable Long scheduleId
    ){
        scheduleService.deleteSchedule(scheduleId);
    }
}
