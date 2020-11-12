package com.example.firstapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {

    private static final int PICK_CONTACT_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        /*
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        String message = bundle.getString("message");
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        */
    }
    public void openContactList(View v) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setData(ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(intent, PICK_CONTACT_REQUEST);
    }

    public void openCamera(View v) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == PICK_CONTACT_REQUEST && resultCode == RESULT_OK && data != null) {
            String contactName = null;
            Uri result = data.getData();
            Log.d("<contact>", result.toString());
            assert result != null;
            Cursor cursor = getContentResolver()
                    .query(result,
                            null,
                            null,
                            null,
                            null);
            assert cursor != null;
            if (cursor.moveToFirst()) {
                contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            }
            cursor.close();
            assert contactName != null;
            Toast.makeText(this, contactName, Toast.LENGTH_SHORT).show();
            Log.d("<contact", contactName);
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}