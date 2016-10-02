package br.com.rodrigoma.rest;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.module.jsv.JsonSchemaValidator;
import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import org.apache.http.HttpStatus;
import org.hamcrest.core.StringContains;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiControlers.class)
public class ApiControlersTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        RestAssuredMockMvc.mockMvc(MockMvcBuilders.webAppContextSetup(webApplicationContext).build());
    }

    @Test
    public void get() throws Exception {
        //@formatter:off
        RestAssuredMockMvc
                .given().log().all()
                    .param("date", "2016-10-01")
                    .contentType(ContentType.JSON)
                .when()
                    .get("/techtalk")
                .then().log().all()
                    .assertThat()
                    .statusCode(HttpStatus.SC_OK)
                    .content(StringContains.containsString("{\"title\":\"Teste\",\"date\":\"2016-10-01\"}"));
        //@formatter:on
    }

    @Test
    public void post() throws Exception {
        //@formatter:off
        RestAssuredMockMvc
                .given().log().all()
                    .param("title", "Spring")
                    .param("date", "2016-10-01")
                    .contentType(ContentType.JSON)
                .when()
                    .post("/techtalk")
                .then().log().all()
                    .assertThat()
                    .statusCode(HttpStatus.SC_OK)
                    .content(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/techtalk-schema.json"));
        //@formatter:on
    }
}