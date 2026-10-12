package com.mycompany.casa_steiger;

import javax.swing.ImageIcon;

/**
 * Imágenes del proyecto (src/main/resources/imagenes) cargadas una sola vez,
 * y la dirección de la imagen que se descarga de internet.
 */
public class Recursos {

    // Fondos
    public static final ImageIcon MENU = Util.recurso("/imagenes/menu_mansion.jpg");
    public static final ImageIcon DORMITORIO = Util.recurso("/imagenes/dormitorio.jpg");
    public static final ImageIcon BIBLIOTECA = Util.recurso("/imagenes/biblioteca.jpg");
    public static final ImageIcon SOTANO = Util.recurso("/imagenes/sotano.jpg");

    // Pruebas e iconos
    public static final ImageIcon DIARIO = Util.recurso("/imagenes/diario.png");
    public static final ImageIcon LIBRO = Util.recurso("/imagenes/libro.png");
    public static final ImageIcon SIMBOLO = Util.recurso("/imagenes/simbolo.png");
    public static final ImageIcon INTERROGANTE = Util.recurso("/imagenes/interrogante.png");

    /**
     * Imagen del sótano descargada de internet. Si no hay conexión, se usa sotano.jpg.
     */
    public static final String URL_SOTANO = "https://picsum.photos/seed/casa-hollow-sotano/800/600";

    private Recursos() {
    }
}
