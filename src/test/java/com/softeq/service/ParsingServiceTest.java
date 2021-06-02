package com.softeq.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.softeq.repository.ParsingEntityRepository;
import com.softeq.repository.ParsingRequestRepository;
import com.softeq.repository.entity.ParsingEntity;
import com.softeq.repository.entity.ParsingRequest;
import com.softeq.repository.entity.SearchWord;
import com.softeq.service.entity.ParsingEntityDto;
import com.softeq.service.entity.SearchParameter;

@ExtendWith(MockitoExtension.class)
class ParsingServiceTest {

    @Mock
    private Searcher searcher;
    @Mock
    private ParsingEntityRepository parsingEntityRepository;
    @Mock(lenient = true)
    private ParsingRequestRepository parsingRequestRepository;
    @Mock(lenient = true)
    private Mapper mapper;
    @InjectMocks
    private ParsingService parsingService;

    private SearchParameter parameter = new SearchParameter();
    private List<SearchWord> words = Arrays.asList(new SearchWord());
    private ParsingRequest request = new ParsingRequest();
    private ParsingEntity entity = new ParsingEntity();
    private ParsingEntityDto dto = new ParsingEntityDto();
    private List<ParsingEntityDto> dtoList = Arrays.asList(dto);
    private List<ParsingEntity> results = Arrays.asList(entity);

    @Test
    void findResultsByCriteria() {
        Mockito.when(searcher.search(parameter)).thenReturn(dtoList);
        Mockito.when(mapper.createSearchWordListFromSearchParametr(parameter)).thenReturn(words);
        Mockito.when(mapper.changeParsingEntityToParsingEntityDto(entity)).thenReturn(dto);
        Mockito.when(parsingRequestRepository.createRequest(request)).thenReturn(request);
        Mockito.when(parsingEntityRepository.create(entity)).thenReturn(entity);
        Mockito.when(mapper.changeListOfParsingEntityDtoToParsingEntity(dtoList)).thenReturn(results);
        Mockito.when(mapper.changeListOfParsingEntityToParsingEntityDto(results)).thenReturn(dtoList);

        assertEquals(0, parsingService.findResultsByCriteria(parameter).size());
    }

    @Test
    void findFirstBiggestResults() {
        Mockito.when(searcher.search(parameter)).thenReturn(dtoList);
        Mockito.when(mapper.createSearchWordListFromSearchParametr(parameter)).thenReturn(words);
        Mockito.when(mapper.changeParsingEntityToParsingEntityDto(entity)).thenReturn(dto);
        Mockito.when(parsingRequestRepository.createRequest(request)).thenReturn(request);
        Mockito.when(parsingEntityRepository.create(entity)).thenReturn(entity);
        Mockito.when(mapper.changeListOfParsingEntityDtoToParsingEntity(dtoList)).thenReturn(results);
        Mockito.when(mapper.changeListOfParsingEntityToParsingEntityDto(results)).thenReturn(dtoList);
        int number = 1;
        assertEquals(0, parsingService.findFirstBiggestResults(parameter, number).size());

    }

    @Test
    void createCsvFile() {

    }

    @Test
    void createCsvFileWithBiggestResults() {
    }

    @Test
    void createRequest() {
//        List<SearchWord> words = Arrays.asList(new SearchWord());
//        request.setCreationTime(LocalDateTime.now());
//        request.setSearchWords(words);
//        Mockito.when(mapper.createSearchWordListFromSearchParametr(parameter)).thenReturn(words);
//        Mockito.when(parsingRequestRepository.createRequest(request)).thenReturn(request);
//        assertEquals(request.getSearchWords(), parsingService.createRequest(parameter).getSearchWords());

    }
}