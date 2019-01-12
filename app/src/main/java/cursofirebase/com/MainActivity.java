package cursofirebase.com;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText correo, clave;
    private Button registrar, acceder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        correo = (EditText) findViewById(R.id.editText_correo);
        clave = (EditText) findViewById(R.id.editText_clave);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_registrar:
                Intent intent = new Intent(MainActivity.this, RegistrarActivity.class);
                startActivity(intent);
                finish();
                break;

            case R.id.button_acceder:

                break;
        }
    }
}
