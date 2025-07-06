package com.example.tokokita;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tokokita.adapter.BarangAdapter;
import com.example.tokokita.api.ApiClient;
import com.example.tokokita.model.ApiResponse;
import com.example.tokokita.model.Barang;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private RecyclerView recyclerView;
    private BarangAdapter adapter;
    private List<Barang> barangList = new ArrayList<>();
    private EditText etSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Toko Kita");
        }

        initViews();
        setupRecyclerView();
        setupSearch();
        loadData();
    }

    private void initViews() {
        recyclerView = findViewById(R.id.recyclerView);
        etSearch = findViewById(R.id.etSearch);
    }

    private void setupRecyclerView() {
        adapter = new BarangAdapter(this, barangList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void setupSearch() {
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    searchBarang(s.toString());
                } else {
                    loadData();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void loadData() {
        Log.d(TAG, "Loading data...");

        ApiClient.getApiService().getAllBarang().enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                Log.d(TAG, "Response code: " + response.code());

                if (response.isSuccessful() && response.body() != null) {
                    ApiResponse apiResponse = response.body();
                    Log.d(TAG, "API Response success: " + apiResponse.isSuccess());

                    if (apiResponse.isSuccess() && apiResponse.getData() != null) {
                        barangList.clear();
                        barangList.addAll(apiResponse.getData());
                        adapter.notifyDataSetChanged();
                        Log.d(TAG, "Data loaded: " + barangList.size() + " items");
                    } else {
                        Toast.makeText(MainActivity.this, "No data available", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.e(TAG, "Response not successful: " + response.message());
                    Toast.makeText(MainActivity.this, "Failed to load data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.e(TAG, "API call failed", t);
                Toast.makeText(MainActivity.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void searchBarang(String query) {
        Log.d(TAG, "Searching for: " + query);

        ApiClient.getApiService().searchBarang(query).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ApiResponse apiResponse = response.body();
                    if (apiResponse.isSuccess() && apiResponse.getData() != null) {
                        adapter.updateData(apiResponse.getData());
                        Log.d(TAG, "Search results: " + apiResponse.getData().size() + " items");
                    } else {
                        adapter.updateData(new ArrayList<>());
                    }
                } else {
                    Log.e(TAG, "Search failed: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.e(TAG, "Search API call failed", t);
                Toast.makeText(MainActivity.this, "Search failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}