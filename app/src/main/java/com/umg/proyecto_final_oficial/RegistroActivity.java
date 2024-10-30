package com.umg.proyecto_final_oficial;

import android.database.Cursor;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.umg.proyecto_final_oficial.BaseDatos.DbHelper;
public class RegistroActivity extends AppCompatActivity {
    private DbHelper dbHelper;
    private EditText editTextName, editTextPhone, editTextEmail, editTextEducation;
    private Button btnCrear, btnLeer, btnActualizar, btnEliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        // Inicialización de views
        dbHelper = new DbHelper(this);
        editTextName = findViewById(R.id.TextNombre);
        editTextPhone = findViewById(R.id.TextNumero);
        editTextEmail = findViewById(R.id.TexEmail);
        editTextEducation = findViewById(R.id.TextEscolar);
        btnCrear = findViewById(R.id.btnCrear);
        btnLeer = findViewById(R.id.btnLeer);
        btnActualizar = findViewById(R.id.btnActualizar);
        btnEliminar = findViewById(R.id.btnEliminar);

        btnCrear.setOnClickListener(v -> crearPersonal());
        btnLeer.setOnClickListener(v -> leerPersonal());
        btnActualizar.setOnClickListener(v -> actualizarPersonal());
        btnEliminar.setOnClickListener(v -> eliminarPersonal());
    }

    private void crearPersonal() {
        String name = editTextName.getText().toString();
        String phone = editTextPhone.getText().toString();
        String email = editTextEmail.getText().toString();
        String education = editTextEducation.getText().toString();
        dbHelper.insertPersonal(name, phone, email, education);
        mostrarDialogo("Éxito", "Personal agregado exitosamente.");
        limpiarCampos();
    }

    private void leerPersonal() {
        Cursor cursor = dbHelper.readPersonal();
        if (cursor.getCount() == 0) {
            mostrarDialogo("Información", "No hay datos disponibles.");
            return;
        }
        StringBuilder builder = new StringBuilder();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String phone = cursor.getString(2);
            String email = cursor.getString(3);
            String education = cursor.getString(4);
            builder.append("ID: ").append(id)
                    .append("\nNombre: ").append(name)
                    .append("\nTeléfono: ").append(phone)
                    .append("\nCorreo: ").append(email)
                    .append("\nEducación: ").append(education)
                    .append("\n\n");
        }
        cursor.close();
        mostrarDialogo("Registros de Personal", builder.toString());
    }

    private void actualizarPersonal() {
        mostrarDialogoEntradaID("Actualizar", "Introduce el ID del registro que deseas actualizar", id -> {
            String name = editTextName.getText().toString();
            String phone = editTextPhone.getText().toString();
            String email = editTextEmail.getText().toString();
            String education = editTextEducation.getText().toString();
            dbHelper.updatePersonal(id, name, phone, email, education);
            mostrarDialogo("Éxito", "Personal actualizado correctamente.");
            limpiarCampos();
        });
    }

    private void eliminarPersonal() {
        mostrarDialogoEntradaID("Eliminar", "Introduce el ID del registro que deseas eliminar", id -> {
            dbHelper.deletePersonal(id);
            mostrarDialogo("Éxito", "Personal eliminado exitosamente.");
        });
    }

    private void mostrarDialogo(String titulo, String mensaje) {
        new AlertDialog.Builder(this)
                .setTitle(titulo)
                .setMessage(mensaje)
                .setPositiveButton("OK", null)
                .setIcon(android.R.drawable.ic_dialog_info)
                .show();
    }

    private void mostrarDialogoEntradaID(String titulo, String mensaje, OnIdInputListener listener) {
        EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        new AlertDialog.Builder(this)
                .setTitle(titulo)
                .setMessage(mensaje)
                .setView(input)
                .setPositiveButton("OK", (dialog, which) -> {
                    int id = Integer.parseInt(input.getText().toString());
                    listener.onIdInput(id);
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }

    private void limpiarCampos() {
        editTextName.setText("");
        editTextPhone.setText("");
        editTextEmail.setText("");
        editTextEducation.setText("");
    }

    interface OnIdInputListener {
        void onIdInput(int id);
    }
}
