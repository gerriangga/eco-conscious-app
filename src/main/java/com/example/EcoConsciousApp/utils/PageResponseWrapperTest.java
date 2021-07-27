package com.example.EcoConsciousApp.utils;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@NoArgsConstructor
public class PageResponseWrapperTest<T> {
    private List<T> content;
    private Long count;
    private Integer totalPages;
    private Integer page;
    private Integer size;

    public PageResponseWrapperTest(Page<T> page) {
        this.content = page.getContent();
        this.count = page.getTotalElements();
        this.totalPages = page.getTotalPages();
        this.page = page.getNumber();
        this.size = page.getSize();
    }

}
