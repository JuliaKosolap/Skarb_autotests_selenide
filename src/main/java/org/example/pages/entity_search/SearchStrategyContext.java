package org.example.pages.entity_search;

public class SearchStrategyContext {
    private SearchStrategy strategy;

    public SearchStrategyContext(SearchStrategy strategy){
        this.strategy = strategy;
    }

    public void executeEntitySearch(String searchTxt){
        strategy.searchEntity(searchTxt);
    }
}
