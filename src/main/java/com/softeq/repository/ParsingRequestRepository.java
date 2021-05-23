package com.softeq.repository;

import java.util.List;

import com.softeq.repository.entity.ParsingRequest;
/**
 * interface for working with ParsingRequest entity
 */
public interface ParsingRequestRepository {

    /**
     * add ParsingRequest to PersistenceContext and then to database
     * @param request
     * @return ParsingRequest
     */
    public ParsingRequest createRequest(ParsingRequest request);


}
