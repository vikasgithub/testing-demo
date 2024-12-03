package com.grpc.example.springboottest;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ModelRecordMapper implements RowMapper<ModelRecord> {

    @Override
    public ModelRecord mapRow(ResultSet rs, int rowNum) throws SQLException {
        ModelRecord modelRecord = new ModelRecord();
        modelRecord.setId(rs.getLong("id"));
        modelRecord.setName(rs.getString("name"));
        return modelRecord;
    }
}
