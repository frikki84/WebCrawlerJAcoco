package com.softeq.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.softeq.service.ParsingService;
import com.softeq.service.entity.CsvEntity;
import com.softeq.service.entity.SearchParameter;

@RestController
@RequestMapping("/v1")
public class Controller {

    public static final String DEFAULTE_VALUE_OF_BIGGEST_RESULTS = "10";

    private final ParsingService parsingService;

    public Controller(ParsingService parsingService) {
        this.parsingService = parsingService;
    }

    /**
     * method is used for taking SearchParameter, making parsing and showing the
     * results to the user's screen
     * @param parameter
     * @return
     */
    @GetMapping
    public List<CsvEntity> searchResults(@RequestBody SearchParameter parameter) {
        return parsingService.findResultsByCriteria(parameter);
    }

    /**
     * method is used for taking SearchParameter and the number of the biggest values, which user wants
     * to watch,making parsing, sorting the result by decs and showing the results to the user's screen
     * @param number - number of max results
     * @param parameter
     * @return
     */
    @GetMapping("/max")
    public List<CsvEntity> findBiggestResults(
            @RequestParam(value = "size", required = false, defaultValue = DEFAULTE_VALUE_OF_BIGGEST_RESULTS) int number,
            @RequestBody SearchParameter parameter) {
        return parsingService.findFirstBiggestResults(parameter, number);
    }

    /**
     * method is used for taking SearchParameter, making parsing and using results
     * for creating csv-file in nessesary format.
     * @param parameter
     */
    @GetMapping("/csv")
    public void createCsvFile(@RequestBody SearchParameter parameter) {
        parsingService.createCsvFile(parameter);
    }

    /**
     * method is used for taking SearchParameter and the number of the biggest values, which user wants
     * to watch, making parsing , sorting results by decs for creating csv-file
     * in nessesary format. The separator between points is ,
     * @param number -  number of max results
     * @param parameter
     */
    @GetMapping("/csv-max")
    public void createCsvFileWithBiggestResults(
            @RequestParam(value = "size", required = false, defaultValue = DEFAULTE_VALUE_OF_BIGGEST_RESULTS) int number,
            @RequestBody SearchParameter parameter) {
        parsingService.createCsvFileWithBiggestResults(parameter, number);
    }

}
