package com.intents.juangarcia.intentsapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.Character.isDigit;
import static java.lang.Character.isLetter;

public class MainActivity extends AppCompatActivity {
    final int CAMERA = 1;
    final int GALLERY = 2;
    ImageView photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        photo = findViewById(R.id.imgPlaceholder);
    }

    public void camera(View view){
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA);
    }

    public void photoGallery(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent, "Selecciona la Aplicación"),GALLERY);
    }

    public void searchWeb(View view) {
        TextView txtUrl = findViewById(R.id.txtUrl);
        String url= txtUrl.getText().toString();

        Uri web = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, web);
        if ((intent.resolveActivity(getPackageManager()) != null) && (url.contains("w.")))
            startActivity(intent);
        else Toast.makeText(this,"no URL address detected!",Toast.LENGTH_LONG).show();
    }

    public void call(View view) {
        TextView txtCall = findViewById(R.id.txtCall);
        String number = txtCall.getText().toString();

        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + number));

        if (intent.resolveActivity(getPackageManager()) != null) startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA && resultCode == RESULT_OK) {
            Bitmap photoImage = (Bitmap) data.getExtras().get("data");
            photo.setImageBitmap(photoImage);
        }
        if (requestCode == GALLERY && resultCode == RESULT_OK){
            Uri path = data.getData();
            photo.setImageURI(path);
        }
    }
}
