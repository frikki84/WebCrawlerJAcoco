package com.softeq.service.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity for creating CsvEntity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchWordCountEntity {

    private String searchWord;
    private long count;

}
