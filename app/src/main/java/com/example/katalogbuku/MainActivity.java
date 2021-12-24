package com.example.katalogbuku;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.katalogbuku.database.AppDatabase;
import com.example.katalogbuku.database.entitas.buku;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button btnTambah;
    private RecyclerView rvIndex;
    private AppDatabase database;
    private com.example.katalogbuku.BukuAdapter bukuAdapter;
    private List<buku> list = new ArrayList<>();
    private AlertDialog.Builder dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvIndex = findViewById(R.id.rv_book);

        btnTambah = (Button) findViewById(R.id.btnTambah);
        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                intent = new Intent(getApplicationContext(), ActivityTambah.class);
                startActivity(intent);
            }
        });

        database = AppDatabase.getInstance(getApplicationContext());
        list.clear();
        list.addAll(database.bukuDao().getAll());
        bukuAdapter = new com.example.katalogbuku.BukuAdapter(getApplicationContext(),list);
        bukuAdapter.setDialog(new com.example.katalogbuku.BukuAdapter.Dialog() {
            @Override
            public void onClick(int position) {
                final CharSequence[] dialogItem = {"Edit Produk", "Hapus Produk"};
                dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        switch (i){
                            case 0:
                                Intent intent = new Intent(MainActivity.this, ActivityTambah.class);
                                intent.putExtra("id_buku", list.get(position).id_buku);
                                startActivity(intent);
                                break;
                            case 1:
                                buku buku = list.get(position);
                                database.bukuDao().delete(buku);
                                onStart();
                                break;
                        }
                    }
                });
                dialog.show();

            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false);
        rvIndex.setLayoutManager(layoutManager);
        rvIndex.setAdapter(bukuAdapter);

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(com.example.katalogbuku.MainActivity.this,ActivityTambah.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        list.clear();
        list.addAll(database.bukuDao().getAll());
        bukuAdapter.notifyDataSetChanged();
    }
}