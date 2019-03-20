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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText correo, clave;
    private Button registrar, acceder;
    FirebaseAuth auth;

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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.boton_registrate:
                Intent intent = new Intent(LoginActivity.this, RegistrarActivity.class);
                startActivity(intent);
                break;

            case R.id.boton_acceder:
                String userE = correo.getText().toString();
                String password = clave.getText().toString();

                if (TextUtils.isEmpty(userE)) {
                    Toast.makeText(getApplicationContext(), "coloca un correo", Toast.LENGTH_SHORT).show();
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "coloca una contraseña", Toast.LENGTH_SHORT).show();
                }

                auth.signInWithEmailAndPassword(userE,password).
                        addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "correo o contraseña incorrectos",
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    Intent intent1 = new Intent(LoginActivity.this, PrincipalActivity.class);
                                    startActivity(intent1);
                                    finish();
                                }
                            }
                        });
                break;
        }
    }
}
