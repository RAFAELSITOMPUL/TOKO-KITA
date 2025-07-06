# VortexGadget - Android Shopping App

Aplikasi belanja Android dengan antarmuka yang bersih dan modern untuk mencari dan menjelajahi produk gadget menggunakan Java.

## Fitur Utama

- **Header Aplikasi**: Menampilkan nama aplikasi "VortexGadget" dengan tagline "Belanja DI VortexGadget !!"
- **Pencarian Real-time**: Fitur pencarian dengan TextWatcher untuk pencarian otomatis
- **Daftar Produk**: RecyclerView untuk menampilkan daftar produk secara dinamis
- **Integrasi API**: Menggunakan Retrofit untuk komunikasi dengan backend
- **Error Handling**: Penanganan error yang komprehensif dengan Toast dan Log

## Struktur Layout

Layout utama menggunakan `LinearLayout` vertikal dengan komponen berikut:

### Header Section
- Background dengan warna primer
- Judul aplikasi dengan styling bold
- Tagline aplikasi
- Search bar dengan ikon pencarian

### Content Section
- RecyclerView untuk menampilkan daftar produk
- Padding 8dp untuk spacing yang konsisten

## Resources yang Dibutuhkan

### Colors
```xml
<!-- colors.xml -->
<resources>
    <color name="background_color">#FFFFFF</color>
    <color name="primary_color">#2196F3</color>
    <color name="white">#FFFFFF</color>
    <color name="hint_color">#9E9E9E</color>
    <color name="text_color">#212121</color>
</resources>


Drawables

@drawable/search_background - Background untuk search bar
@drawable/ic_search - Ikon pencarian (24dp x 24dp)


Implementasi
MainActivity.java

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
            getSupportActionBar().setTitle("VortexGadget");
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
        // Implementation from your provided code
    }

    private void searchBarang(String query) {
        // Implementation from your provided code
    }
}

Model Classes
Barang.java

public class Barang {
    private int id;
    private String nama;
    private String deskripsi;
    private double harga;
    private String imageUrl;
    
    // Constructors
    public Barang() {}
    
    public Barang(int id, String nama, String deskripsi, double harga, String imageUrl) {
        this.id = id;
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.harga = harga;
        this.imageUrl = imageUrl;
    }
    
    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }
    
    public String getDeskripsi() { return deskripsi; }
    public void setDeskripsi(String deskripsi) { this.deskripsi = deskripsi; }
    
    public double getHarga() { return harga; }
    public void setHarga(double harga) { this.harga = harga; }
    
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}


ApiResponse.java

public class ApiResponse {
    private boolean success;
    private String message;
    private List<Barang> data;
    
    // Constructors
    public ApiResponse() {}
    
    // Getters and Setters
    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }
    
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    
    public List<Barang> getData() { return data; }
    public void setData(List<Barang> data) { this.data = data; }
}


Dependencies
Tambahkan dependencies berikut ke build.gradle (Module: app):

dependencies {
    implementation 'androidx.appcompat:appcompat:1.6.1'
    implementation 'com.google.android.material:material:1.9.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.4'
    implementation 'androidx.recyclerview:recyclerview:1.3.1'
    
    // Retrofit for API calls
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
    implementation 'com.squareup.okhttp3:logging-interceptor:4.9.1'
    
    // Glide for image loading
    implementation 'com.github.bumptech.glide:glide:4.15.1'
    annotationProcessor 'com.github.bumptech.glide:compiler:4.15.1'
}


Fitur Teknis

-Real-time Search: Pencarian otomatis menggunakan TextWatcher
-Error Handling: Comprehensive error handling dengan Toast messages
-Logging: Detailed logging untuk debugging
-API Integration: Menggunakan Retrofit untuk REST API calls
-Data Binding: Efficient data binding dengan RecyclerView


Penggunaan
1.Clone repository ini
2.Buka project di Android Studio
3.Pastikan semua resource (colors, drawables) telah ditambahkan
4.Implementasikan BarangAdapter untuk RecyclerView
5.Setup ApiClient untuk konfigurasi Retrofit
6.Konfigurasi base URL API di ApiClient
7.Build dan jalankan aplikasi


Persyaratan

-Android SDK minimum: API 21 (Android 5.0)
-Android Studio 4.0+
-Java 8+
-Internet connection untuk API calls


com.example.tokokita/
├── adapter/
│   └── BarangAdapter.java
├── api/
│   ├── ApiClient.java
│   └── ApiService.java
├── model/
│   ├── Barang.java
│   └── ApiResponse.java
└── MainActivity.java


Kontribusi
Silakan buat pull request untuk perbaikan atau penambahan fitur baru.

Lisensi
[Tambahkan informasi lisensi di sini]

README ini telah disesuaikan dengan kode Java yang Anda berikan, termasuk struktur kelas, implementasi API, dan fitur-fitur yang ada dalam aplikasi VortexGadget.
