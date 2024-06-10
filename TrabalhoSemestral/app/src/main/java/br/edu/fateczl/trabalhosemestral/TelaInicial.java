package br.edu.fateczl.trabalhosemestral;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;

import br.edu.fateczl.trabalhosemestral.controller.ClienteController;
import br.edu.fateczl.trabalhosemestral.controller.ClientePremiumController;
import br.edu.fateczl.trabalhosemestral.controller.PagamentoCreditoController;
import br.edu.fateczl.trabalhosemestral.controller.PagamentoDebitoController;
import br.edu.fateczl.trabalhosemestral.model.Cliente;
import br.edu.fateczl.trabalhosemestral.model.ClientePadrao;
import br.edu.fateczl.trabalhosemestral.model.ClientePremium;
import br.edu.fateczl.trabalhosemestral.model.FormaPagamentoClube;
import br.edu.fateczl.trabalhosemestral.model.PagamentoCredito;
import br.edu.fateczl.trabalhosemestral.persistence.ClienteDao;
import br.edu.fateczl.trabalhosemestral.persistence.PremiumDao;

public class TelaInicial extends Fragment {


    private View view;
    private Button btnEncerraConta;
    private Button btnAtualizar;
    private Button btnInformacoes;
    private Button btnCancelarPlano;
    private Fragment fragment;

    private EditText nomeInicio;
    private EditText CPFInicio;
    private EditText emailInicio;
    private EditText senhaInicio;
    private EditText confSenhaInicio;
    private TextView tvFrete;
    private TextView tvStreaming;
    private TextView tvErro;
    private ClienteController cCont;
    private ClientePremiumController cpCont;

    private PagamentoCreditoController pcCont;
    private PagamentoDebitoController pdCont;
    private Cliente cliente;

    public TelaInicial() {
       super();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_tela_inicial, container, false);
        cCont = new ClienteController(new ClienteDao(view.getContext()));
        cpCont = new ClientePremiumController(new PremiumDao(view.getContext()));


        nomeInicio = view.findViewById(R.id.etNomeClienteInicio);
        emailInicio = view.findViewById(R.id.etEmailClienteInicio);
        senhaInicio = view.findViewById(R.id.etSenha1Inicio);
        confSenhaInicio = view.findViewById(R.id.etSenha2Inicio);
        tvFrete = view.findViewById(R.id.TvFrete);
        tvStreaming = view.findViewById(R.id.tvStreaming);
        tvErro = view.findViewById(R.id.tvErroInicio);
        btnEncerraConta = view.findViewById(R.id.btnEncerraConta);
        btnAtualizar = view.findViewById(R.id.btnAtualizar);
        btnInformacoes = view.findViewById(R.id.btnAssinatura);
        btnCancelarPlano = view.findViewById(R.id.btnCancelarAssinatura);

        Bundle bundle = getArguments();
        if (bundle != null) {
            cliente = (Cliente) bundle.getSerializable("cliente");
            preencheCampos(cliente);
            mostraInfosPlano(cliente);
        }
        btnAtualizar.setOnClickListener(op -> atualizarDados());
        btnInformacoes.setOnClickListener(op -> Upgrade());

        btnEncerraConta.setOnClickListener(op -> encerrarConta());
        btnCancelarPlano.setOnClickListener(op -> Downgrade(cliente));
        return view;
    }

    private void Downgrade(Cliente c) {
        try {
            ClientePremium cp = montaClientePremium(c);
            ClientePadrao cpa = montaClientePadrao(c);

            cpCont.deletar(cp);
            mostraInfosPlano(cpa);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    private void mostraInfosPlano(Cliente c) {
        ClientePremium cp = montaClientePremium(c);
        if (cp.getStreaming() == null){
            ClientePadrao cpp = montaClientePadrao(c);
            tvFrete.setText(cpp.condicaoFrete());
            tvStreaming.setText(cpp.streaming());
            btnCancelarPlano.setVisibility(View.GONE);
        } else {
            tvFrete.setText(cp.condicaoFrete());
            tvStreaming.setText(cp.streaming());
            btnInformacoes.setVisibility(View.GONE);
        }
    }

    private void Upgrade() {
        Bundle bundle = new Bundle();
        bundle.putSerializable("cliente", cliente);

        CadastroPagamento telaClienteFragment = new CadastroPagamento();
        telaClienteFragment.setArguments(bundle);

        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.Fragment, telaClienteFragment);
        fragmentTransaction.commit();

    }

    private void encerrarConta() {
        ClientePadrao cp = (montaClientePadrao(cliente));
        String senha = senhaInicio.getText().toString();
        String senhaBanco = cliente.getSenha();
        if (senha.equals(senhaBanco)){
            try {
                cCont.deletar(cp);
                voltarLogin();
            } catch (SQLException | ClassNotFoundException e) {
                Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
        } else {
            tvErro.setText("Senha atual está incorreta, ela é obrigatória para prosseguir");
        }
    }

    private void atualizarDados() {
        String senha = senhaInicio.getText().toString();
        String senhaBanco = cliente.getSenha();

        if (senha.equals(senhaBanco)){
            ClientePadrao cp = (montaClientePadrao(cliente));
            try {
                cCont.atualizar(cp);
                preencheCampos(cp);
            } catch (SQLException | ClassNotFoundException e) {
                Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
        } else {
            tvErro.setText("Senha atual está incorreta" + senhaBanco);
        }
    }

    private void preencheCampos(Cliente c){
        nomeInicio.setText(String.valueOf(c.getNome().toString()));
        emailInicio.setText(String.valueOf(c.getEmail().toString()));
        tvErro.setText("");
        senhaInicio.setText("");
        confSenhaInicio.setText("");
        mostraInfosPlano(c);
    }



    private ClientePadrao montaClientePadrao (Cliente c){
        ClientePadrao cp = new ClientePadrao();
        cp.setNome(nomeInicio.getText().toString());
        cp.setEmail(emailInicio.getText().toString());
        cp.setCPF(c.getCPF());
        String senha = senhaInicio.getText().toString();
        String senhaNova = confSenhaInicio.getText().toString();

        if ((senha != senhaNova) && (!senhaNova.isEmpty())){
            cp.setSenha(senhaNova);
        } else {
            cp.setSenha(senha);
        }

        return cp;
    }

    private ClientePremium montaClientePremium (Cliente c){
        ClientePremium cp = new ClientePremium();
        cp.setCPF(c.getCPF());
        try {
            ClientePremium clp = cpCont.buscar(cp);
            cp.setStreaming(clp.getStreaming());
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return cp;
    }

    private void voltarLogin() {
        fragment = new Login();
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.Fragment, fragment);
        fragmentTransaction.commit();
    }

}