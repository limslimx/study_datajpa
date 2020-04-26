package com.lim.datajpa.springboot.repository;

import com.lim.datajpa.springboot.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
