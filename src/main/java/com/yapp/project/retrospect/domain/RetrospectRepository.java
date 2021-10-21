package com.yapp.project.retrospect.domain;

import com.yapp.project.routine.domain.Routine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface RetrospectRepository extends JpaRepository<Retrospect, Long> {
    Optional<Retrospect> findByRoutineAndDate(Routine routine, LocalDate date);
}
