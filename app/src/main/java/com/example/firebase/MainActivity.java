package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.firebase.modelo.Persona;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private EditText txtNombre,txtApellidos,txtCorreo,txtContraseña;
    private FirebaseDatabase firebaseDatabase;
    private ListView listView;
    private DatabaseReference databaseReference;
    private ArrayList<Persona> personas=new ArrayList<Persona>();
    private ArrayAdapter<Persona> adapter;
    private Persona personaSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNombre=findViewById(R.id.txt_nombre);
        txtApellidos=findViewById(R.id.txt_apellidos);
        txtCorreo=findViewById(R.id.txt_correo);
        txtContraseña=findViewById(R.id.txt_contraseña);
        listView=findViewById(R.id.lstV_listaPersonas);

        inicializarFireBase();
        listarDatos();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                
                personaSelect=(Persona) adapterView.getItemAtPosition(i);
                txtNombre.setText(personaSelect.getNombre());
                txtApellidos.setText(personaSelect.getApellidos());
                txtCorreo.setText(personaSelect.getCorreo());
                txtContraseña.setText(personaSelect.getContraseña());
            }
        });

    }

    private void listarDatos() {

        databaseReference.child("Persona").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                personas.clear();
                for (DataSnapshot dataSnapshot:snapshot.getChildren()) {
                    Persona persona=dataSnapshot.getValue(Persona.class);
                    personas.add(persona);
                    adapter=new ArrayAdapter<Persona>(MainActivity.this, android.R.layout.simple_list_item_1,personas);
                    listView.setAdapter(adapter);
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

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
                    Persona persona=new Persona(UUID.randomUUID().toString(),
                            txtNombre.getText().toString(),
                            txtApellidos.getText().toString(),
                            txtCorreo.getText().toString(),
                            txtContraseña.getText().toString());

                    databaseReference.child("Persona").child(persona.getUid()).setValue(persona);

                    limpiarFormulario();
                }
                break;

            case R.id.icon_delete:
                databaseReference.child("Persona").child(personaSelect.getUid()).removeValue();
                limpiarFormulario();
                Toast.makeText(this, "DELETE", Toast.LENGTH_SHORT).show();
                break;

            case R.id.icon_save:
                validarFormulario();
                Persona persona= new Persona();
                persona.setUid(personaSelect.getUid());
                persona.setNombre(txtNombre.getText().toString());
                persona.setApellidos(txtApellidos.getText().toString());
                persona.setCorreo( txtCorreo.getText().toString());
                persona.setContraseña(txtContraseña.getText().toString());

                databaseReference.child("Persona").child(personaSelect.getUid()).setValue(persona);
                limpiarFormulario();

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

    public void inicializarFireBase(){

        FirebaseApp.initializeApp(this);
        firebaseDatabase=FirebaseDatabase.getInstance();
        firebaseDatabase.setPersistenceEnabled(true);
        databaseReference=firebaseDatabase.getReference();
    }
}
