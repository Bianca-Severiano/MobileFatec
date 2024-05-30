package br.edu.fateczl.aula11_p1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.List;

import br.edu.fateczl.aula11_p1.controller.JogadorController;
import br.edu.fateczl.aula11_p1.controller.TimeController;
import br.edu.fateczl.aula11_p1.model.Jogador;
import br.edu.fateczl.aula11_p1.model.Time;
import br.edu.fateczl.aula11_p1.persistence.JogadorDao;
import br.edu.fateczl.aula11_p1.persistence.TimeDao;

public class JogadorFragment extends Fragment {

    private View view;
    private Button btnBuscarJogador;
    private Button btnInserirJogador;
    private Button btnAtualizarJogador;
    private Button btnExcluirJogador;
    private Button btnListarJogador;
    private TextView tvSaidaJogador;

    private EditText etIDJogador;
    private EditText etNomeJogador;
    private EditText etDataNasc;
    private EditText etPeso;
    private EditText etAltura;
    private Spinner spTimes;
    

    private TimeController tCont;
    private JogadorController jCont;
    private List<Jogador> jogadores;
    private List<Time> times;


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
        tvSaidaJogador = view.findViewById(R.id.tvSaidaJogador);
        tvSaidaJogador.setMovementMethod(new ScrollingMovementMethod());
        etIDJogador = view.findViewById(R.id.etID_Jogador);
        etNomeJogador = view.findViewById(R.id.etNome_Jogador);
        etDataNasc = view.findViewById(R.id.etNascJogador);
        etPeso = view.findViewById(R.id.etPeso);
        etAltura = view.findViewById(R.id.etAltura);
        spTimes = view.findViewById(R.id.spTime);

        jCont = new JogadorController(new JogadorDao(view.getContext()));
        tCont = new TimeController(new TimeDao(view.getContext()));

        preenceSpinner();

        btnAtualizarJogador.setOnClickListener(op -> atualizarJogador());
        btnInserirJogador.setOnClickListener(op -> inserirJogador());
        btnExcluirJogador.setOnClickListener(op -> excluirJogador());
        btnListarJogador.setOnClickListener(op -> listarJogadores());
        btnBuscarJogador.setOnClickListener(op -> buscarJogador());
        return view;
    }

    private void buscarJogador() {
        tvSaidaJogador.setText("");
        limpaCampos();
        Jogador j = null;
        j = montaJogador();

        try {
            j = jCont.buscar(j);
            if (j != null && j.getNome() != null) {
                System.out.println(j);
                preencheCampos(j);
            } else {
                Toast.makeText(view.getContext(), "Jogador não encontrado", Toast.LENGTH_LONG).show();
                limpaCampos();
            }
        } catch (SQLException | ClassNotFoundException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }


    private void listarJogadores() {
        try {
            List<Jogador> listaJogadores = jCont.listar();
            StringBuffer buffer = new StringBuffer();

            for (Jogador j: listaJogadores) {
                buffer.append(j.toString() + "\n");
            }

            tvSaidaJogador.setText(buffer.toString());
        } catch (SQLException | ClassNotFoundException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void excluirJogador() {
        Jogador jogador =  montaJogador();
        try {
            jCont.deletar(jogador);
            Toast.makeText(view.getContext(), "Jogador EXCLUIDO com sucesso", Toast.LENGTH_LONG).show();
        } catch (SQLException | ClassNotFoundException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        limpaCampos();
    }

    private void inserirJogador() {
        int spPos = spTimes.getSelectedItemPosition();
        if (spPos > 0){
           Jogador jogador =  montaJogador();
            try {
                jCont.inserir(jogador);
                Toast.makeText(view.getContext(), "Jogador INSERIDO com sucesso", Toast.LENGTH_LONG).show();
            } catch (SQLException | ClassNotFoundException e) {
                Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
            limpaCampos();
        } else {
        Toast.makeText(view.getContext(), "Selecione um time antes de Incluir Jogador", Toast.LENGTH_LONG).show();
        }
    }

    private void atualizarJogador() {
        int spPos = spTimes.getSelectedItemPosition();
        if (spPos > 0){
            Jogador jogador =  montaJogador();
            try {
                jCont.atualizar(jogador);
                Toast.makeText(view.getContext(), "Jogador ATUALIZADA com sucesso", Toast.LENGTH_LONG).show();
            } catch (SQLException | ClassNotFoundException e) {
                Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
            limpaCampos();
        } else {
            Toast.makeText(view.getContext(), "Selecione um time antes de Incluir Jogador", Toast.LENGTH_LONG).show();
        }
    }

    private Jogador montaJogador(){
        Jogador j = new Jogador();
        j.setTime((Time) spTimes.getSelectedItem());
        j.setId(Integer.parseInt(etIDJogador.getText().toString()));
        j.setNome(etNomeJogador.getText().toString());
        j.setDataNasc(etDataNasc.getText().toString());
        String altura = etAltura.getText().toString();
        String peso = etPeso.getText().toString();

        if (!altura.isEmpty()) {
            j.setAltura(Double.parseDouble(altura));
        }
        if (!peso.isEmpty()) {
            j.setPeso(Double.parseDouble(peso));
        }

        return j;
    }

    private void preencheCampos(Jogador j){
      etIDJogador.setText(String.valueOf(j.getId()));
      etNomeJogador.setText(j.getNome());
      etDataNasc.setText(j.getDataNasc());
      etPeso.setText(String.valueOf(j.getPeso()));
      etAltura.setText(String.valueOf(j.getAltura()));

      int cont = 0;
      for (Time t : times){
          if (t.getCodigo() == j.getTime().getCodigo() ){
              spTimes.setSelection(cont);
          } else {
              cont++;
          }
      }

      if (cont > times.size()){
          spTimes.setSelection(0);
      }
    }

    private void limpaCampos(){
        etIDJogador.setText("");
        etNomeJogador.setText("");
        etAltura.setText("");
        etPeso.setText("");
        etDataNasc.setText("");

    }

    private void preenceSpinner(){
        Time t0 = new Time();
        t0.setCodigo(0);
        t0.setNome("Selecione o Time");
        t0.setNome("");

        try {
            times = tCont.listar();
            times.add(0, t0);

            ArrayAdapter ad = new ArrayAdapter(view.getContext(),
                    android.R.layout.simple_spinner_item,
                    times);

            ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spTimes.setAdapter(ad);

        } catch (SQLException | ClassNotFoundException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}