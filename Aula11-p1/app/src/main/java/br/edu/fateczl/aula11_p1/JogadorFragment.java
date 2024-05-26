package br.edu.fateczl.aula11_p1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import br.edu.fateczl.aula11_p1.model.Jogador;

public class JogadorFragment extends Fragment {

    private View view;
    private Button btnBuscarJogador;
    private Button btnInserirJogador;
    private Button btnAtualizarJogador;
    private Button btnExcluirJogador;
    private Button btnListarJogador;
    private TextView tvSaidaJgador;

    private EditText etIDJogador;
    private EditText etNomeJogador;
    private EditText etDataNasc;
    private EditText etPeso;
    private EditText etAltura;
    private Spinner spTimes;
    
    private Jogador j;
    public JogadorFragment() {
        super();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       view =  inflater.inflate(R.layout.fragment_jogador, container, false);
        btnBuscarJogador = view.findViewById(R.id.btnBuscar_Jogador);
        btnInserirJogador = view.findViewById(R.id.btnInserir_Jogador);
        btnAtualizarJogador = view.findViewById(R.id.btnAtualizar_Jogador);
        btnExcluirJogador = view.findViewById(R.id.btnDeletar_Jogador);
        btnListarJogador = view.findViewById(R.id.btnListar_Jogador);
        tvSaidaJgador = view.findViewById(R.id.tvSaidaJogador);
        etIDJogador = view.findViewById(R.id.etID_Jogador);
        etNomeJogador = view.findViewById(R.id.etNome_Jogador);
        etDataNasc = view.findViewById(R.id.etNascJogador);
        etPeso = view.findViewById(R.id.etPeso);
        etAltura = view.findViewById(R.id.etAltura);
        spTimes = view.findViewById(R.id.spTime);

      
        btnAtualizarJogador.setOnClickListener(op -> atualizarJogador(j));
        btnInserirJogador.setOnClickListener(op -> inserirJogador(j));
        btnExcluirJogador.setOnClickListener(op -> excluirJogador(j));
        btnListarJogador.setOnClickListener(op -> listarJogadores());
        btnBuscarJogador.setOnClickListener(op -> buscarJogador(j));
        return view;
    }

    private void buscarJogador(Jogador j) {
        tvSaidaJgador.setText("Buscar Jogador");
    }

    private void listarJogadores() {
        tvSaidaJgador.setText("Listar Jogador");
    }

    private void excluirJogador(Jogador j) {
        tvSaidaJgador.setText("Excluir Jogador");
    }

    private void inserirJogador(Jogador j) {
        tvSaidaJgador.setText("Inserir Jogador");
    }

    private void atualizarJogador(Jogador j) {
        tvSaidaJgador.setText("Atualizar Jogador");
    }


}