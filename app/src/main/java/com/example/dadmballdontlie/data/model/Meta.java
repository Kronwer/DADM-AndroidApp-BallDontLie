package com.example.dadmballdontlie.data.model;

public class Meta {

    private int total_pages;

    private int current_page;

    private int next_page;

    private int per_page;

    private int total_count;

    public Meta(int total_pages, int current_page, int next_page, int per_page, int total_count) {
        this.total_pages = total_pages;
        this.current_page = current_page;
        this.next_page = next_page;
        this.per_page = per_page;
        this.total_count = total_count;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public int getNext_page() {
        return next_page;
    }

    public void setNext_page(int next_page) {
        this.next_page = next_page;
    }

    public int getPer_page() {
        return per_page;
    }

    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

}
