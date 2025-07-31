package org.example.springpracticeschedule.service;

import lombok.RequiredArgsConstructor;
import org.example.springpracticeschedule.dto.ScheduleRequest;
import org.example.springpracticeschedule.dto.ScheduleResponse;
import org.example.springpracticeschedule.entity.Schedule;
import org.example.springpracticeschedule.repository.ScheduleRepository;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Transactional
    public ScheduleResponse save(ScheduleRequest scheduleRequest){
        Schedule schedule = new Schedule(
                scheduleRequest.getTitle(),
                scheduleRequest.getContent(),
                scheduleRequest.getAuthor(),
                scheduleRequest.getPassword()
                );
        Schedule savedSchedule = scheduleRepository.save(schedule);

        return new ScheduleResponse(
                savedSchedule.getId(),
                savedSchedule.getTitle(),
                savedSchedule.getAuthor(),
                savedSchedule.getContent(),
                savedSchedule.getCreatedAt(),
                savedSchedule.getModifiedAt()
        );
    }

    @Transactional(readOnly = true)
    public List<ScheduleResponse> findSchedules(){
        List<Schedule> schedules = scheduleRepository.findAll();
        return schedules.stream()
                .map(schedule -> new ScheduleResponse(
                        schedule.getId(),
                        schedule.getTitle(),
                        schedule.getContent(),
                        schedule.getAuthor(),
                        schedule.getCreatedAt(),
                        schedule.getModifiedAt()
                ))
                .toList();
    }

    @Transactional(readOnly = true)
    public ScheduleResponse findSchedule(Long scheduleId){
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 일정입니다.")
        );
        return new ScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getAuthor(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }

    @Transactional
    public ScheduleResponse updateSchedule(Long scheduleId, ScheduleRequest request){
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 일정입니다.")
        );
        schedule.updateSchedule(request.getTitle(), request.getAuthor(), request.getContent());
        return new ScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getAuthor(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }

    @Transactional
    public void deleteSchedule(Long scheduleId){
        boolean b = scheduleRepository.existsById(scheduleId);
        if (!b) {
            throw new IllegalArgumentException("존재하지 않는 일정입니다.");
        }
        scheduleRepository.deleteById(scheduleId);
    }
}
