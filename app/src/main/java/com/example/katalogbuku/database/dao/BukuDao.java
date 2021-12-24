package com.example.katalogbuku.database.dao;

import androidx.room.Delete;
import androidx.room.Query;
import androidx.room.Dao;
import com.example.katalogbuku.database.entitas.buku;

import java.util.List;
@Dao
public interface BukuDao {
    @Query("SELECT * FROM buku")
    List<buku> getAll();

    @Query("INSERT INTO buku (judul, kategori, harga, penulis, imlo, stok) VALUES(:judul,:kategori,:harga,:penulis,:imlo,:stok)")
    void insertbuku(String judul, String kategori, String harga, String penulis, String imlo, String stok);

    @Query("UPDATE buku SET judul=:judul , kategori=:kategori, harga=:harga , penulis=:penulis , imlo=:imlo , stok=:stok WHERE id_buku=:id_buku")
    void update(int id_buku, String judul, String kategori, String harga, String penulis, String imlo, String stok);

    @Query("SELECT * FROM buku WHERE id_buku=:id_buku")
    buku get(int id_buku);

    @Delete
    void delete(buku buku);

}
