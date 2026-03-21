package com.iac.onboarding.repository;

import com.iac.onboarding.model.Tracking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackingRepository extends JpaRepository<Tracking, Long> {
}