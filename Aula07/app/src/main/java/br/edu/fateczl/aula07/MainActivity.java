package br.edu.fateczl.aula07;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.InvalidClassException;

import br.edu.fateczl.aula07.model.ContaBancária;
import br.edu.fateczl.aula07.model.ContaEspecial;
import br.edu.fateczl.aula07.model.ContaPoupanca;

public class MainActivity extends AppCompatActivity {

    private Button btnCadastrar;
    private Button btnOpercacao;

    private RadioButton rbContaPoupanca;
    private RadioButton rbContaEspecial;
    private RadioButton rbRendimento;
    private RadioButton rbListar;
    private RadioButton rbSaque;
    private RadioButton rbDeposito;

    private EditText etNome;
    private EditText etNumeroConta;
    private EditText etSaldo;
    private EditText etVariavel;
    private EditText etValor;
    private TextView tvSaida;

    private ContaEspecial e;
    private ContaPoupanca p;

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

        rbContaPoupanca = findViewById(R.id.rbContaPoupanca);
        rbContaPoupanca.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        rbContaPoupanca.setChecked(true);
        rbContaEspecial = findViewById(R.id.rbContaEspecial);
        rbContaEspecial.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        rbRendimento = findViewById(R.id.rbRendimento);
        rbRendimento.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        rbListar = findViewById(R.id.rbListar);
        rbListar.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        rbListar.setChecked(true);
        rbSaque = findViewById(R.id.rbSaque);
        rbSaque.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        rbDeposito = findViewById(R.id.rbDeposito);
        rbDeposito.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        etNome = findViewById(R.id.etNome);
        etNome.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        etNumeroConta = findViewById(R.id.etNumeroConta);
        etNumeroConta.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        etSaldo = findViewById(R.id.etSaldo);
        etSaldo.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        etVariavel = findViewById(R.id.etVariavel);
        etVariavel.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        etValor = findViewById(R.id.etValor);
        etValor = findViewById(R.id.etValor);



        atualizaActivity();

        rbContaEspecial.setOnCheckedChangeListener((buttonView, isChecked) -> atualizaActivity());
        rbListar.setOnCheckedChangeListener((buttonView, isChecked) -> atualizaActivity());
        rbRendimento.setOnCheckedChangeListener((buttonView, isChecked) -> atualizaActivity());


        tvSaida = findViewById(R.id.tvSaida);

        btnCadastrar = findViewById(R.id.btnCadastrar);
        btnCadastrar.setOnClickListener(op -> CriarUsuario());
        btnOpercacao = findViewById(R.id.btnOperacao);
        btnOpercacao.setEnabled(false);
        btnOpercacao.setOnClickListener(op -> Operacao());
    }

    private void atualizaActivity() {

        if (rbContaEspecial.isChecked()){
            etVariavel.setHint("Limite");
        } else {
            etVariavel.setHint("Dia Rendimento");
        }

        if (rbListar.isChecked()){
            etValor.setVisibility(View.GONE);
        } else {
            etValor.setVisibility(View.VISIBLE);
        }

        if (rbContaPoupanca.isChecked()) {
            if (rbRendimento.isChecked()) {
                etValor.setHint("Taxa");
            } else {
                etValor.setHint("Valor");
            }
        } else {
            rbRendimento.setVisibility(View.GONE);
        }

    }

    private void CriarUsuario() {
        String nome = etNome.getText().toString();
        int numConta = Integer.parseInt(etNumeroConta.getText().toString());
        float saldo = Float.parseFloat(etSaldo.getText().toString());

        if (rbContaPoupanca.isChecked()){
            int diaRendimento = Integer.parseInt(etVariavel.getText().toString());
            p = new ContaPoupanca(nome, numConta, saldo, diaRendimento);
        } else {
            float limite = Float.parseFloat(etVariavel.getText().toString());
            e = new ContaEspecial(nome, numConta, saldo, limite);
            rbRendimento.setEnabled(false);
        }
        rbContaEspecial.setEnabled(false);
        rbContaPoupanca.setEnabled(false);
        btnOpercacao.setEnabled(true);
    }

    private void Operacao() {
        tvSaida.setText(" ");
        if (rbRendimento.isChecked()){
            float taxa = Float.parseFloat(etValor.getText().toString());
            if (taxa <= 0){
                tvSaida.setText("Taxa informado é inválido");
            } else {
                    p.calcularNovoSaldo(taxa);
                    tvSaida.setText("Novo Saldo R$"+p.getSaldo());
            }

        } else if (rbSaque.isChecked()) {
            if (rbContaPoupanca.isChecked()){
                float valor = Float.parseFloat(etValor.getText().toString());
                tvSaida.setText(p.sacar(valor));
            } else {
                float valor = Float.parseFloat(etValor.getText().toString());
                tvSaida.setText(e.sacar(valor));
            }
        } else if (rbDeposito.isChecked()) {
            if (rbContaPoupanca.isChecked()){
                float valor = Float.parseFloat(etValor.getText().toString());
                tvSaida.setText(p.depositar(valor));
            } else {
                float valor = Float.parseFloat(etValor.getText().toString());
                tvSaida.setText(e.depositar(valor));
            }
        } else {
            if (rbContaPoupanca.isChecked()){
                tvSaida.setText(p.toString());
            } else{
                tvSaida.setText(e.toString());
            }
        }

    }
}