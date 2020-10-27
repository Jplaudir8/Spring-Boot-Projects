package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.domain.Schedule;

import java.util.List;

public interface ScheduleService {
    Schedule save(Schedule schedule);
    List<Schedule> getAllSchedules();
}
