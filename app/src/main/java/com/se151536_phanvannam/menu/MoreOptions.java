package com.se151536_phanvannam.menu;

public class MoreOptions {
    private String title;
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MoreOptions(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
