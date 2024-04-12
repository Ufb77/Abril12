package com.softtek.modelo.ejRepasoPolimorfismo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Factura {

    private IImpuesto impuesto;
    private List<Producto> productos;



    public double calcularTotalFactura(){
        double total = 0;


        for (Producto producto: this.productos
             ) {
            total += impuesto.calcularImpuesto(producto);

        }

        return total;
    }
}
