package com.example.tokokita;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.tokokita.api.ApiClient;
import com.example.tokokita.model.ApiResponse;
import com.example.tokokita.model.Barang;

import java.text.NumberFormat;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {
    private static final String TAG = "DetailActivity";
    private ImageView imgBarang;
    private TextView tvNamaBarang, tvHarga, tvStok, tvDeskripsi;
    private String kdbarang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Setup action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Detail Barang");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        initViews();
        kdbarang = getIntent().getStringExtra("kdbarang");

        if (kdbarang != null) {
            Log.d(TAG, "Loading detail for kdbarang: " + kdbarang);
            loadDetailBarang();
        } else {
            Toast.makeText(this, "Invalid product ID", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void initViews() {
        imgBarang = findViewById(R.id.imgBarang);
        tvNamaBarang = findViewById(R.id.tvNamaBarang);
        tvHarga = findViewById(R.id.tvHarga);
        tvStok = findViewById(R.id.tvStok);
        tvDeskripsi = findViewById(R.id.tvDeskripsi);
    }

    private void loadDetailBarang() {
        ApiClient.getApiService().getBarangById(kdbarang).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                Log.d(TAG, "Detail response code: " + response.code());

                if (response.isSuccessful() && response.body() != null) {
                    ApiResponse apiResponse = response.body();
                    Log.d(TAG, "Detail API Response success: " + apiResponse.isSuccess());

                    if (apiResponse.isSuccess() && apiResponse.getData() != null && !apiResponse.getData().isEmpty()) {
                        Barang barang = apiResponse.getData().get(0);
                        displayBarangDetail(barang);
                        Log.d(TAG, "Detail loaded for: " + barang.getNmbarang());
                    } else {
                        Toast.makeText(DetailActivity.this, "Product not found", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                } else {
                    Log.e(TAG, "Detail response not successful: " + response.message());
                    Toast.makeText(DetailActivity.this, "Failed to load product details", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.e(TAG, "Detail API call failed", t);
                Toast.makeText(DetailActivity.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void displayBarangDetail(Barang barang) {
        tvNamaBarang.setText(barang.getNmbarang());

        // Format harga
        NumberFormat formatter = NumberFormat.getInstance(new Locale("id", "ID"));
        String formattedPrice = "Rp " + formatter.format(barang.getHarga());
        tvHarga.setText(formattedPrice);

        tvStok.setText("Stok: " + barang.getStok());
        tvDeskripsi.setText(barang.getDeskripsi());

        // Load image
        Glide.with(this)
                .load(barang.getFoto())
                .placeholder(R.drawable.ic_image_placeholder)
                .error(R.drawable.ic_image_placeholder)
                .into(imgBarang);
    }
}