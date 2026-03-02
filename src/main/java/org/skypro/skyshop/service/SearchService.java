package org.skypro.skyshop.service;

import org.skypro.skyshop.model.search.SearchResult;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SearchService {
    private final StorageService storageService;

    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }

    public Collection<SearchResult> search(String searchString) {
        return storageService.getSearchEngine().stream()
                .filter(Objects::nonNull)
                .filter(i -> i.getSearchTerm().contains(searchString))
                .map(SearchResult::fromSearchable)
                .collect(Collectors.toList());
    }
}
