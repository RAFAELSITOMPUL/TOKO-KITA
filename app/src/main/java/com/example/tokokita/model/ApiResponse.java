package com.example.tokokita.model;

import java.util.List;

public class ApiResponse {
    private boolean success;
    private String message;
    private List<Barang> data;

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public List<Barang> getData() { return data; }
    public void setData(List<Barang> data) { this.data = data; }
}