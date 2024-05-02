package br.edu.fateczl.aula04;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private  EditText etDia;
    private EditText etMes;
    private EditText etAno;
    private TextView tvResultado;
    private TextView tvErro;
    private Button btnCalcular;

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

        etDia = findViewById(R.id.etDia);
        etDia.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        etMes = findViewById(R.id.etMes);
        etMes.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        etAno = findViewById(R.id.etAno);
        etAno.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tvResultado = findViewById(R.id.tvResultado);
        tvResultado.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tvErro = findViewById(R.id.e404);
        tvErro.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        btnCalcular = findViewById(R.id.btnCalcular);
        btnCalcular.setOnClickListener(op -> CalcularIdade());
    }


    private void CalcularIdade() {
        int dia = Integer.parseInt(etDia.getText().toString());
        int mes = Integer.parseInt(etMes.getText().toString());
        int ano = Integer.parseInt(etAno.getText().toString());

        if (validarData(dia, mes, ano) == 0) {
            tvErro.setText(R.string.dataE);
        } else {
            tvErro.setText("");
            Calendar calendar = Calendar.getInstance();
            int anoAtual = calendar.get(Calendar.YEAR);
            int mesAtual = calendar.get(Calendar.MONTH);
            int diaAtual = calendar.get(Calendar.DAY_OF_MONTH);

            int diaIdade = 0;
            int mesIdade = 0;
            int anoIdade = 0;

            if (mesAtual > mes || (mesAtual == mes && diaAtual >= dia)){ // ja fez aniversário
                anoIdade = anoAtual - ano;
            } else { // não fez aniverário
                anoIdade = anoAtual - ano - 1;
            }

            if (mesAtual > mes || (mesAtual == mes && diaAtual >= dia)){
                mesIdade = mesAtual - mes;
            } else {
                mesIdade = (12 - (mes - mesAtual));
            }

            if (diaAtual >= dia){
                diaIdade = diaAtual - dia;
            } else {
                int diasMesAnterior = diasMes((mesAtual-1), anoAtual);
                diaIdade = diasMesAnterior - (dia - diaAtual);
            }
                tvResultado.setText(anoIdade + " anos," + mesIdade + " mes(es)," + diaIdade + " dias");
            }

        }

        private int bissexto (int ano){
            if (((ano % 4 == 0) && (ano % 100 != 0)) || (ano % 400 == 0)) {
                return 1;
            } else {
                return 0;
            }
        }


    private int validarData (int dia, int mes, int ano){
        if ((dia < 1 || dia > 31 || mes < 1 || mes > 12)){
            return 0;
        }

        if ((dia == 31 && (mes == 4 || mes == 6 || mes == 9 || mes == 11))){
            return 0;
        }

        if (mes == 2 && dia > 29){
            return 0;
        }

        if (mes == 2 && dia == 29 && (bissexto(ano) == 0)) {
            return 0;
        }
        return 1;
    }

    private int diasMes(int mes, int ano){
        if (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12 ){
            return 31;
        } else {
            if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
                return 30;
            } else{
                if (bissexto(ano) == 1){
                    return 29;
                } else {
                    return 28;
                }
            }
        }
    }
}