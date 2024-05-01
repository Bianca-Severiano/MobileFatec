package br.edu.fateczl.exercicio32;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText etConsumo;
    private EditText etCombustivel;
    private Button btnCalcular;
    private TextView tvResultado;


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

        etConsumo = findViewById(R.id.etConsumo);
        etConsumo.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        etCombustivel = findViewById(R.id.etCombustivel);
        etCombustivel.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tvResultado = findViewById(R.id.tvResultado);
        tvResultado.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        btnCalcular = findViewById(R.id.btnCalcular);

        btnCalcular.setOnClickListener(op -> Calculo());
    }

    private void Calculo (){
        float consumo = Float.parseFloat(etConsumo.getText().toString());
        float combustivel = Float.parseFloat(etCombustivel.getText().toString());
        float consumoMetros = consumo * 1000;
        float autonomia = combustivel * consumoMetros;

        String retorno = getString(R.string.autonomia) + " " + autonomia + "m";
        tvResultado.setText(retorno);
    }
}