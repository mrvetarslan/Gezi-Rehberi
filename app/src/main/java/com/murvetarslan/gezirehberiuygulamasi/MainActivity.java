package com.murvetarslan.gezirehberiuygulamasi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Spinner spinner;
    public static String selected = "";
    public static int secilenSehirId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //sehirSecmeSpinner ı adında spinner oluşturuyorum.Bu şehirleri listeleyen bir pencere tarzı bir şey
        Spinner spinner = findViewById(R.id.sehirSecmeSpinner);

        // Veritabanı oluşturma
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // options adında liste oluşturuldu.
        List<String> options = new ArrayList<>();

        //my_table tablosu oluşturup veritabanından veri alarak options içine ekler
        Cursor cursor = db.rawQuery("SELECT name FROM sehirler_tablo", null);
        while (cursor.moveToNext()) {
            options.add(cursor.getString(0));
        }

        //Spinner bileşenine bir ArrayAdapter bağlayarak, seçeneklerin görüntülenmesi sağlanır
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                if (position != 0){
                    MainActivity.selected = adapterView.getItemAtPosition(position).toString(); //Seçilen ögenin değerini MainActivity de olan selected değerine atıyor
                    MainActivity.secilenSehirId = position; //Seçilen pozisyonu secilenSehirId değişkenine atıyor
                    Toast.makeText(getApplicationContext(),selected + " seçildi.", Toast.LENGTH_SHORT).show(); //Toast mesajı gösterir
                    //Sayfa değişimi ve başlatılması
                    Intent intent = new Intent(MainActivity.this,SehirSayfasi.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Hiçbir öğe seçilmediğinde yapılacak işlemler buraya yazılabilir
            }
        });

        cursor.close();
        dbHelper.close();
    }







}