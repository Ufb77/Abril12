package com.softtek.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetallesOrdenes {
    private int order_id;
    private int product_id;
    private double unit_price;
    private int quantity;
    private double discount;
}
