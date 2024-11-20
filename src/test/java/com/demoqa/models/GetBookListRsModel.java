package com.demoqa.models;

import lombok.Data;

import java.util.List;
@Data
    public class GetBookListRsModel {
        private String userId; // ID пользователя
        private String username; // Имя пользователя
        private List<BookModel> books; // Список книг пользователя
    }

