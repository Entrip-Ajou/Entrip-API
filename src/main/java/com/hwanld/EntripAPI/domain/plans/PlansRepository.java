package com.hwanld.EntripAPI.domain.plans;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlansRepository extends JpaRepository <Plans, Long> {
    Optional<Plans> findByHashValue(Long hash_value);
}
