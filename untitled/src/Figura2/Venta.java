package Figura2;

import enums.TipoDocumento;

import java.time.LocalDate;

public class Venta {
    private String idDocumento;
    private TipoDocumento tipo;
    private LocalDate fecha;

    public Venta(String idDocumento, TipoDocumento tipo, LocalDate fecha, Cliente cli) {
        this.idDocumento = idDocumento;
        this.tipo = tipo;
        this.fecha = fecha;
        this.cliente = cli;
    }

}
