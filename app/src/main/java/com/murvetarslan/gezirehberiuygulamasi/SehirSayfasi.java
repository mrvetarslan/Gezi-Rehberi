package com.murvetarslan.gezirehberiuygulamasi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

//Seçilen şehrin tüm özelliklerinin gösterilmesi için oluşturulan sınıf
public class SehirSayfasi extends AppCompatActivity {
    public static int kategorid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sehir_sayfasi);

        //Burdaki sehirIsmi adlı textView MainActivity sayfasındaki selected değişkeninden alındı.
        TextView textView = findViewById(R.id.sehirIsmi);
        textView.setText(MainActivity.selected);

    }

    //Geri butonu oluşturuldu
    public void anasayfaDon(View view){
        Intent intent = new Intent(SehirSayfasi.this,MainActivity.class);
        startActivity(intent);
    }

    //Burada tüm kategoriler tanımlanıyor ve kategorid ler veriliyor.Sonrasında da sayfa değiştirilip 3. sayfaya geçiliyor.
    public void genelBilgi(View view){
        kategorid = 1;
        Intent intent = new Intent(SehirSayfasi.this,OzellikSayfasi.class);
        startActivity(intent);
    }

    public void gezilecekYerler(View view){
        kategorid = 2;
        Intent intent = new Intent(SehirSayfasi.this,OzellikSayfasi.class);
        startActivity(intent);
    }

    public void tarih(View view){
        kategorid = 3;
        Intent intent = new Intent(SehirSayfasi.this,OzellikSayfasi.class);
        startActivity(intent);
    }

    public void yemekler(View view){
        kategorid = 4;
        Intent intent = new Intent(SehirSayfasi.this,OzellikSayfasi.class);
        startActivity(intent);
    }

    public void yoresel(View view){
        kategorid = 5;
        Intent intent = new Intent(SehirSayfasi.this,OzellikSayfasi.class);
        startActivity(intent);
    }

    public void ulasim(View view){
        kategorid = 6;
        Intent intent = new Intent(SehirSayfasi.this,OzellikSayfasi.class);
        startActivity(intent);
    }

    public void havaDurumu(View view){
        kategorid = 7;
        Intent intent = new Intent(SehirSayfasi.this,OzellikSayfasi.class);
        startActivity(intent);
    }

    public void muzik(View view){
        kategorid = 8;
        Intent intent = new Intent(SehirSayfasi.this,OzellikSayfasi.class);
        startActivity(intent);
    }
}