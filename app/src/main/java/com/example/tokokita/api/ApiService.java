package com.example.tokokita.api;

import com.example.tokokita.model.ApiResponse;
import com.example.tokokita.model.Barang;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface ApiService {

    @GET("api_barang.php?action=getAll")
    Call<ApiResponse> getAllBarang();

    @GET("api_barang.php?action=search")
    Call<ApiResponse> searchBarang(@Query("query") String query);

    @GET("api_barang.php?action=getById")
    Call<ApiResponse> getBarangById(@Query("kdbarang") String kdbarang);

    @POST("api_barang.php")
    Call<ApiResponse> addBarang(@Body Barang barang);

    @PUT("api_barang.php")
    Call<ApiResponse> updateBarang(@Body Barang barang);

    @DELETE("api_barang.php")
    Call<ApiResponse> deleteBarang(@Query("kdbarang") String kdbarang);
}