package br.edu.fateczl.aula06;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button btSeguir;
    private RadioButton rbHorista;
    private RadioButton rbTitular;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rbHorista = findViewById(R.id.rbHorista);
        rbHorista.setChecked(true);
        rbTitular = findViewById(R.id.rbTitular);
        btSeguir = findViewById(R.id.btnSeguir);
        btSeguir.setOnClickListener(op -> trocaTela());
    }

    private void trocaTela() {

        if (rbHorista.isChecked()){
            Intent intent = new Intent(MainActivity.this, Horista.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(MainActivity.this, Titular.class);
            startActivity(intent);
        }
    }

}