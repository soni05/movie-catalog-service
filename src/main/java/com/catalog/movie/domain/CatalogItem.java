package com.catalog.movie.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@AllArgsConstructor
public class CatalogItem {
    private String name;
    private String desc;
    private int rating;
}
