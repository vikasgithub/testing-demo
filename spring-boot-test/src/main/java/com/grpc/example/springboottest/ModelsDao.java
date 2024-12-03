package com.grpc.example.springboottest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Repository
public class ModelsDao {

    private static final Logger logger = LoggerFactory.getLogger(ModelsDao.class);

    JdbcClient jdbcClient;

    public ModelsDao(@Autowired JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<ModelRecord> getModels() {
        String sql = "select * from models";
        return jdbcClient.sql(sql).query(new ModelRecordMapper()).list();
    }

    public byte[] getModelBytes(ModelRecord modelRecord) {
        String sql = "select model_archive from models where id = ?";
        return jdbcClient
                .sql(sql)
                .param(1, modelRecord.getId())
                .query((rs, num) -> {
                    return rs.getBytes(1);
                }).optional().orElseGet(() -> new byte[0]);
    }


}
