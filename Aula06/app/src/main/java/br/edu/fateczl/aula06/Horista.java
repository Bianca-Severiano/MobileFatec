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

import br.edu.fateczl.aula06.model.ProfessorHorista;

public class Horista extends AppCompatActivity {
    private Button btVoltar;
    private Button calcSalario;

    private EditText numHoras;
    private EditText valorHora;

    private TextView tvResultadoHorista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_horista);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        numHoras = findViewById(R.id.etHorasT);
        valorHora = findViewById(R.id.etValorHora);
        tvResultadoHorista = findViewById(R.id.tvResultadoHorista);

        calcSalario = findViewById(R.id.btnCalcular);
        calcSalario.setOnClickListener(op -> calcularSalarioHorista());

        btVoltar = findViewById(R.id.btnVoltar);
        btVoltar.setOnClickListener(op -> trocaTela());
    }

    private void calcularSalarioHorista() {
        int horasAula = Integer.parseInt(numHoras.getText().toString());
        double valorHoraTrabalhada = Double.parseDouble(valorHora.getText().toString());
        ProfessorHorista p = new ProfessorHorista();
        p.setHorasAula(horasAula);
        p.setValorHoraAula(valorHoraTrabalhada);
        double salario = p.calculoSalario();
        String resultado = getString(R.string.result) + " " +  salario;
        tvResultadoHorista.setText(resultado);
    }

    private void trocaTela() {
        Intent intent = new Intent(Horista.this, MainActivity.class);
        startActivity(intent);
    }
}