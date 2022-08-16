package com.example.dispatch.repository;

import com.example.inverontoryservice.model.Allocation;
import com.example.inverontoryservice.model.CurrentStatus;
import com.example.order.model.Orders;
import com.example.schedule.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DispatchRepository extends JpaRepository<Schedule, Integer> {

    List<Schedule> findScheduleByScheduled(CurrentStatus currentStatus);

    Schedule findScheduleByOrderGasStationId(int id);
}
