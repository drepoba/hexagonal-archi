package drepoba.domain;

import java.util.List;

public class PageResult<T> {
    private List<T> content;
    private int totalElements;
    private int totalPages;
    private int page;
    private int size;

    public PageResult(List<T> content, int totalElements, int totalPages, int page, int size) {
        this.content = content;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.page = page;
        this.size = size;
    }

    public List<T> getContent() {
        return content;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }
}
