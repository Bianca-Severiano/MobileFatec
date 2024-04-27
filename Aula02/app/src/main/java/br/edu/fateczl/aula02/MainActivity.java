package br.edu.fateczl.aula02;

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

    private EditText etValorA;
    private EditText etValorB;
    private EditText etValorC;
    private TextView tvEquacaoStatus;
    private TextView tvx1;
    private TextView tvx2;
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

        etValorA = findViewById(R.id.etValorA);
        etValorA.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        etValorB = findViewById(R.id.etValorB);
        etValorB.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        etValorC = findViewById(R.id.etValorC);
        etValorC.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tvEquacaoStatus = findViewById(R.id.tvEquacaoStatus);
        tvEquacaoStatus.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tvx1 = findViewById(R.id.tvx1);
        tvx1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tvx2 = findViewById(R.id.tvx2);
        tvx2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        btnCalcular = findViewById(R.id.btnCalcular);

        btnCalcular.setOnClickListener(op -> Calculo());
    }

    private void Calculo(){
        float valorA = Float.parseFloat(etValorA.getText().toString());
        float valorB = Float.parseFloat(etValorB.getText().toString());
        float valorC = Float.parseFloat(etValorC.getText().toString());

        if (valorA != 0){
            double delta = ((valorB*valorB)- 4 * valorA * valorC);
            if ( delta < 0){
                String tvEqucaoS = "Equação não tem raízes reais. Delta = " + delta;
                tvEquacaoStatus.setText(tvEqucaoS);
                tvx1.setText(" ");
                tvx2.setText(" ");
            } else {
                double x1 = (((valorB * (-1)) + (Math.sqrt(delta)))/(2*valorA));
                double x2 = (((valorB * (-1)) - (Math.sqrt(delta)))/(2*valorA));

                String tvEqucaoS = " ";
                tvEquacaoStatus.setText(tvEqucaoS);

                String tvX1 = getString(R.string.resultado1) + " " + x1;
                String tvX2 = getString(R.string.resultado2) + " " + x2;
                tvx1.setText(tvX1);
                tvx2.setText(tvX2);
            }
        } else {
            String tvEqucaoS = "Não se trata de uma equação de segundo grau";
            tvEquacaoStatus.setText(tvEqucaoS);
            tvx1.setText(" ");
            tvx2.setText(" ");
        }


    }
}