package cursofirebase.com;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistrarActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText correo, clave;
    private Button aceptar;
    private FirebaseAuth auth;
    FirebaseAuth.AuthStateListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrar_activity);
        auth = FirebaseAuth.getInstance();

        correo = (EditText) findViewById(R.id.id_correo);
        clave = (EditText) findViewById(R.id.id_clave);
        aceptar = (Button) findViewById(R.id.boton_aceptar);

        aceptar.setOnClickListener(this);

        listener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = auth.getCurrentUser();
                if (user != null) {
                    //SI ESTA LOGUEADO.
                    abrirMiCuenta();
                }
            }
        };

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.boton_aceptar:
                registrarUsuario();
                break;
        }
    }

    private void registrarUsuario() {
        String userE = correo.getText().toString();
        String passwordE = clave.getText().toString();
        if (!userE.isEmpty() && !passwordE.isEmpty()) {
            auth.createUserWithEmailAndPassword(userE,passwordE)
                    .addOnCompleteListener(RegistrarActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Usuario registrado", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Error al registrar", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    private void abrirMiCuenta() {
            Intent i = new Intent(this, InicioActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(listener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (listener != null) {
            auth.removeAuthStateListener(listener);
        }
    }
}
