package com.grpc.example.springboottest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;

@Service
public class BookService {

    DatabaseService databaseService;

    public BookService(@Autowired DatabaseService databaseService) {
        this.databaseService = databaseService;
        System.out.println(databaseService);
    }

    public int getBookId() {
        return databaseService.testMethod();
    }
}
