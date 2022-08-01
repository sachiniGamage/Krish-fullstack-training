package com.example.dispatch.repository;

import com.example.inverontoryservice.model.Allocation;
import com.example.inverontoryservice.model.CurrentStatus;
import com.example.schedule.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DispatchRepository extends JpaRepository<Schedule, Integer> {

//    List<Schedule> findByScheduled(CurrentStatus currentStatus);
    List<Schedule> findScheduleByScheduled(CurrentStatus currentStatus);
}
