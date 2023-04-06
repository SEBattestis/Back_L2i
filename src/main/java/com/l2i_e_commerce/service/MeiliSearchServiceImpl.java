package com.l2i_e_commerce.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.l2i_e_commerce.model.Book;
import com.meilisearch.sdk.Client;
import com.meilisearch.sdk.Config;
import com.meilisearch.sdk.Index;
import org.springframework.stereotype.Service;


import com.meilisearch.sdk.json.GsonJsonHandler;
import com.meilisearch.sdk.json.JsonHandler;
import com.meilisearch.sdk.model.*;

import java.util.*;

@Service
public class MeiliSearchServiceImpl implements MeiliSearchService {

    private final Client client;
    private final Index bookIndex;
    private final ObjectMapper objectMapper;

    public MeiliSearchServiceImpl() throws Exception {
    	
        Config config = new Config("http://127.0.0.1:7700");
        client = new Client(config);
        bookIndex = client.index("books");
        objectMapper = new ObjectMapper();
    }

    @Override
    public void addOrUpdateBook(Book book) throws Exception {
        String bookJson = objectMapper.writeValueAsString(book);
        bookIndex.addDocuments(bookJson);
    }

    @Override
    public void deleteBookById(String id) throws Exception {
        bookIndex.deleteDocument(id);
    }
    
    @Override
    public List<Book> searchBooks(String query, String filter) throws Exception {
        JsonHandler jsonHandler = new GsonJsonHandler();
        Map<String, String> searchParameters = new HashMap<>();
        searchParameters.put("q", query);
        searchParameters.put("filters", filter);
        String searchParametersJson = objectMapper.writeValueAsString(searchParameters);
        SearchResult searchResponse = bookIndex.search(searchParametersJson);
        Book[] books = jsonHandler.decode(searchResponse, Book[].class);
        return Arrays.asList(books);
    }
}