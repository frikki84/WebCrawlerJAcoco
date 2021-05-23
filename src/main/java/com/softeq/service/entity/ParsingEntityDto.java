package com.softeq.service.entity;

import com.softeq.repository.entity.ParsingRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO- entity, used between ParsingEntity and CsvEntity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParsingEntityDto {

    private long id;
    private String url;
    private String searchWord;
    private long count;
    private ParsingRequest request;
}
