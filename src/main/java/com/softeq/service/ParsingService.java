package com.softeq.service;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.softeq.repository.ParsingEntityRepository;
import com.softeq.repository.ParsingRequestRepository;
import com.softeq.repository.SeachWordRepository;
import com.softeq.repository.entity.ParsingEntity;
import com.softeq.repository.entity.ParsingRequest;
import com.softeq.repository.entity.SearchWord;
import com.softeq.service.entity.CsvEntity;
import com.softeq.service.entity.ParsingEntityDto;
import com.softeq.service.entity.SearchParameter;
import com.softeq.service.entity.SearchWordCountEntity;

/**
 * Main Service class. Use for connection work of Controller and Repositories. Use business logic
 */
@Service
public class ParsingService {

    private static final String CSV_SEPARATOR = ", ";

    private final Searcher searcher;
    private final ParsingEntityRepository parsingEntityRepository;
    private final ParsingRequestRepository parsingRequestRepository;
    private final Mapper mapper;
    private final SeachWordRepository seachWordRepository;

    public ParsingService(Searcher searcher, ParsingEntityRepository parsingEntityRepository,
            ParsingRequestRepository parsingRequestRepository, Mapper mapper, SeachWordRepository seachWordRepository) {
        this.searcher = searcher;
        this.parsingEntityRepository = parsingEntityRepository;
        this.parsingRequestRepository = parsingRequestRepository;
        this.mapper = mapper;
        this.seachWordRepository = seachWordRepository;
    }

    /**
     * method is used for taking SearchParameter, making parsing and showing the
     * results to the user's screen
     * @param parameter
     * @return List<CsvEntity>
     */
    public List<CsvEntity> findResultsByCriteria(SearchParameter parameter) {
        List<ParsingEntityDto> resultsFromInternet = searcher.search(parameter);
        ParsingRequest request = createRequest(parameter);
        List<ParsingEntityDto> resultList = addRequestToParsingEntities(resultsFromInternet, request);
        resultList = addEntitiesToDB(resultList);
        return mapper.changeListParsingEntitiesDtoToCsv(resultList);
    }

    /**
     * method is used for taking SearchParameter and the number of the biggest values, which user wants
     * to watch,making parsing, sorting the result by decs and showing the results to the user's screen
     * @param parameter
     * @param number
     * @return List<CsvEntity>
     */
    public List<CsvEntity> findFirstBiggestResults(SearchParameter parameter, int number) {
        List<CsvEntity> list = findResultsByCriteria(parameter);
        List<CsvEntity> sortedList = list.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        if (number > sortedList.size()) {
            return sortedList.subList(0, sortedList.size());
        }
        return sortedList.subList(0, number);
    }

    /**
     * method is used for taking SearchParameter, making parsing and using results
     * for creating csv-file in nessesary format. The separator between points is ,
     * @param parameter
     */
    public void createCsvFile(SearchParameter parameter) {
        List<CsvEntity> list = findResultsByCriteria(parameter);
        writeToCSV(parameter, list);

    }
    /**
     * method is used for taking SearchParameter and the number of the biggest values, which user wants
     * to watch, making parsing , sorting results by decs for creating csv-file
     * in nessesary format. The separator between points is ,
     * @param parameter
     */
    public void createCsvFileWithBiggestResults(SearchParameter parameter, int number) {
        List<CsvEntity> list = findFirstBiggestResults(parameter, number);
        writeToCSV(parameter, list);

    }

    public ParsingRequest createRequest(SearchParameter parameter) {
        ParsingRequest request = new ParsingRequest();
        LocalDateTime time = LocalDateTime.now();
        request.setCreationTime(time);
        request.setSearchUrl(parameter.getUrl());
        List<SearchWord> words = mapper.createSearchWordListFromSearchParametr(parameter);
        request.setSearchWords(words);
        return parsingRequestRepository.createRequest(request);
    }

    private List<ParsingEntityDto> addRequestToParsingEntities(List<ParsingEntityDto> dtos, ParsingRequest request) {
        for (ParsingEntityDto dto : dtos) {
            dto.setRequest(request);
        }
        return dtos;
    }

    private List<ParsingEntityDto> addEntitiesToDB(List<ParsingEntityDto> list) {
        List<ParsingEntity> results = mapper.changeListOfParsingEntityDtoToParsingEntity(list);
        List<ParsingEntity> resultsWithId = new ArrayList<>();
        for (ParsingEntity item : results) {
            resultsWithId.add(parsingEntityRepository.create(item));
        }
        return mapper.changeListOfParsingEntityToParsingEntityDto(resultsWithId);
    }

    private void writeToCSV(SearchParameter parameter, List<CsvEntity> list) {
        try {
            BufferedWriter bw = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream("parsing_entities.csv"), "UTF-8"));
            StringBuffer firstLine = new StringBuffer();
            firstLine.append(parameter.getUrl());
            firstLine.append(CSV_SEPARATOR);
            for (String word : parameter.getSearchWord()) {
                firstLine.append(word);
                firstLine.append(CSV_SEPARATOR);
            }
            firstLine.append("Count");
            firstLine.append(CSV_SEPARATOR);
            bw.write(firstLine.toString());
            bw.newLine();

            for (CsvEntity product : list) {
                StringBuffer oneLine = new StringBuffer();
                oneLine.append(product.getUrl());
                oneLine.append(CSV_SEPARATOR);
                for (SearchWordCountEntity entity : product.getSearches()) {
                    oneLine.append(entity.getCount());
                    oneLine.append(CSV_SEPARATOR);
                }
                oneLine.append(product.getFullCount());
                bw.write(oneLine.toString());
                bw.newLine();
            }
            bw.flush();
            bw.close();

        } catch (UnsupportedEncodingException e) {
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
