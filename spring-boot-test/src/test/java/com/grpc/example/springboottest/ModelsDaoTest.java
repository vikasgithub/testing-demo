package com.grpc.example.springboottest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

@Sql(value = "classpath:test1/models.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "classpath:test1/drop_models.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@SpringBootTest(classes = SpringBootTestApplication.class)
public class ModelsDaoTest {

    @Autowired
    private ModelsDao modelsDao;

    @Test
    public void testGetModelList() {
        List<ModelRecord> modelList = modelsDao.getModels();
        modelList.forEach(System.out::println);
    }

    @Test
    public void testGetModelBytes() {
        byte[] modelBytes = modelsDao.getModelBytes(new ModelRecord(1, "1"));
        System.out.println(modelBytes.length);
    }
}
