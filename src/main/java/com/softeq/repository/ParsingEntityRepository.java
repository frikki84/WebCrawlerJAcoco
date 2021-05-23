package com.softeq.repository;

import java.util.List;

import com.softeq.repository.entity.ParsingEntity;

/**
 * interface for working with ParsingEntity
 */
public interface ParsingEntityRepository {

    /**
     * find all infornation from database about ParsingEntity
     * @return List<ParsingEntity>
     */
    public List<ParsingEntity> findAll();

    /**
     * find ParsingEntity by id
     * @param id
     * @return ParsingEntity
     */
    public ParsingEntity findById(long id);

    /**
     * add ParsingEntity to PersistenceContext and then to database
     * @param entity
     * @return
     */
    public ParsingEntity create(ParsingEntity entity);

}
