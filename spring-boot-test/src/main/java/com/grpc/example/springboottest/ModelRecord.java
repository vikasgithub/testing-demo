package com.grpc.example.springboottest;

public class ModelRecord {

    private long id;
    private String name;

    public ModelRecord() {
    }

    public ModelRecord(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ModelRecord{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
