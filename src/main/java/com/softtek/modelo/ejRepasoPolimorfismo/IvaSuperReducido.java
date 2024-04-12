package com.softtek.modelo.ejRepasoPolimorfismo;

public class IvaSuperReducido implements IImpuesto{

    @Override
    public double calcularImpuesto(Producto p1) {
        return p1.getPrecio() * (1+ 0.04);
    }

}
