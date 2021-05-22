package com.softeq.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.softeq.repository.ParsingRequestRepository;
import com.softeq.repository.entity.ParsingEntity;
import com.softeq.repository.entity.ParsingRequest;

@Repository
@Transactional
public class ParsingRequestRepositoryImpl implements ParsingRequestRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ParsingRequest createRequest(ParsingRequest request) {
        entityManager.persist(request);
        return request;
    }

    @Override
    public Long deleteRequest(Long requstId) {
        return null;
    }

    @Override
    public List<ParsingRequest> findAll() {
        return null;
    }

    @Override
    public ParsingRequest findRequest(Long id) {
        return entityManager.find(ParsingRequest.class, id);
    }
}
