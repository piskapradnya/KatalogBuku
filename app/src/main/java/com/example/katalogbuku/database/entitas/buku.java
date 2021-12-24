package com.example.katalogbuku.database.entitas;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.logging.Handler;

@Entity
public class buku {
    @PrimaryKey(autoGenerate = true)
    public int id_buku;

    public String judul;
    public String kategori;
    public String harga;
    public String penulis;
    public String imlo;
    public String stok;

    public int getId_buku() {
        return id_buku;
    }

    public void setId_buku(int id_buku) {
        this.id_buku = id_buku;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public String getImlo() {
        return imlo;
    }

    public void setImlo(String imlo) { this.imlo = imlo; }

    public String getStok() {
        return stok;
    }

    public void setStok(String stok) {
        this.stok = stok;
    }
}

