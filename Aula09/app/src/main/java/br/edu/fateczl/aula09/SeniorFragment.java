package br.edu.fateczl.aula09;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.edu.fateczl.aula09.model.AtletaJuvenil;
import br.edu.fateczl.aula09.model.AtletaSenior;
public class SeniorFragment extends Fragment {

    private View view;

    private EditText nomeS;
    private EditText bairroS;
    private EditText dataNascS;
    private RadioButton sim;
    private RadioButton nao;
    private Button bntProsseguir;
    public SeniorFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_senior, container, false);

        nomeS = view.findViewById(R.id.etNomeSenior);
        bairroS = view.findViewById(R.id.etBairroSenior);
        dataNascS = view.findViewById(R.id.etDataSenior);
        sim = view.findViewById(R.id.rbSim);
        nao = view.findViewById(R.id.rbNao);
        nao.isChecked();
        bntProsseguir = view.findViewById(R.id.btnSenior);

        bntProsseguir.setOnClickListener(op -> Prosseguir());
        return view;
    }

    private void Prosseguir() {
        AtletaSenior a = new AtletaSenior();
        a.setNome(nomeS.getText().toString());
        a.setBairro(bairroS.getText().toString());

        if (sim.isChecked()){
            a.setProblemaCardiaco("Sim");
        } else {
            a.setProblemaCardiaco("Não");
        }
        String dataNascStr = dataNascS.getText().toString();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yy");
        try {
            Date dataNasc = sdf.parse(dataNascStr);
            a.setData_nasc(dataNasc);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Toast.makeText(view.getContext(), a.toString(), Toast.LENGTH_LONG).show();
    }
}