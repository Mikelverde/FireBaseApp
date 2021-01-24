package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText txtNombre,txtApellidos,txtCorreo,txtContraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNombre=findViewById(R.id.txt_nombre);
        txtApellidos=findViewById(R.id.txt_apellidos);
        txtCorreo=findViewById(R.id.txt_correo);
        txtContraseña=findViewById(R.id.txt_contraseña);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.icon_add:
                Toast.makeText(this, "ADD", Toast.LENGTH_SHORT).show();
                if(validarFormulario()){
                    limpiarFormulario();
                }
                break;
            case R.id.icon_delete:
                Toast.makeText(this, "DELETE", Toast.LENGTH_SHORT).show();
                break;
            case R.id.icon_save:
                Toast.makeText(this, "SAVE", Toast.LENGTH_SHORT).show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    public void limpiarFormulario(){
        txtNombre.setText("");
        txtApellidos.setText("");
        txtCorreo.setText("");
        txtContraseña.setText("");
    }

    public Boolean validarFormulario(){

        boolean formularioCorrecto=true;

        if (txtNombre.getText().toString().isEmpty()){
            txtNombre.setError("Error");
            formularioCorrecto=false;
        }if (txtApellidos.getText().toString().isEmpty()){
            txtApellidos.setError("Error");
            formularioCorrecto=false;
        }
        if (txtCorreo.getText().toString().isEmpty()){
            txtCorreo.setError("Error");
            formularioCorrecto=false;
        }
        if (txtContraseña.getText().toString().isEmpty()){
            txtContraseña.setError("Error");
            formularioCorrecto=false;
        }
        return formularioCorrecto;
    }
}
