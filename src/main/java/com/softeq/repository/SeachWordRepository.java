package com.softeq.repository;

import com.softeq.repository.entity.SearchWord;

/**
 * interface for working with SeachWord entity
 */
public interface SeachWordRepository {

    /**
     * add SeachWord to PersistenceContext and then to database
     * @param searchWord
     * @return SeachWord
     */
    public SearchWord create(SearchWord searchWord);


}
