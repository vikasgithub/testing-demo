package com.grpc.example.springboottest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.mockito.Mockito.when;

@SpringBootTest
public class TestMockTest {

    @MockitoBean
    DatabaseService databaseService;

    @Autowired
    BookService bookService;

    @Test
    public void test111() {
        when(databaseService.testMethod()).thenReturn(100);
        System.out.println(bookService.getBookId());
    }
}
