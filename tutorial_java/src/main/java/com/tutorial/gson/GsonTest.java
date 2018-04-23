package com.tutorial.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tutorial.domain.Book;

/**
 * Created by Jimmy. 2018/4/23  14:27
 */
public class GsonTest {

    public static void main(String[] args) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .disableHtmlEscaping()
                .create();
        Book book = new Book();
        book.setId(1L);
        book.setName("Java");
    //    System.out.println(gson.toJson(book));//{"id": 1,"name": "Java"}
        String jsonStr = "{\"id\": 1,\"name\": \"“Java”\"}";//中文的双引号可以解析,英文的不行
        Book book2 = gson.fromJson(jsonStr,Book.class);
        System.out.println(book2);
    }
}
