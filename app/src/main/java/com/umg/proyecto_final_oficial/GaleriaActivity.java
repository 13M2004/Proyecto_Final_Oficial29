package com.umg.proyecto_final_oficial;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class GaleriaActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private int currentImageIndex = 0; // Índice de imagen actual
    private ImageView[] imageViews; // Array de ImageView para mostrar las fotos
    private Button btnCamara;
    private ArrayList<Bitmap> imageBitmaps; // Almacena Fotos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_galeria);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializar el botón y los ImageView
        btnCamara = findViewById(R.id.btnCamara);
        imageViews = new ImageView[]{
                findViewById(R.id.imageView1),
                findViewById(R.id.imageView2),
                findViewById(R.id.imageView3)
        };

        // Inicializar la lista de imágenes
        imageBitmaps = new ArrayList<>();

        // botón foto
        btnCamara.setOnClickListener(view -> abrirCamara());

        // Restaurar imágenes al recrear la actividad
        restoreImages();
    }

    private void abrirCamara() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            // Verificar que el índice de imagen actual esté dentro del rango
            if (currentImageIndex < imageViews.length) {
                Bundle extras = data.getExtras();
                Bitmap imgBitmap = (Bitmap) extras.get("data");

                // Almacenar la imagen en la lista
                imageBitmaps.add(imgBitmap);

                // Asignar la imagen al ImageView correspondiente
                imageViews[currentImageIndex].setImageBitmap(imgBitmap);
                imageViews[currentImageIndex].setVisibility(View.VISIBLE); // Hacer visible el ImageView
                currentImageIndex++; // Incrementar el índice
            }
        }
    }

    private void restoreImages() {
        // Restaurar imágenes desde la lista a los ImageView
        for (int i = 0; i < imageBitmaps.size() && i < imageViews.length; i++) {
            imageViews[i].setImageBitmap(imageBitmaps.get(i));
            imageViews[i].setVisibility(View.VISIBLE); // Hacer visible el ImageView
            currentImageIndex = i + 1; // Ajustar el índice actual
        }
    }
}




