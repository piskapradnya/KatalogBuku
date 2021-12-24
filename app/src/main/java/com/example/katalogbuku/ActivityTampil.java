package com.example.katalogbuku;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityTampil extends AppCompatActivity {
    TextView tampilJudul, tampilKategori, tampilHarga, tampilPenulis, tampilImportLokal, tampilStok;
    String judul, kategori, harga, penulis, imlo, stok;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil);

        tampilJudul = (TextView) findViewById(R.id.tampilJudul);
        tampilKategori = (TextView) findViewById(R.id.tampilKategori);
        tampilHarga = (TextView) findViewById(R.id.tampilHarga);
        tampilPenulis = (TextView) findViewById(R.id.tampilPenulis);
        tampilImportLokal = (TextView) findViewById(R.id.tampilImportLokal);
        tampilStok = (TextView) findViewById(R.id.tampilStok);

        if (getIntent().getStringExtra("judul") != "") {
            judul = getIntent().getStringExtra("judul");
            tampilJudul.setText(judul);
        }
        if (getIntent().getStringExtra("kategori") != "") {
            kategori = getIntent().getStringExtra("kategori");
            tampilKategori.setText(kategori);
        }
        if (getIntent().getStringExtra("harga") != "") {
            harga = getIntent().getStringExtra("harga");
            tampilHarga.setText(harga);
        }
        if (getIntent().getStringExtra("penulis") != "") {
            penulis = getIntent().getStringExtra("penulis");
            tampilPenulis.setText(penulis);
        }
        if (getIntent().getStringExtra("import/lokal") != "") {
            imlo = getIntent().getStringExtra("import/lokal");
            tampilImportLokal.setText(imlo);
        }
        if (getIntent().getStringExtra("stok") != "") {
            stok = getIntent().getStringExtra("stok");
            tampilStok.setText(stok);
        }
    }
    public void  submit (View view) {
        Intent intent = new Intent(com.example.katalogbuku.ActivityTampil.this, MainActivity.class);
        startActivity(intent);
    }
}