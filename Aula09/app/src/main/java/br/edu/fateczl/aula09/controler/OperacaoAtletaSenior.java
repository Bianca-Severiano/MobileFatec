package br.edu.fateczl.aula09.controler;

import br.edu.fateczl.aula09.model.AtletaSenior;

public class OperacaoAtletaSenior implements IOperacao<AtletaSenior>{
    @Override
    public void cadastrar(AtletaSenior atletaSenior) {
        AtletaSenior a = new AtletaSenior();
        a.setNome(atletaSenior.getNome());
        a.setBairro(atletaSenior.getBairro());
        a.setData_nasc(atletaSenior.getData_nasc());
        a.setProblemaCardiaco(atletaSenior.getProblemaCardiaco());
        listarAtleta(a);
    }

    @Override
    public String listarAtleta(AtletaSenior atletaSenior) {
        return atletaSenior.toString();
    }
}
