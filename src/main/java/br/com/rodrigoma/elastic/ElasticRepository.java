package br.com.rodrigoma.elastic;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
public class ElasticRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(ElasticRepository.class);

    // TODO 03 Indexing and Querying using Jest

    @Autowired
    private JestClient jestClient;

    public void saveElastic(String json) {
        try {
            Index index = new Index.Builder(json)
                    .index("techtalk")
                    .type("slide")
                    .id(UUID.randomUUID().toString())
                    .build();

            LOGGER.info("JEST INDEX: {}", index.toString());

            jestClient.execute(index);
        } catch (IOException e) {
            LOGGER.error("JEST INDEX ERROR: {}", e.getMessage());
        }
    }

    public String getElastic(String title) {
        // @formatter:off
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder()
                .query(QueryBuilders.matchQuery("title", title));

        Search search = new Search.Builder(searchSourceBuilder.toString())
                .addIndex("techtalk")
                .addType("slide")
                .build();
        //@formatter:on

        LOGGER.info("JEST QUERY: {}", searchSourceBuilder.toString());

        String result = "";

        try {
            JestResult jestResult = jestClient.execute(search);
            result = jestResult.getSourceAsString();
        } catch (IOException e) {
            LOGGER.error("JEST SEARCH ERROR: {}", e.getMessage());
        }

        return result;
    }
}