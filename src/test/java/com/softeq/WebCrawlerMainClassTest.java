package com.softeq;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.softeq.controller.Controller;
import com.softeq.service.ParsingService;
import com.softeq.service.entity.CsvEntity;
import com.softeq.service.entity.SearchParameter;
import com.softeq.service.entity.SearchWordCountEntity;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class WebCrawlerMainClassTest {

    @Autowired
    private Controller controller;

    private MockMvc mockMvc;

    @MockBean
    private ParsingService parsingService;

    @Autowired
    private WebApplicationContext wac;

    @BeforeAll
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();

    }

    @Test
    void main() {
        assertNotNull(controller);
    }

    @Test
    void mainController() throws Exception {
        SearchParameter parameter = new SearchParameter(
                "https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/jdbc/support/JdbcAccessor.html",
                Arrays.asList("JdbcAccessor"));
        CsvEntity entity = new CsvEntity();
        entity.setUrl(parameter.getUrl());
        entity.getSearches().add(new SearchWordCountEntity("JdbcAccessor", 6));
        List<CsvEntity> csvEntities = Arrays.asList(entity);
        given(parsingService.findResultsByCriteria(parameter)).willReturn(csvEntities);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/v1").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "[{\"url\":\"https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/jdbc/support/JdbcAccessor.html\",\"searches\":[{\"searchWord\":\"JdbcAccessor\",\"count\":6}],\"fullCount\":6}]"));

    }

}

