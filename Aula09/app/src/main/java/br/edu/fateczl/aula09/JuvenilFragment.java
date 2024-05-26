package br.edu.fateczl.aula09;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.edu.fateczl.aula09.model.AtletaJuvenil;

public class JuvenilFragment extends Fragment {

    private View view;

    private EditText nomeJ;
    private EditText bairroJ;
    private EditText dataNascJ;
    private EditText anosPraticaJ;
    private Button bntProsseguir;



    public JuvenilFragment() {
       super();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_juvenil, container, false);


        nomeJ = view.findViewById(R.id.etNome);
        bairroJ = view.findViewById(R.id.etBairro);
        dataNascJ = view.findViewById(R.id.etData);
        anosPraticaJ = view.findViewById(R.id.etTempoPratica);
        bntProsseguir = view.findViewById(R.id.btnJuvenil);
        
        bntProsseguir.setOnClickListener(op -> Prosseguir());
        return view;

    }

    private void Prosseguir() {
        AtletaJuvenil a = new AtletaJuvenil();
        a.setNome(nomeJ.getText().toString());
        a.setBairro(bairroJ.getText().toString());
        a.setAnosPratica(Integer.parseInt(anosPraticaJ.getText().toString()));

        String dataNascStr = dataNascJ.getText().toString();
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