package org.example.springpracticeschedule.repository;

import org.example.springpracticeschedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
