package com.yapp.project.routine.controller;

import com.yapp.project.aux.common.AccountUtil;
import com.yapp.project.routine.domain.RoutineDTO;
import com.yapp.project.routine.domain.Week;
import com.yapp.project.routine.service.RoutineService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/routine")
public class RoutineController {

    private final RoutineService routineService;

    @ApiOperation(value = "루틴 추가", notes = "새로운 루틴 추가하기")
    @PostMapping("/")
    public RoutineDTO.ResponseRoutineDto createRoutine(@RequestBody RoutineDTO.RequestRoutineDto newRoutine) {
        return routineService.createRoutine(newRoutine, AccountUtil.getAccount());
    }

    @ApiOperation(value = "루틴 단일 조회", notes = "루틴ID로 루틴 조회하기")
    @GetMapping("/{routineId}")
    public RoutineDTO.ResponseRoutineDto getRoutine(@PathVariable Long routineId) {
        return routineService.getRoutine(routineId, AccountUtil.getAccount());
    }

    @ApiOperation(value = "루틴 요일별 전체 조회", notes = "요일별 루틴 전체 조회하기")
    @GetMapping("/day/{day}")
    public List<RoutineDTO.ResponseRoutineDto> getRoutineList(@PathVariable Week day) {
        return routineService.getRoutineList(day, AccountUtil.getAccount());
    }
}