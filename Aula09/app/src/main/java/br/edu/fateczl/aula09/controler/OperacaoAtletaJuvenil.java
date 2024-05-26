package br.edu.fateczl.aula09.controler;

import br.edu.fateczl.aula09.model.Atleta;
import br.edu.fateczl.aula09.model.AtletaJuvenil;

public class OperacaoAtletaJuvenil implements IOperacao<AtletaJuvenil>{

     @Override
    public void cadastrar(AtletaJuvenil atletaJuvenil) {
         AtletaJuvenil a = new AtletaJuvenil();
         a.setNome(atletaJuvenil.getNome());
         a.setBairro(atletaJuvenil.getBairro());
         a.setData_nasc(atletaJuvenil.getData_nasc());
         a.setAnosPratica(atletaJuvenil.getAnosPratica());
        listarAtleta(a);
    }

    @Override
    public String listarAtleta(AtletaJuvenil atletaJuvenil) {
        return atletaJuvenil.toString();
    }
}
