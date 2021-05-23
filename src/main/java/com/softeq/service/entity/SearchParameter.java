package com.softeq.service.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity which takes info for parsing from user
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchParameter {
    private String url;
    private List<String> searchWord;

}
