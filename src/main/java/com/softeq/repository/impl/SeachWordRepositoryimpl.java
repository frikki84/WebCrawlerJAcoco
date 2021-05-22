package com.softeq.repository.impl;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.softeq.repository.SeachWordRepository;
import com.softeq.repository.entity.SearchWord;

@Component
public class SeachWordRepositoryimpl implements SeachWordRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public SearchWord create(SearchWord searchWord) {
        entityManager.persist(searchWord);
        return searchWord;
    }
}
