package com.umg.proyecto_final_oficial;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.card.MaterialCardView;
import com.umg.proyecto_final_oficial.BaseDatos.DbHelper;

public class PlatosActivity extends AppCompatActivity {

    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_platos);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        dbHelper = new DbHelper(this);

        // Aquí puedes añadir un Listener para cada tarjeta
        MaterialCardView cardView1 = findViewById(R.id.cardView1);
        cardView1.setOnClickListener(v -> {
            String itemName = getString(R.string.TextViewHam);
            String itemPrice = getString(R.string.price);
            saveSelection(itemName, itemPrice, "Plato");
            showAddedMessage(itemName); // Mostrar mensaje de agregado
        });

        MaterialCardView cardView2 = findViewById(R.id.cardView2);
        cardView2.setOnClickListener(v -> {
            String itemName = getString(R.string.TextViewPiz);
            String itemPrice = getString(R.string.pricePiz);
            saveSelection(itemName, itemPrice, "Plato");
            showAddedMessage(itemName); // Mostrar mensaje de agregado
        });

        MaterialCardView cardView3 = findViewById(R.id.cardView3);
        cardView3.setOnClickListener(v -> {
            String itemName = getString(R.string.TextViewHue);
            String itemPrice = getString(R.string.priceHue);
            saveSelection(itemName, itemPrice, "Plato");
            showAddedMessage(itemName); // Mostrar mensaje de agregado
        });
    }

    private void saveSelection(String itemName, String itemPrice, String itemType) {
        dbHelper.insertSelectedItem(itemName, itemPrice, itemType); // Cambiado a insertSelectedItem
    }

    private void showAddedMessage(String itemName) {
        Toast.makeText(this, itemName + " ha sido agregado a la orden.", Toast.LENGTH_SHORT).show();
    }
}
