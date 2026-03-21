package com.iac.onboarding.repository;

import com.iac.onboarding.model.UTM;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UTMRepository extends JpaRepository<UTM, Long> {

    UTM findByUtmCode(String utmCode);
}