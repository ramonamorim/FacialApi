package br.com.facial.persistence;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface BaseRepo<T extends BaseEntity> extends JpaRepository<T, UUID>, QueryDslPredicateExecutor<T> {

}
