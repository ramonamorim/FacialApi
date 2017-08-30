package br.com.facial.persistencia;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface BaseRepositorio<T extends BaseEntidade> extends JpaRepository<T, UUID>, QueryDslPredicateExecutor<T> {

}
