package br.edu.fateczl.aula10;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import br.edu.fateczl.aula10.controller.ControllerRetangulo;
import br.edu.fateczl.aula10.model.Circulo;
import br.edu.fateczl.aula10.model.Retangulo;

public class RetanguloFragment extends Fragment {
    private View view;
    private EditText etBase;
    private EditText etAltura;
    private Button bntCalcularPerimetroRetangulo;
    private Button bntCalcularAreaRetangulo;
    private TextView tvSaidaRetangulo;
    private ControllerRetangulo Cr = new ControllerRetangulo();

    public RetanguloFragment() {
        super();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_retangulo, container, false);
        etBase = view.findViewById(R.id.etBase);
        etAltura = view.findViewById(R.id.etAltura);
        bntCalcularPerimetroRetangulo = view.findViewById(R.id.bntCalcularPerimetroRetangulo);
        bntCalcularAreaRetangulo = view.findViewById(R.id.bntCalcularAreaRetangulo);
        tvSaidaRetangulo = view.findViewById(R.id.tvSaidaRetangulo);

        bntCalcularAreaRetangulo.setOnClickListener(op -> calculoAreaRetangulo());
        bntCalcularPerimetroRetangulo.setOnClickListener(op -> calculoPerimetroRetangulo());

        return view;
    }

    private void calculoPerimetroRetangulo() {
        Retangulo r = criaRetangulo();
        LimpaTela();
        float perimetro = Cr.calculoPerimetro(r);
        String saida = "Perimetro: " + perimetro;
        tvSaidaRetangulo.setText(saida);
    }

    private void calculoAreaRetangulo() {
        Retangulo r = criaRetangulo();
        LimpaTela();
        float area = Cr.calculoArea(r);
        String saida = "Área: "+ area;
        tvSaidaRetangulo.setText(saida);
    }

    private Retangulo criaRetangulo() {
        Retangulo retangulo = new Retangulo();
        retangulo.setBase(Float.parseFloat(etBase.getText().toString()));
        retangulo.setAltura(Float.parseFloat(etAltura.getText().toString()));
        return retangulo;
    }

    private void LimpaTela(){
        etBase.setText("");
        etAltura.setText("");
    }
}