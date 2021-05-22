package com.softeq.repository;

import java.util.List;

import com.softeq.repository.entity.ParsingEntity;

public interface ParsingEntityRepository {
    public List<ParsingEntity> findAll();

    public ParsingEntity findById(long id);

    public ParsingEntity create(ParsingEntity entity);

}
