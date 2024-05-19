package br.edu.fateczl.aula07.model;

public class ContaEspecial extends ContaBancária {

    private float limite;

    public float getLimite() {
        return limite;
    }

    public void setLimite(float limite){
        this.limite = limite;
    }

    public ContaEspecial(String cliente, int num_conta, float saldo, float limite) {
        super(cliente, num_conta, saldo);
        if (limite < 0) {
            this.limite = 0;
        } else {
            this.limite = limite;
        }
    }

    @Override
    public String sacar(float valor) {
            float saldoAtual = getSaldo();
            if (valor > saldoAtual) {
                if (valor-saldoAtual > this.limite) {
                    return "Saque não realizado, valor indisponível";
                } else {
                    saldoAtual = valor - saldoAtual;
                    limite = limite - (saldoAtual);
                    setSaldo(0);
                    setLimite(limite);
                    return "Saque realizado com Limite Especial no valor de R$" + valor;
                }
            } else {
                saldoAtual = saldoAtual - valor;
                setSaldo(saldoAtual);
                return "Saque realizado no valor de R$" + valor + "\n Saldo atual de R$" + saldoAtual;
            }
        }

    @Override
    public String toString() {
        return super.toString() + "  " + getLimite();
    }
}

