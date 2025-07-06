package com.example.tokokita.model;

public class Barang {
    private String kdbarang;
    private String nmbarang;
    private int harga;
    private int stok;
    private String deskripsi;
    private String foto;

    public Barang() {}

    public Barang(String kdbarang, String nmbarang, int harga, int stok, String deskripsi, String foto) {
        this.kdbarang = kdbarang;
        this.nmbarang = nmbarang;
        this.harga = harga;
        this.stok = stok;
        this.deskripsi = deskripsi;
        this.foto = foto;
    }

    // Getters and Setters
    public String getKdbarang() { return kdbarang; }
    public void setKdbarang(String kdbarang) { this.kdbarang = kdbarang; }

    public String getNmbarang() { return nmbarang; }
    public void setNmbarang(String nmbarang) { this.nmbarang = nmbarang; }

    public int getHarga() { return harga; }
    public void setHarga(int harga) { this.harga = harga; }

    public int getStok() { return stok; }
    public void setStok(int stok) { this.stok = stok; }

    public String getDeskripsi() { return deskripsi; }
    public void setDeskripsi(String deskripsi) { this.deskripsi = deskripsi; }

    public String getFoto() { return foto; }
    public void setFoto(String foto) { this.foto = foto; }
}