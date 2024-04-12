package com.softtek.presentacion.mainRepasoPolimorfismo;

import com.softtek.modelo.ejRepasoPolimorfismo.Factura;
import com.softtek.modelo.ejRepasoPolimorfismo.IvaGeneral;
import com.softtek.modelo.ejRepasoPolimorfismo.IvaSuperReducido;
import com.softtek.modelo.ejRepasoPolimorfismo.Producto;

import java.util.ArrayList;
import java.util.List;

public class EjPolimorfismo {
    public static void main(String[] args) {
        List<Producto> lista = new ArrayList<>();
        lista.add(new Producto("Leche", 2));
        lista.add(new Producto("Aceite", 10));
        lista.add(new Producto("Pan", 1));
        lista.add(new Producto("Jamón", 5));
        lista.add(new Producto("Caviar", 50));
        IvaGeneral iva21 = new IvaGeneral();
        IvaSuperReducido iva4 = new IvaSuperReducido();
        Factura facturaReducida = new Factura(iva4, lista);
        Factura facturaNormal = new Factura(iva21, lista);

        System.out.println("Factura iva reducido:\n" + facturaReducida.calcularTotalFactura());
        System.out.println("Factura iva normal:\n" + facturaNormal.calcularTotalFactura());





    }
}
