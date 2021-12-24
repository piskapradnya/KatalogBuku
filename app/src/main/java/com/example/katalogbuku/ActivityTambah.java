package com.example.katalogbuku;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.katalogbuku.database.AppDatabase;
import com.example.katalogbuku.database.AppDatabase;
import com.example.katalogbuku.database.entitas.buku;

public class ActivityTambah extends AppCompatActivity implements View.OnClickListener {

    EditText editJudul, editPenulis, editHarga;
    SeekBar seekbarStok;
    TextView stokSeekbar;
    RadioButton radioImport, radioLokal, rb;
    RadioGroup rgImLo;
    Button btnTampil;
    CheckBox cb1, cb2, cb3, cb4, cb5, cb6;
    String kategori = "";
    private AppDatabase database;
    private boolean isEdit = false;
    private int id_buku = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);
        database = AppDatabase.getInstance(getApplicationContext());


        editJudul = (EditText) findViewById(R.id.editNama);
        editPenulis = (EditText) findViewById(R.id.editPenulis);
        editHarga = (EditText) findViewById(R.id.editHarga);

        radioLokal = (RadioButton) findViewById(R.id.radio_lokal);
        radioImport = (RadioButton) findViewById(R.id.radio_impor);
        rgImLo = (RadioGroup) findViewById(R.id.rGroup_ImportLokal);

        cb1 = (CheckBox) findViewById(R.id.kategori1);
        cb2 = (CheckBox) findViewById(R.id.kategori2);
        cb3 = (CheckBox) findViewById(R.id.kategori3);
        cb4 = (CheckBox) findViewById(R.id.kategori4);
        cb5 = (CheckBox) findViewById(R.id.kategori5);
        cb6 = (CheckBox) findViewById(R.id.kategori6);


        btnTampil = (Button) findViewById(R.id.btnTampilkan);
        btnTampil.setOnClickListener(this);

        seekbarStok = (SeekBar) findViewById(R.id.seekbarStok);
        stokSeekbar = (TextView) findViewById(R.id.stokSeekbar);

        Intent intent = getIntent();
        id_buku = intent.getIntExtra("id_buku", 0);
        if (id_buku>0) {
            isEdit = true;
            buku buku = database.bukuDao().get(id_buku);
            editJudul.setText(buku.judul);
            editPenulis.setText(buku.penulis);
            editHarga.setText(buku.harga);
            stokSeekbar.setText(buku.stok);

            if (buku.imlo.toString().equals("Import")) {
                radioImport.setChecked(true);
            } else if (buku.imlo.toString().equals("Lokal")) {
                radioLokal.setChecked(true);
            }

        }else{
            isEdit = false;
        }

        seekbarStok.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                stokSeekbar.setText(String.valueOf(progress));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    @Override
    public void onClick(View v) {

        int radio = rgImLo.getCheckedRadioButtonId();
        rb = findViewById(radio);

        String Judul = editJudul.getText().toString();
        String Penulis = editPenulis.getText().toString();
        String Harga = editHarga.getText().toString();
        String stok = stokSeekbar.getText().toString();
        String imlo = rb.getText().toString();


        //Check Box
        if (cb1.isChecked()) {
            kategori += "Novel";
        }
        if (cb2.isChecked()) {
            kategori += "Biografi";
        }
        if (cb3.isChecked()) {
            kategori += "Romance";
        }
        if (cb4.isChecked()) {
            kategori += "Ilmiah";
        }
        if (cb5.isChecked()) {
            kategori += "Komik";
        }
        if (cb6.isChecked()) {
            kategori += "Dan Lain Lain";
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        Toast.makeText(this, "Mohon Cek Kembali", Toast.LENGTH_SHORT).show();
        builder.setTitle("Pengecekan Ulang");
        builder.setMessage(
                        "Judul Buku : " + String.valueOf(Judul) + "\n" +
                        "Harga : " + "Rp." + String.valueOf(Harga) + "\n" +
                        "Kategori : " + String.valueOf(kategori) + "\n" +
                        "Penulis : " + String.valueOf(Penulis) + "\n" +
                        "Import/Lokal : " + String.valueOf(imlo) + "\n" +
                        "Stok : " + String.valueOf(stok) + "." + "\n" + "\n" + "Apakah anda yakin ingin menyimpan produk ini ?")
                .setPositiveButton("Simpan", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplicationContext(), com.example.katalogbuku.ActivityTampil.class);

                        if (isEdit){
                            database.bukuDao().update(id_buku, Judul, kategori, Harga, Penulis , imlo, stok);
                            intent.putExtra("judul", Judul);
                            intent.putExtra("kategori", kategori.toString());
                            intent.putExtra("harga", Harga);
                            intent.putExtra("penulis", Penulis);
                            intent.putExtra("imlo", imlo);
                            intent.putExtra("stok", stok);
                        }else{
                            database.bukuDao().insertbuku(Judul, kategori, Harga, Penulis, imlo, stok);
                            intent.putExtra("judul", Judul);
                            intent.putExtra("kategori", kategori.toString());
                            intent.putExtra("harga", Harga);
                            intent.putExtra("penulis", Penulis);
                            intent.putExtra("imlo", imlo);
                            intent.putExtra("stok", stok);
                        }

                        startActivity(intent);
                        finish();
                    }

                });
        builder.setNegativeButton(
                "Batalkan", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(com.example.katalogbuku.ActivityTambah.this, "Mohon input dengan benar", Toast.LENGTH_SHORT).show();
                    }
                });
        AlertDialog dialoghasil = builder.create();
        dialoghasil.show();
    }

    public void openMainActivity() {
        Intent intent = new Intent(this, com.example.katalogbuku.MainActivity.class);
        startActivity(intent);
    };




    //Untuk menampilkan hasil input data diatas di Activity Tampil
    public void openActivityTampil() {
        Intent intent = new Intent(this, com.example.katalogbuku.ActivityTampil.class);

        String Judul = editJudul.getText().toString();
        String Penulis = editPenulis.getText().toString();
        String Harga = editHarga.getText().toString();
        String ImLo = "";
        String Kategori = "";
        String stok = stokSeekbar.getText().toString();


        //Radio Button Size
        if (radioLokal.isChecked()) {
            ImLo += "Lokal";
        }
        if (radioImport.isChecked()) {
            ImLo += "Import";
        }


        //Check Box
        if (cb1.isChecked()) {
            kategori += "Novel";
        }
        if (cb2.isChecked()) {
            kategori += "Biografi";
        }
        if (cb3.isChecked()) {
            kategori += "Romance";
        }
        if (cb4.isChecked()) {
            kategori += "Ilmiah";
        }
        if (cb5.isChecked()) {
            kategori += "Komik";
        }
        if (cb6.isChecked()) {
            kategori += "Dan Lain Lain";
        }

        intent.putExtra("judul", Judul);
        intent.putExtra("kategori", kategori.toString());
        intent.putExtra("harga", Harga);
        intent.putExtra("penulis", Penulis);
        intent.putExtra("imlo", ImLo);
        intent.putExtra("stok", stok);

        startActivity(intent);

    }

    //Lifecycle
    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "Silahkan Masukan Data Produk", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "Input Data Sedang Berjalan",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "Mohon Menunggu",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "Produk Berhasil Ditambahkan",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Aplikasi ditutup, Selamat Tinggal",Toast.LENGTH_SHORT).show();
    }

}