package com.softeq.service.entity;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity for showing info to user on screen or creation csv-file
 * Entities are comparable by fullCount
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CsvEntity implements Comparable<CsvEntity> {

    private String url;
    private List<SearchWordCountEntity> searches = new ArrayList<>();
    private long fullCount;

    @Override
    public int compareTo(CsvEntity csvEntity) {
        if (this.fullCount == csvEntity.fullCount) {
            return 0;
        } else if (this.fullCount < csvEntity.fullCount) {
            return -1;
        } else {
            return 1;
        }
    }
}
