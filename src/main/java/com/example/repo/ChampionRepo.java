package com.example.repo;

import com.example.entities.Champion;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class ChampionRepo implements PanacheRepository<Champion> {

}
