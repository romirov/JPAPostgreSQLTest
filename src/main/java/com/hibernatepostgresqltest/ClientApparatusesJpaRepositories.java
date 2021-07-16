package com.hibernatepostgresqltest;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;

@Repository
@EnableConfigurationProperties
@Validated
interface ClientApparatusesJpaRepositories extends JpaRepository<Client, Long>  {
}

@Repository
@EnableConfigurationProperties
@Validated
interface ApparatusesJpaRepository extends JpaRepository<Apparatuses, Long>  {
}