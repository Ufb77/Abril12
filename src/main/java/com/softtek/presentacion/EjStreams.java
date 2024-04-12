package com.softtek.presentacion;

import com.softtek.modelo.DetallesOrdenes;
import com.softtek.persistencia.AccesoADatosPreparedStatement;

import java.sql.SQLException;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class EjStreams {
    public static void main(String[] args) {

        AccesoADatosPreparedStatement ap1 = new AccesoADatosPreparedStatement();

        try {
            obtenerDetallesMayor30(ap1);
            ordenarPrecioDescendente(ap1);
            obtenerPrecioMinimo(ap1);
            obtenerEstadisticas(ap1);
            obtenerCantidadProductos(ap1);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void obtenerDetallesMayor30(AccesoADatosPreparedStatement ap1) throws SQLException, ClassNotFoundException {
        Predicate<DetallesOrdenes> precioMayor30 = x-> x.getUnit_price()>30;

        ap1.mostrarTodos().stream()
                .filter(precioMayor30)
                .forEach(System.out::println);
    }

    public static void ordenarPrecioDescendente(AccesoADatosPreparedStatement ap1) throws SQLException, ClassNotFoundException {
        List<DetallesOrdenes> pedidosOrdenados = ap1.mostrarTodos().stream()
                .sorted(Comparator.comparing(DetallesOrdenes::getUnit_price).reversed())
                .collect(Collectors.toList());


        pedidosOrdenados.stream().forEach(System.out::println);
    }

    public static void obtenerPrecioMinimo(AccesoADatosPreparedStatement ap1) throws SQLException, ClassNotFoundException{

        List<DetallesOrdenes> ordenes = ap1.mostrarTodos(); 


        Optional<Double> precioMinimo = ordenes.stream()
                .map(DetallesOrdenes::getUnit_price)
                .min(Double::compareTo);


        List<DetallesOrdenes> ordenesPrecioMinimo = ordenes.stream()
                .filter(orden -> orden.getUnit_price() == precioMinimo.orElse(Double.MAX_VALUE))
                .collect(Collectors.toList());



        ordenesPrecioMinimo.forEach(orden -> System.out.println(orden));
    }

    public static void obtenerEstadisticas(AccesoADatosPreparedStatement ap1) throws SQLException, ClassNotFoundException {

        List<DetallesOrdenes> detallesOrdenes = ap1.mostrarTodos();


        DoubleSummaryStatistics estadisticas = detallesOrdenes.stream()
                .mapToDouble(DetallesOrdenes::getQuantity)
                .summaryStatistics();


        System.out.println("Cantidad total: " + estadisticas.getCount());
        System.out.println("Suma total: " + estadisticas.getSum());
        System.out.println("Media: " + estadisticas.getAverage());
        System.out.println("Mínimo: " + estadisticas.getMin());
        System.out.println("Máximo: " + estadisticas.getMax());
    }

    public static void obtenerCantidadProductos(AccesoADatosPreparedStatement ap1) throws SQLException, ClassNotFoundException {

        List<DetallesOrdenes> detallesOrdenes = ap1.mostrarTodos();


        Map<Integer, Integer> sumaCantidadesProducto = detallesOrdenes.stream()
                .collect(Collectors.groupingBy(DetallesOrdenes::getProduct_id, Collectors.summingInt(DetallesOrdenes::getQuantity)));


        System.out.println("Productos y la suma de sus cantidades:");
        sumaCantidadesProducto.forEach((producto, suma) -> System.out.println(producto + ": " + suma));
    }


    }



