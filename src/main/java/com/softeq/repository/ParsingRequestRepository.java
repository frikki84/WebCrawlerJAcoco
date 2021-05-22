package com.softeq.repository;

import java.util.List;

import com.softeq.repository.entity.ParsingRequest;

public interface ParsingRequestRepository {
    public ParsingRequest createRequest(ParsingRequest request);
    public Long deleteRequest(Long requstId);
    public List<ParsingRequest> findAll();
    public ParsingRequest findRequest(Long id);

}
