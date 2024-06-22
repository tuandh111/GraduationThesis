package com.DuAn.DuAnTotNghiep.repositories;

import com.DuAn.DuAnTotNghiep.entities.TimeOfShift;
import jakarta.websocket.server.PathParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeOfShiftRepository extends JpaRepository<TimeOfShift,Integer> {
    @Query("select tos from TimeOfShift tos where tos.shift.shiftId=:id")
    List<TimeOfShift> getTimeOfShiftByShift(@Param("id") Integer id);
}
