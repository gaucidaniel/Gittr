package com.danielgauci.gittr.data.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daniel on 3/7/17.
 */

public class Filter {

    private String name;
    private String key;
    private boolean enabled;

    public Filter(String name, String key) {
        this.name = name;
        this.key = key;
    }

    public static List<Filter> getAllFilters() {
        List<Filter> filters = new ArrayList<>();

        filters.add(new Filter("Create", Event.EventType.CREATE));
        filters.add(new Filter("Delete", Event.EventType.DELETE));
        filters.add(new Filter("Fork", Event.EventType.FORK));
        filters.add(new Filter("Issues", Event.EventType.ISSUES));
        filters.add(new Filter("Issue Comment", Event.EventType.ISSUE_COMMENT));
        filters.add(new Filter("Member", Event.EventType.MEMBER));
        filters.add(new Filter("Public", Event.EventType.PUBLIC));
        filters.add(new Filter("Pull Request", Event.EventType.PULL_REQUEST));
        filters.add(new Filter("Review", Event.EventType.PULL_REQUEST_REVIEW));
        filters.add(new Filter("Review Comment", Event.EventType.PULL_REQUREST_REVIEW_COMMENT));
        filters.add(new Filter("Push", Event.EventType.PUSH));
        filters.add(new Filter("Release", Event.EventType.RELEASE));
        filters.add(new Filter("Repository", Event.EventType.REPOSITORY));
        filters.add(new Filter("Watch", Event.EventType.WATCH));

        return filters;
    }

    public String getName() {
        return name;
    }

    public String getKey() {
        return key;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public boolean toggle(){
        enabled = !enabled;
        return enabled;
    }
}
