package com.softeq.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.softeq.repository.ParsingEntityRepository;
import com.softeq.repository.entity.ParsingEntity;

@Repository
@Transactional
public class ParsingEntityRepositoryImpl implements ParsingEntityRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ParsingEntity> findAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ParsingEntity> giftCertificateCriteriaQuery = criteriaBuilder.createQuery(
                ParsingEntity.class);
        Root<ParsingEntity> root = giftCertificateCriteriaQuery.from(ParsingEntity.class);
        giftCertificateCriteriaQuery.select(root);
        return entityManager.createQuery(giftCertificateCriteriaQuery)
                .getResultList();
    }

    @Override
    public ParsingEntity findById(long id) {
        return entityManager.find(ParsingEntity.class, id);
    }

    @Override
    public ParsingEntity create(ParsingEntity entity) {
        entityManager.persist(entity);
        return entity;
    }
}
