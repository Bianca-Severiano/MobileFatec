package br.edu.fateczl.aula09;

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
import br.edu.fateczl.aula09.model.AtletaOutros;

public class OutroFragment extends Fragment {

    private View view;

    private EditText nomeO;
    private EditText bairroO;
    private EditText dataNascO;
    private EditText academia;
    private EditText recorde;
    private Button bntProsseguirO;


    public OutroFragment() {
        super();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_outro, container, false);
        nomeO = view.findViewById(R.id.etNomeOutro);
        bairroO = view.findViewById(R.id.etBairroOutro);
        dataNascO = view.findViewById(R.id.etNascOutro);
        academia = view.findViewById(R.id.etAcademia);
        bntProsseguirO = view.findViewById(R.id.btnOutro);
        recorde = view.findViewById(R.id.etRecordeOutro);
        bntProsseguirO.setOnClickListener(op -> Prosseguir());
        return view;
    }

    private void Prosseguir() {
        AtletaOutros a = new AtletaOutros();
        a.setNome(nomeO.getText().toString());
        a.setBairro(bairroO.getText().toString());
        a.setAcademia(academia.getText().toString());

        String dataNascStr = dataNascO.getText().toString();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yy");
        try {
            Date dataNasc = sdf.parse(dataNascStr);
            a.setData_nasc(dataNasc);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        a.setRecordeSegundos(Float.parseFloat(recorde.getText().toString()));
        Toast.makeText(view.getContext(), a.toString(), Toast.LENGTH_LONG).show();
    }
}