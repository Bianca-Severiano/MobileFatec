package br.edu.fateczl.aula07.model;

public class ContaBancária {
    private String cliente;
    private int num_conta;
    private float saldo;

    public String getCliente() {
        return cliente;
    }


    public ContaBancária(String cliente, int num_conta, float saldo) {
        this.cliente = cliente;
        this.num_conta = num_conta;
        if (saldo < 0){
            this.saldo = 0;
        } else {
            this.saldo = saldo;
        }
    }

    public String sacar (float valor){
        if (valor > this.saldo){
            return "Saque não realizado, valor indisponível";
        } else {
            this.saldo = this.saldo - valor;
            return "Saque realizado no valor de R$" +  "\n Saldo atual de R$" + this.saldo;
        }
    }

    public String depositar (float valor){
        if (valor <= 0){
            return "Valor informado para o saque é inválido, verifique e tente novamente";
        } else {
            this.saldo = this.saldo + valor;
            return "Depósito realizado no valor de R$" + valor;
        }
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public int getNum_conta() {
        return num_conta;
    }

    public void setNum_conta(int num_conta) {
        this.num_conta = num_conta;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        if (saldo < 0){
            this.saldo = 0;
        } else {
            this.saldo = saldo;
        }
    }

    @Override
    public String toString() {
        return
                "Cliente:" + cliente +
                ", Numero da conta:" + num_conta +
                ", Saldo R$" + saldo;
    }
}
