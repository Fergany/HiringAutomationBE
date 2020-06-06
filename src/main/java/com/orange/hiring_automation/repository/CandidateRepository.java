package com.orange.hiring_automation.repository;

import com.orange.hiring_automation.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {

}
