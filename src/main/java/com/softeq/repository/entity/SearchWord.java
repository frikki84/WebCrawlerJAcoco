package com.softeq.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Additional information for ParsingRequest
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SearchWord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "word")
    private String searchWord;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parsing_request_id", nullable = false)
    private ParsingRequest request;

}
