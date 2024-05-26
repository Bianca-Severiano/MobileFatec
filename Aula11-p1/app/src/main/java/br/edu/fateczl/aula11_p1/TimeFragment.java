package br.edu.fateczl.aula11_p1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import br.edu.fateczl.aula11_p1.model.Time;

public class TimeFragment extends Fragment {
    private View view;

    private Button btnBuscarTime;
    private Button btnInserirTime;
    private Button btnAtualizarTime;
    private Button btnExcluirTime;
    private Button btnListarTime;
    private TextView tvSaidaTime;

    private EditText etCodTime;
    private EditText etNomeTime;
    private EditText etCidadeTime;

    private Time t;
    public TimeFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_time, container, false);

        btnBuscarTime = view.findViewById(R.id.btnBuscar_Time);
        btnInserirTime = view.findViewById(R.id.btnInserir_Time);
        btnAtualizarTime = view.findViewById(R.id.btnAtualizar_Time);
        btnExcluirTime = view.findViewById(R.id.btnDeletar_Time);
        btnListarTime = view.findViewById(R.id.btnListar_Time);
        tvSaidaTime = view.findViewById(R.id.tvSaidaTime);
        etCodTime = view.findViewById(R.id.etCodigo);
        etNomeTime = view.findViewById(R.id.etNome_Time);
        etCidadeTime = view.findViewById(R.id.etCidade_Time);

        btnAtualizarTime.setOnClickListener(op -> atualizarJogador(t));
        btnInserirTime.setOnClickListener(op -> inserirJogador(t));
        btnExcluirTime.setOnClickListener(op -> excluirJogador(t));
        btnListarTime.setOnClickListener(op -> listarJogadores());
        btnBuscarTime.setOnClickListener(op -> buscarJogador(t));

        return view;
    }

    private void atualizarJogador(Time t) {
        tvSaidaTime.setText("Atualizar Time");
    }

    private void inserirJogador(Time t) {
        tvSaidaTime.setText("Inserir Time");
    }

    private void excluirJogador(Time t) {
        tvSaidaTime.setText("Excluir Time");
    }

    private void listarJogadores() {
        tvSaidaTime.setText("Listar Time");
    }

    private void buscarJogador(Time t) {
        tvSaidaTime.setText("Buscar Time");

    }
}