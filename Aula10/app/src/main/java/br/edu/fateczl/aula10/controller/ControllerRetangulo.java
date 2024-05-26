package br.edu.fateczl.aula10.controller;

import br.edu.fateczl.aula10.model.Retangulo;

public class ControllerRetangulo implements IGeometriaController <Retangulo> {

    float area;
    float perimetro;

    @Override
    public float calculoArea(Retangulo r) {
        area = (r.getBase()) * (r.getAltura());
        return area;
    }

    @Override
    public float calculoPerimetro(Retangulo r) {
        perimetro = ((2*r.getAltura()) + (2*r.getBase()));
        return perimetro;
    }
}
