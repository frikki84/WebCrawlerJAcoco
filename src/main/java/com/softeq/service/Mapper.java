package com.softeq.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.softeq.repository.entity.ParsingEntity;
import com.softeq.repository.entity.SearchWord;
import com.softeq.service.entity.CsvEntity;
import com.softeq.service.entity.ParsingEntityDto;
import com.softeq.service.entity.SearchParameter;
import com.softeq.service.entity.SearchWordCountEntity;

@Component
public class Mapper {

    public ParsingEntity changeParsingEntityDtoToParsingEntity(ParsingEntityDto dto) {
        ParsingEntity parsingEntity = new ParsingEntity();
        parsingEntity.setId(dto.getId());
        parsingEntity.setSearchWord(dto.getSearchWord());
        parsingEntity.setUrl(dto.getUrl());
        parsingEntity.setCount(dto.getCount());
        parsingEntity.setParsingRequest(dto.getRequest());
        return parsingEntity;
    }

    public ParsingEntityDto changeParsingEntityToParsingEntityDto(ParsingEntity entity) {
        ParsingEntityDto dto = new ParsingEntityDto();
        dto.setSearchWord(entity.getSearchWord());
        dto.setUrl(entity.getUrl());
        dto.setCount(entity.getCount());
        dto.setRequest(entity.getParsingRequest());
        return dto;
    }

    public List<ParsingEntityDto> changeListOfParsingEntityToParsingEntityDto(List<ParsingEntity> entities) {
        List<ParsingEntityDto> dtos = new LinkedList<>();
        for (ParsingEntity entity : entities) {
            dtos.add(changeParsingEntityToParsingEntityDto(entity));
        }
        return dtos;
    }

    public List<ParsingEntity> changeListOfParsingEntityDtoToParsingEntity(List<ParsingEntityDto> dtos) {
        List<ParsingEntity> entities = new LinkedList<>();
        for (ParsingEntityDto dto : dtos) {
            entities.add(changeParsingEntityDtoToParsingEntity(dto));
        }
        return entities;
    }

    public List<CsvEntity> changeListParsingEntitiesDtoToCsv(List<ParsingEntityDto> dtos) {
        List<CsvEntity> csvEntities = new ArrayList<>();
        CsvEntity entity = new CsvEntity();
        entity.setUrl(dtos.get(0).getUrl());
        entity.getSearches().add(new SearchWordCountEntity(dtos.get(0).getSearchWord(), dtos.get(0).getCount()));
        dtos.remove(0);
        for (ParsingEntityDto dto : dtos) {
            if (dto.getUrl().equals(entity.getUrl())) {
                entity.getSearches().add(new SearchWordCountEntity(dto.getSearchWord(), dto.getCount()));
            } else {
                long fullCount = 0;
                for (SearchWordCountEntity item : entity.getSearches()) {
                    fullCount += item.getCount();
                }
                entity.setFullCount(fullCount);
                csvEntities.add(entity);

                entity = new CsvEntity();
                entity.setUrl(dto.getUrl());
                entity.getSearches().add(new SearchWordCountEntity(dto.getSearchWord(), dto.getCount()));
            }
        }

        return csvEntities;
    }

    public List<SearchWord> createSearchWordListFromSearchParametr(SearchParameter parameter) {
        List<SearchWord> words = new ArrayList<>();
        for (String item : parameter.getSearchWord()) {
            SearchWord word = new SearchWord();
            word.setSearchWord(item);
            words.add(word);
        }
        return words;
    }
}
