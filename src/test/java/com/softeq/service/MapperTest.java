package com.softeq.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.softeq.repository.entity.ParsingEntity;
import com.softeq.repository.entity.ParsingRequest;
import com.softeq.repository.entity.SearchWord;
import com.softeq.service.entity.CsvEntity;
import com.softeq.service.entity.ParsingEntityDto;
import com.softeq.service.entity.SearchParameter;

@ExtendWith(MockitoExtension.class)
class MapperTest {

    @InjectMocks
    private Mapper mapper;

    private ParsingEntity parsingEntity = new ParsingEntity();
    private ParsingEntityDto dto = new ParsingEntityDto();
    List<ParsingEntityDto> dtos = Arrays.asList(dto);
    List<ParsingEntity> entities = Arrays.asList(parsingEntity);
    List<CsvEntity> csvEntities = Arrays.asList(new CsvEntity());


    @Test
    void changeParsingEntityDtoToParsingEntityTest() {
        assertEquals(parsingEntity, mapper.changeParsingEntityDtoToParsingEntity(dto));
    }

    @Test
    void changeParsingEntityToParsingEntityDtoTest() {
        assertEquals(dto, mapper.changeParsingEntityToParsingEntityDto(parsingEntity));
    }

    @Test
    void changeParsingEntityToParsingEntityDtoTestNegative() {
        parsingEntity.setUrl("skjdngf");
        ParsingEntityDto dto = new ParsingEntityDto();
        assertNotEquals(dto, mapper.changeParsingEntityToParsingEntityDto(parsingEntity));
    }

    @Test
    void changeListOfParsingEntityToParsingEntityDtoTest() {
        assertEquals(dtos, mapper.changeListOfParsingEntityToParsingEntityDto(entities));
    }

    @Test
    void changeListOfParsingEntityToParsingEntityDtoTestNegative() {
        dtos.get(0).setUrl("kjsdngs");
        assertNotEquals(dtos, mapper.changeListOfParsingEntityToParsingEntityDto(entities));
    }

    @Test
    void changeListOfParsingEntityDtoToParsingEntity() {
        assertEquals(entities, mapper.changeListOfParsingEntityDtoToParsingEntity(dtos));
    }

//    @Test
//    void changeListParsingEntitiesDtoToCsvTest() {
//        assertEquals(csvEntities, mapper.changeListParsingEntitiesDtoToCsv(dtos));
//    }

    @Test
    void createSearchWordListFromSearchParametrTest() {
        SearchWord word = new SearchWord(0, "mo", null );
        List<SearchWord> searchWords = Arrays.asList(word);
        SearchParameter parameter = new SearchParameter("", Arrays.asList("mo"));
        System.out.println("parameter " + parameter);
        assertEquals(searchWords, mapper.createSearchWordListFromSearchParametr(parameter));
    }
}