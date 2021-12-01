package org.esei.moneygest;

public class utilidades {
    //CONSTANTES CAMPOS TABLA GASTO

    public static final String TABLA_GASTO="gasto";
    public static final String CAMPO_ID="id_gasto";
    public static final String CAMPO_CONCEPTO="concepto_gasto";
    public static final String CAMPO_FECHA="fecha_gasto";
    public static final String CAMPO_CANTIDAD="cantidad_gasto";
    public static final String CAMPO_LOGINAUT="login_autor";

    public static final String CREAR_TABLA_GASTO = "CREATE TABLE IF NOT EXISTS "+TABLA_GASTO+" (\n" +
            "  "+CAMPO_ID+" INT PRIMARY KEY AUTO_INCREMENT,\n" +
            "  "+CAMPO_CONCEPTO+" VARCHAR(100) NOT NULL,\n" +
            "  "+CAMPO_CANTIDAD+" DOUBLE(7,2) NOT NULL,\n" +
            "  "+CAMPO_FECHA+" DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,\n" +
            "  "+CAMPO_LOGINAUT+" VARCHAR(15) NOT NULL,\n" +
            "  FOREIGN KEY ("+CAMPO_LOGINAUT+") REFERENCES USUARIO(login_usuario) ON DELETE CASCADE\n" +
            ") \n";
}
