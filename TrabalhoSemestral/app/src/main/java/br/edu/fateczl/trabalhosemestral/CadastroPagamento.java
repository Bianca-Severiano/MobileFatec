package br.edu.fateczl.trabalhosemestral;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import java.sql.SQLException;

import br.edu.fateczl.trabalhosemestral.controller.ClienteController;
import br.edu.fateczl.trabalhosemestral.controller.ClientePremiumController;
import br.edu.fateczl.trabalhosemestral.controller.PagamentoCreditoController;
import br.edu.fateczl.trabalhosemestral.controller.PagamentoDebitoController;
import br.edu.fateczl.trabalhosemestral.model.Cliente;
import br.edu.fateczl.trabalhosemestral.model.ClientePadrao;
import br.edu.fateczl.trabalhosemestral.model.ClientePremium;
import br.edu.fateczl.trabalhosemestral.model.PagamentoCredito;
import br.edu.fateczl.trabalhosemestral.model.PagamentoDebitoConta;
import br.edu.fateczl.trabalhosemestral.persistence.ClienteDao;
import br.edu.fateczl.trabalhosemestral.persistence.PagamentoCeditoDao;
import br.edu.fateczl.trabalhosemestral.persistence.PagamentoDebitoDao;
import br.edu.fateczl.trabalhosemestral.persistence.PremiumDao;


public class CadastroPagamento extends Fragment {
    private ClienteController cCont;

    private ClientePremiumController pCont;

    private PagamentoCreditoController pcCont;
    private PagamentoDebitoController pdCont;
    private Fragment fragment;
    private View view;
    private Cliente cliente;

    private EditText etValor1;
    private EditText etValor2;
    private EditText etString;

    private RadioButton credito;
    private RadioButton debito;

    private Spinner spStreaming;

   private Button voltarInicio;
   private Button salvar;

    public CadastroPagamento() {
        super();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_cadastro_pagamento, container, false);
        voltarInicio = view.findViewById(R.id.btnVoltarPagamento);
        salvar = view.findViewById(R.id.btnCadastrarPagamento);

        spStreaming = view.findViewById(R.id.spinner);
        debito = view.findViewById(R.id.rbDebitoConta);
        credito = view.findViewById(R.id.rbCredito);
        credito.setChecked(true);

        etValor1 = view.findViewById(R.id.etValor1);
        etValor2 = view.findViewById(R.id.etValor2);
        etString = view.findViewById(R.id.etString);

        Bundle bundle = getArguments();
        if (bundle != null) {
            cliente = (Cliente) bundle.getSerializable("cliente");
        }
        preenceSpinner();

        atualizarView();
        debito.setOnCheckedChangeListener((buttonView, isChecked) -> atualizarView());
        credito.setOnCheckedChangeListener((buttonView, isChecked) -> atualizarView());

        cCont = new ClienteController(new ClienteDao(view.getContext()));
        pCont = new ClientePremiumController(new PremiumDao(view.getContext()));
        pcCont = new PagamentoCreditoController(new PagamentoCeditoDao(view.getContext()));
        pdCont = new PagamentoDebitoController(new PagamentoDebitoDao(view.getContext()));

        voltarInicio.setOnClickListener(op -> carregarTelaClientePadrao(cliente) );
        salvar.setOnClickListener(op -> CadastrarPremium());
        return view;
    }

    private void CadastrarPremium() {

        //Credito
        if (credito.isChecked()){
            //Cliente
            ClientePremium cp = montaClientePremium(cliente);
            PagamentoCredito pc = montaPagamentoCredito(cp);
            cp.setPagamento(pc);
            try {
                pcCont.inserir(pc);
                pCont.inserir(cp);
                carregarTelaClientePadrao(cp);
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            ClientePremium cp2 = montaClientePremium(cliente);
            PagamentoDebitoConta pd = montaPagamentoDebito(cp2);
            cp2.setPagamento(pd);
            try {
                pdCont.inserir(pd);
                pCont.inserir(cp2);
                carregarTelaClientePadrao(cp2);
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }


    }

    private PagamentoCredito montaPagamentoCredito(ClientePremium cp) {
        PagamentoCredito pc = new PagamentoCredito();
        pc.setNomeTitular(cp.getCPF());
        pc.setTipo("1");
        pc.setNumeroCartao(Integer.parseInt(etValor1.getText().toString()));
        pc.setVencimento(etString.getText().toString());
        pc.setCvv(Integer.parseInt(etValor2.getText().toString()));
        return pc;
    }

    private PagamentoDebitoConta montaPagamentoDebito(ClientePremium cp) {
        PagamentoDebitoConta pc2 = new PagamentoDebitoConta();
        pc2.setNomeTitular(cp.getCPF());
        pc2.setTipo("2");
        pc2.setConta(Integer.parseInt(etValor1.getText().toString()));
        pc2.setBanco(etString.getText().toString());
        pc2.setAgencia(Integer.parseInt(etValor2.getText().toString()));
        return pc2;
    }

    private ClientePremium montaClientePremium (Cliente c){
        ClientePremium cp = new ClientePremium();
        cp.setNome(c.getNome());
        cp.setCPF(c.getCPF());
        cp.setEmail(c.getEmail());
        cp.setSenha(c.getSenha());
        cp.setStreaming(spStreaming.getSelectedItem().toString());
        return cp;
    }


    private void atualizarView() {
        if (credito.isChecked()){
            etValor1.setHint("Número do Cartão");
            etValor2.setHint("CVV");
            etString.setHint("Vencimento");
        } else {
            etValor1.setHint("Conta");
            etValor2.setHint("Agencia");
            etString.setHint("Banco");
        }
    }

    private void preenceSpinner(){
        String Array[] = {" Escolha um Streaming" ,"Disney+", "Globoplay"};
        ArrayAdapter ad = new ArrayAdapter(view.getContext(),
                android.R.layout.simple_spinner_item,
                Array);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spStreaming.setAdapter(ad);
    }



    private void carregarTelaClientePadrao(Cliente cliente) {
        // Criar um Bundle para passar os dados do cliente para a próxima tela
        Bundle bundle = new Bundle();
        bundle.putSerializable("cliente", cliente);

        // Iniciar a nova tela que mostra as informações do cliente
        TelaInicial telaClienteFragment = new TelaInicial();
        telaClienteFragment.setArguments(bundle);


        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.Fragment, telaClienteFragment);
        fragmentTransaction.commit();
    }

}