package com.demoqa.models.books;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AllBooksFromStoreRsModel {
        private List<BookModel> books;
}
