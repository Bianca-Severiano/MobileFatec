package br.edu.fateczl.aula07.model;

public class ContaPoupanca extends ContaBancária {

    private int diaDeRendimento;

    public ContaPoupanca(String cliente, int num_conta, float saldo, int diaRendimento) {
        super(cliente, num_conta, saldo);
        this.diaDeRendimento = diaRendimento;
    }

    public void calcularNovoSaldo(float taxaRendimento){
        float saldoAtual = getSaldo();
        float novoSaldo = saldoAtual + (saldoAtual * (taxaRendimento/100));
        if (novoSaldo <= 0){
            setSaldo(0);
        } else {
            setSaldo(novoSaldo);
        }
    }

}
