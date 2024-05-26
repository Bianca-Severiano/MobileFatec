package br.edu.fateczl.aula10;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import br.edu.fateczl.aula10.controller.ControllerCirculo;
import br.edu.fateczl.aula10.model.Circulo;


public class CirculoFragment extends Fragment {

    private View view;
    private EditText etRaio;
    private Button bntCalcularPerimetroCirculo;
    private Button bntCalcularAreaCirculo;
    private TextView tvSaidaCirculo;
    private ControllerCirculo Cc = new ControllerCirculo();

    public CirculoFragment() {
       super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_circulo, container, false);
        etRaio = view.findViewById(R.id.etRaio);
        bntCalcularPerimetroCirculo = view.findViewById(R.id.bntCalcularPerimetroCirculo);
        bntCalcularAreaCirculo = view.findViewById(R.id.bntCalcularAreaCirculo);
        tvSaidaCirculo = view.findViewById(R.id.tvSaidaCirculo);

        bntCalcularAreaCirculo.setOnClickListener(op -> calculoAreaCirculo());
        bntCalcularPerimetroCirculo.setOnClickListener(op -> calculoPerimetroCirculo());

        return view;
    }

    private void calculoPerimetroCirculo() {
        Circulo c = criaCirculo();
        LimpaTela();
        float perimetro = Cc.calculoPerimetro(c);
        tvSaidaCirculo.setText("Perimetro: " + perimetro);
    }

    private void calculoAreaCirculo() {
        Circulo c = criaCirculo();
        LimpaTela();
        float area = Cc.calculoArea(c);
        tvSaidaCirculo.setText("Área: "+ area);
    }

    private Circulo criaCirculo() {
        Circulo circulo = new Circulo();
        circulo.setRaio(Float.parseFloat(etRaio.getText().toString()));
        return circulo;
    }

    private void LimpaTela(){
        etRaio.setText(" ");
    }
}