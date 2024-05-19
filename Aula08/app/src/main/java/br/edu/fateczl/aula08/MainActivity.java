package br.edu.fateczl.aula08;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import br.edu.fateczl.aula08.model.Ingresso;
import br.edu.fateczl.aula08.model.IngressoVIP;

public class MainActivity extends AppCompatActivity {

    private EditText etIdentificador;
    private EditText etValor;
    private EditText etTaxa;
    private EditText etFuncao;

    private CheckBox cbVIP;

    private Button bntCalcular;

    private Ingresso ingresso;

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

        etIdentificador = findViewById(R.id.etId);
        etValor = findViewById(R.id.etValor);
        etFuncao = findViewById(R.id.etFuncao);
        etTaxa = findViewById(R.id.etT);
        cbVIP = findViewById(R.id.cbVIP);
        cbVIP.setChecked(false);
        bntCalcular = findViewById(R.id.btnProsseguir);

        bntCalcular.setOnClickListener(op -> Prosseguir());

        atualizarView();
        cbVIP.setOnCheckedChangeListener((button, isCheched) -> atualizarView());
    }

    private void atualizarView() {
        if (cbVIP.isChecked()){
            etFuncao.setVisibility(View.VISIBLE);
        }
        else {
            etFuncao.setVisibility(View.GONE);
        }
    }

    private void Prosseguir() {
        String id = etIdentificador.getText().toString();
        float valor = Float.parseFloat(etValor.getText().toString());
        float taxa = Float.parseFloat(etTaxa.getText().toString());
        float valorFinal;
        String tipo;
        Bundle bundle = new Bundle();

        if (cbVIP.isChecked()){
            tipo = "VIP";
            String funcao = etFuncao.getText().toString();
            bundle.putString("funcao", funcao);
            ingresso = new IngressoVIP(funcao);
            ingresso.setValor(valor);
            valorFinal = ingresso.valorFinal(taxa);
        } else {
            tipo = "Normal";
            ingresso = new Ingresso();
            ingresso.setValor(valor);
            valorFinal = ingresso.valorFinal(taxa);
        }


        bundle.putString("id", id);
        bundle.putFloat("valor", valor);
        bundle.putFloat("taxa", taxa);
        bundle.putString("tipo", tipo);
        bundle.putFloat("ValorFinal", valorFinal);
        trocaTela(bundle);
    }

    private void trocaTela(Bundle bundle) {
        Intent i = new Intent(this, saida.class);
        i.putExtras(bundle);
        this.startActivity(i);
        this.finish();

    }
}