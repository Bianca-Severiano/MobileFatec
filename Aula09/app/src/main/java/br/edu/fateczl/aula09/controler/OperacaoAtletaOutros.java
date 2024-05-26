package br.edu.fateczl.aula09.controler;

import br.edu.fateczl.aula09.model.AtletaOutros;
import br.edu.fateczl.aula09.model.AtletaSenior;

public class OperacaoAtletaOutros implements IOperacao<AtletaOutros>{
    @Override
    public void cadastrar(AtletaOutros atletaOutros) {
        AtletaOutros a = new AtletaOutros();
        a.setNome(atletaOutros.getNome());
        a.setBairro(atletaOutros.getBairro());
        a.setData_nasc(atletaOutros.getData_nasc());
        a.setAcademia(atletaOutros.getAcademia());
        a.setRecordeSegundos(atletaOutros.getRecordeSegundos());
        listarAtleta(a);
    }

    @Override
    public String listarAtleta(AtletaOutros atletaOutros) {
        return atletaOutros.toString();
    }
}
