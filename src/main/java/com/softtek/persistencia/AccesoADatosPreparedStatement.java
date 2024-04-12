package com.softtek.persistencia;

import com.softtek.modelo.DetallesOrdenes;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AccesoADatosPreparedStatement extends Conexion {






    public ArrayList<DetallesOrdenes> mostrarTodos() throws SQLException, ClassNotFoundException {
        PreparedStatement sentencia;
        ResultSet resultado;
        ArrayList<DetallesOrdenes> listaPedidos = new ArrayList<>();
        String sql = "SELECT * FROM order_details";
        abrirConexion();
        sentencia = miConexion.prepareStatement(sql);
        resultado = sentencia.executeQuery();

        while(resultado.next()){
            listaPedidos.add(new DetallesOrdenes(resultado.getInt("order_id"),
                    resultado.getInt("product_id"),
                    resultado.getDouble("unit_price"),
                    resultado.getInt("quantity"),
                    resultado.getDouble("discount")
            ));
        }

        miConexion.close();
        return listaPedidos;
    }

}
