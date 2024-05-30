package br.edu.fateczl.aula11_p1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.List;

import br.edu.fateczl.aula11_p1.controller.TimeController;
import br.edu.fateczl.aula11_p1.model.Time;
import br.edu.fateczl.aula11_p1.persistence.TimeDao;

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



    private TimeController tCont;
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
        tvSaidaTime.setMovementMethod(new ScrollingMovementMethod());
        etCodTime = view.findViewById(R.id.etCodigo);
        etNomeTime = view.findViewById(R.id.etNome_Time);
        etCidadeTime = view.findViewById(R.id.etCidade_Time);

        tCont = new TimeController(new TimeDao(view.getContext()));

        btnAtualizarTime.setOnClickListener(op -> atualizarTime());
        btnInserirTime.setOnClickListener(op -> inserirTime());
        btnExcluirTime.setOnClickListener(op -> excluirTime());
        btnListarTime.setOnClickListener(op -> listarTime());
        btnBuscarTime.setOnClickListener(op -> buscarTime());

        return view;
    }

    private void atualizarTime() {
        tvSaidaTime.setText("");
        Time t = montaTime();
        try {
            tCont.atualizar(t);
            Toast.makeText(view.getContext(), "Time ATUALIZADO com Sucesso", Toast.LENGTH_LONG).show();
        } catch (SQLException | ClassNotFoundException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        limpaCampos();
    }

    private void inserirTime() {
        tvSaidaTime.setText("");
        Time t = montaTime();
        try {
            tCont.inserir(t);
            Toast.makeText(view.getContext(), "Time INSERIDO com Sucesso", Toast.LENGTH_LONG).show();
        } catch (SQLException | ClassNotFoundException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        limpaCampos();
    }

    private void excluirTime() {
        tvSaidaTime.setText("");
        Time t = montaTime();
        try {
            tCont.deletar(t);
            Toast.makeText(view.getContext(), "Time EXCLUID com Sucesso", Toast.LENGTH_LONG).show();
        } catch (SQLException | ClassNotFoundException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        limpaCampos();
    }

    private void listarTime() {

        try {
            List<Time> listaTimes = tCont.listar();
            StringBuffer buffer = new StringBuffer();

            for (Time t: listaTimes) {
                buffer.append(t.toString() + "\n");
            }
            tvSaidaTime.setText(buffer.toString());
        } catch (SQLException | ClassNotFoundException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void buscarTime() {
        tvSaidaTime.setText("");
        Time t = montaTime();
        try {
            t = tCont.buscar(t);
            if (t.getNome() != null){
                preencheCampos(t);
            } else {
                Toast.makeText(view.getContext(), "Time não encontrado", Toast.LENGTH_LONG).show();
                limpaCampos();
            }
        } catch (SQLException | ClassNotFoundException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private Time montaTime(){
        Time t = new Time();
        t.setCodigo(Integer.parseInt(etCodTime.getText().toString()));
        t.setNome(etNomeTime.getText().toString());
        t.setCidade(etCidadeTime.getText().toString());
        return t;
    }

    private void preencheCampos(Time t){
        etCodTime.setText(String.valueOf(t.getCodigo()));
        etNomeTime.setText(t.getNome());
        etCidadeTime.setText(t.getCidade());
    }

    private void limpaCampos(){
        etCidadeTime.setText("");
        etCodTime.setText("");
        etNomeTime.setText("");

    }
}