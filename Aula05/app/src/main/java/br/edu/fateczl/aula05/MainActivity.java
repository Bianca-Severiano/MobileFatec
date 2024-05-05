package br.edu.fateczl.aula05;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private RadioButton rb1Dados;
    private RadioButton rb2Dados;
    private RadioButton rb3Dados;
    private Spinner spQtdFaces;
    private Button btnJogar;
    private TextView tvSaida;




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

        rb1Dados = findViewById(R.id.rb1Dados);
        rb1Dados.setChecked(true);
        rb2Dados = findViewById(R.id.rb2Dados);
        rb3Dados = findViewById(R.id.rb3Dados);
        spQtdFaces = findViewById(R.id.spQtdFaces);
        btnJogar = findViewById(R.id.btnJogarDados);
        tvSaida = findViewById(R.id.tvSaidaDados);

        preencheSpinner();
        btnJogar.setOnClickListener(op -> jogarDados());

    }

    private void jogarDados() {
        StringBuffer buffer = new StringBuffer();
        Integer qtd = (Integer) spQtdFaces.getSelectedItem();

        if (rb1Dados.isChecked()){
            buffer.append("Dado 1: " + geraAleatorio(qtd) + "\n");
        } else if (rb2Dados.isChecked()) {
            for (int i = 1; i < 3; i++) {
                buffer.append("Dado " + i +": " + geraAleatorio(qtd) + "\n");
            }
        } else {
            for (int i = 1; i < 4; i++) {
                buffer.append("Dado " + i +": " + geraAleatorio(qtd) + "\n");
            }
        }
        tvSaida.setText(buffer.toString());
    }

    private int geraAleatorio(int qtdFaces){
        Random random = new Random();
        int numeroAleatorio = random.nextInt(qtdFaces) + 1;
        return numeroAleatorio;
    }

    private void preencheSpinner() {
        List<Integer> lista = new ArrayList<>();
        lista.add(4);
        lista.add(6);
        lista.add(8);
        lista.add(10);
        lista.add(12);
        lista.add(20);
        lista.add(100);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, lista);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spQtdFaces.setAdapter(adapter);
    }
}