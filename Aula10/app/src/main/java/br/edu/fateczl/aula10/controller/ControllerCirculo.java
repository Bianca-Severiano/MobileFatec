package br.edu.fateczl.aula10.controller;

import br.edu.fateczl.aula10.model.Circulo;

public class ControllerCirculo implements IGeometriaController<Circulo>{
    float area;
    float perimetro;
    final float pi = 3.14f;

    @Override
    public float calculoArea(Circulo c) {
        area = (float) (Math.pow(c.getRaio(), 2) * pi);
        return area;
    }

    @Override
    public float calculoPerimetro(Circulo c) {
        perimetro = ((2*pi) * c.getRaio());
        return perimetro;
    }
}
