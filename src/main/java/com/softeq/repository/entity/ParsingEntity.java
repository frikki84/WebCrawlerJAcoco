package com.softeq.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "csv_entity")
public class ParsingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String url;
    @Column(name = "name")
    private String searchWord;
    @Column
    private long count;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parsing_entity", nullable = false)
    private ParsingRequest parsingRequest;

}
