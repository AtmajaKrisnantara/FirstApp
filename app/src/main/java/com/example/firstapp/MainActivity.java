package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String Note = "tidak ada catatan";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String Note = "Catatan lokal";

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, "Activity utama sedang dibuat...", Toast.LENGTH_SHORT).show();
        Log.i("<note>", this.Note);
        this.Note = "Activity utama sudah dibuat";

        Button button = findViewById(R.id.button);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra("message", "Activity kedua akan ditampilkan...");
            startActivity(intent);
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "Activity utama siap berinteraksi", Toast.LENGTH_SHORT).show();
        Log.i("<note>", Note + "di onCreate");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "Activity utama sedang akan dihentikan", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "Activity utama sedang mau dihentikan", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "Activity utama siap ditampilkan", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Activity utama sedang mau ditutup", Toast.LENGTH_SHORT).show();
    }
    public void composeEmail(View v) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{ "c.asnawi@gmail.com" });
        intent.putExtra(Intent.EXTRA_SUBJECT, "Percobaan Kuliah P.A.M.");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
    public void openFacebook(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://facebook.com"));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}