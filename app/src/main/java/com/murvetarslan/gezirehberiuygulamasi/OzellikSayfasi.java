package com.murvetarslan.gezirehberiuygulamasi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

//Özelliklerin bilgilerini gösterdiğimiz sınıf
public class OzellikSayfasi extends AppCompatActivity {
    Spinner spinner;
    public static String selectedOzellik = "";
    private ScrollView scrollView;
    private Button ileriVeri;
    private Button geriVeri;
    private int currentIndex = 0;
    public int kategoriNo = SehirSayfasi.kategorid;
    public int sehirNo = MainActivity.secilenSehirId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ozellik_sayfasi);

        TextView veriBasligi = findViewById(R.id.veriBaslik);
        TextView veriAciklama = findViewById(R.id.veriAciklama);
        Button ileriVeri = findViewById(R.id.ileriVeri);
        Button geriVeri = findViewById(R.id.geriVeri);
        Spinner spinner = findViewById(R.id.ozellikSecmeSpinner);

        //Verileri çekmek için her sınıfta kullanıyorum
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        //ozellik_tablo tablosundaki ozellikAd sütunundan veri alır ve bu verileri optionsOzellik adlı bir liste içine ekler.
        List<String> optionsOzellik = new ArrayList<>();
        Cursor cursorO = db.rawQuery("SELECT ozellikAd FROM ozellik_tablo",null);
        while (cursorO.moveToNext()) {
            optionsOzellik.add(cursorO.getString(0));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, optionsOzellik);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        switch (kategoriNo){
            case 1:
                spinner.setSelection(1);
                break;
            case 2:
                spinner.setSelection(2);
                break;
            case 3:
                spinner.setSelection(3);
                break;
            case 4:
                spinner.setSelection(4);
                break;
            case 5:
                spinner.setSelection(5);
                break;
            case 6:
                spinner.setSelection(6);
                break;
            case 7:
                spinner.setSelection(7);
                break;
            case 8:
                spinner.setSelection(8);
                break;

            default:
                Toast.makeText(getApplicationContext(),"Hata!", Toast.LENGTH_SHORT).show();
        }

        //Veri gösterimleri yapılıyor
        List<String> veriler = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT baslik FROM bilgiler_tablo WHERE sehirId ="+sehirNo+"  AND ozID ="+kategoriNo+"",null);
        while (cursor.moveToNext()) {
            veriler.add(cursor.getString(0));
        }
        List<String> altVeriler = new ArrayList<>();
        Cursor cursor1 = db.rawQuery("SELECT aciklama FROM bilgiler_tablo WHERE sehirId ="+sehirNo+" AND ozID ="+kategoriNo+"",null);
        while (cursor1.moveToNext()) {
            altVeriler.add(cursor1.getString(0));
        }

        String currentData = veriler.get(currentIndex);
        veriBasligi.setText(currentData);
        String  currentData1= altVeriler.get(currentIndex);
        veriAciklama.setText(currentData1);

        cursorO.close();
        dbHelper.close();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                kategoriNo = i;
                currentIndex = 0;
                if (i != 0) {
                    OzellikSayfasi.selectedOzellik = adapterView.getItemAtPosition(i).toString();
                    Toast.makeText(getApplicationContext(),selectedOzellik + " seçildi.", Toast.LENGTH_SHORT).show();
                    SQLiteDatabase db = dbHelper.getReadableDatabase();
                    List<String> veriler = new ArrayList<>();
                    Cursor cursor = db.rawQuery("SELECT baslik FROM bilgiler_tablo WHERE sehirId =" + sehirNo + "  AND ozID =" + kategoriNo + "", null);
                    while (cursor.moveToNext()) {
                        veriler.add(cursor.getString(0));
                    }
                    List<String> altVeriler = new ArrayList<>();
                    Cursor cursor1 = db.rawQuery("SELECT aciklama FROM bilgiler_tablo WHERE sehirId =" + sehirNo + " AND ozID =" + kategoriNo + "", null);
                    while (cursor1.moveToNext()) {
                        altVeriler.add(cursor1.getString(0));
                    }

                    String currentData = veriler.get(currentIndex);
                    veriBasligi.setText(currentData);
                    String currentData1 = altVeriler.get(currentIndex);
                    veriAciklama.setText(currentData1);
                    cursorO.close();
                    dbHelper.close();
                }
                if (i == 7){
                    Toast.makeText(getApplicationContext(),"Bakımdayız.", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        ileriVeri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex++;
                if (currentIndex >= veriler.size()) {
                    currentIndex = 0;
                }

                String yeniVeri = veriler.get(currentIndex);
                veriBasligi.setText(yeniVeri);
                String altVeri = altVeriler.get(currentIndex);
                veriAciklama.setText(altVeri);
            }
        });

        geriVeri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex--;
                if (currentIndex < 0) {
                    currentIndex = veriler.size() - 1;
                }

                String yeniVeri = veriler.get(currentIndex);
                veriBasligi.setText(yeniVeri);
                String altVeri = altVeriler.get(currentIndex);
                veriAciklama.setText(altVeri);
            }
        });
    }

    public void sehirseceDon(View view){
        Intent intent = new Intent(OzellikSayfasi.this,MainActivity.class);
        startActivity(intent);
    }


}