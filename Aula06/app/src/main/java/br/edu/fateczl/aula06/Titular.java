package br.edu.fateczl.aula06;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import br.edu.fateczl.aula06.model.ProfessorTitular;

public class Titular extends AppCompatActivity {

    private TextView tvResultadoTitular;

    private EditText etAnoIngresso;
    private EditText etSalarioAtual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_titular);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvResultadoTitular = findViewById(R.id.tvResultadoTitular);
        etAnoIngresso = findViewById(R.id.etAnosInstituicao);
        etSalarioAtual = findViewById(R.id.etSalarioAtual);

        Button btCalcularSalarioTitular = findViewById(R.id.calcSalario);
        btCalcularSalarioTitular.setOnClickListener(op -> calcularSarioTitular());

        Button btVoltar = findViewById(R.id.btnVoltar);
        btVoltar.setOnClickListener(op -> trocaTela());
    }

    private void calcularSarioTitular() {
        int anosInstituicao = Integer.parseInt(etAnoIngresso.getText().toString());
        double salarioAtual = Double.parseDouble(etSalarioAtual.getText().toString());

        ProfessorTitular p = new ProfessorTitular();
        p.setAnosInstituicao(anosInstituicao);
        p.setSalario(salarioAtual);
        double salario = p.calculoSalario();
        String resultado = getString(R.string.result) + " " +  salario;
        tvResultadoTitular.setText(resultado);
    }

    private void trocaTela() {
        Intent intent = new Intent(Titular.this, MainActivity.class);
        startActivity(intent);
    }
}