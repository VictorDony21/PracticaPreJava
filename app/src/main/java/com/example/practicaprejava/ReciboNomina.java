package com.example.practicaprejava;

public class ReciboNomina {

    private static final double PAGO_BASE = 200.0;

    public static double calcularSubtotal(int horasNormales, int horasExtras, int puesto) {
        double incrementoPago = 0.0;

        if (puesto == 1) {
            incrementoPago = PAGO_BASE * 0.2;
        } else if (puesto == 2) {
            incrementoPago = PAGO_BASE * 0.5;
        } else if (puesto == 3) {
            incrementoPago = PAGO_BASE;
        }

        double pagoHorasNormales = PAGO_BASE + incrementoPago;
        double pagoHorasExtras = PAGO_BASE * 2.0;

        double subtotal = (pagoHorasNormales * horasNormales) + (pagoHorasExtras * horasExtras);

        return subtotal;
    }

    public static double calcularImpuesto(double subtotal) {
        double impuesto = subtotal * 0.16;
        return impuesto;
    }

    public static double calcularTotal(double subtotal, double impuesto) {
        double total = subtotal - impuesto;
        return total;
    }
}

