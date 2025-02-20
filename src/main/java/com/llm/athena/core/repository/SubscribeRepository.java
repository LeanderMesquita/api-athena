package com.llm.athena.core.repository;

import com.llm.athena.core.entity.Subscribe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SubscribeRepository extends JpaRepository<Subscribe, String>, JpaSpecificationExecutor<Subscribe> {
}
