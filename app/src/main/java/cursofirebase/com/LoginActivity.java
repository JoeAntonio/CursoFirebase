package cursofirebase.com;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText correo, clave;
    private Button registrar, acceder;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        auth = FirebaseAuth.getInstance();

        correo = (EditText) findViewById(R.id.id_correo);
        clave = (EditText) findViewById(R.id.id_clave);
        registrar = (Button) findViewById(R.id.boton_registrate);
        acceder = (Button) findViewById(R.id.boton_acceder);

        registrar.setOnClickListener(this);
        acceder.setOnClickListener(this);

        //ESTO IMPLEMENTA UN METODO CADA VEZ QUE SUCEDE UN CAMBIO.
        listener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                //OBTENEMOS EL USUARIO ACTUAL.
                FirebaseUser user = auth.getCurrentUser();
                if (user != null) {
                    //SI ESTA LOGEADO.
                    abrirCuenta();
                    Toast.makeText(getApplicationContext(), "Correcto y logueado", Toast.LENGTH_LONG).show();
                }
            }
        };
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.boton_acceder:
                ingresar();
                break;

            case R.id.boton_registrate:
                Intent intent = new Intent(LoginActivity.this, RegistrarActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    private void ingresar() {
        String userE = correo.getText().toString();
        String password = clave.getText().toString();
        //TIENE QUE CUMPLIR QUE LOS VALORES NO ESTEN VACIOS.
        if (!userE.isEmpty() && !password.isEmpty()) {
            auth.signInWithEmailAndPassword(userE,password).
                    addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Correcto", Toast.LENGTH_LONG).show();
                                abrirCuenta();
                            } else {
                                Toast.makeText(getApplicationContext(), "Incorrecto", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }
    }

    private void abrirCuenta(){
        Intent i = new Intent(getApplicationContext(), InicioActivity.class);
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
