package com.mycompany.casa_steiger;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Window;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Ayudas comunes: fuentes, carga de imágenes (proyecto e internet), paneles y JDialog simple.
 */
public class Util {

    public static final Color ORO = new Color(230, 180, 90);
    public static final Color CREMA = new Color(240, 230, 210);
    public static final Color OSCURO = new Color(18, 12, 26);

    public static Font fuente(int estilo, int tam) {
        return new Font(Font.SERIF, estilo, tam);
    }

  
    /** Imagen incluida en el proyecto ruta desde la raíz de recursos*/
    public static ImageIcon recurso(String ruta) {
        URL url = Util.class.getResource(ruta);
        if (url == null) {
            throw new IllegalArgumentException("No se encuentra el recurso " + ruta);
        }
        return new ImageIcon(url);
    }

    /** Escala imagen */
    public static ImageIcon ajustar(ImageIcon icono, int ancho, int alto) {
        Image img = icono.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }

    /** Descarga una imagen de internet. Lanza IOException si falla */
    public static ImageIcon descargar(String direccion) throws IOException {
        URLConnection conexion = URI.create(direccion).toURL().openConnection();
        conexion.setConnectTimeout(4000);
        conexion.setReadTimeout(6000);
        conexion.setRequestProperty("User-Agent", "Mozilla/5.0 (CasaSteiger)");
        BufferedImage imagen = ImageIO.read(conexion.getInputStream());
        if (imagen == null) {
            throw new IOException("La URL no devuelve una imagen: " + direccion);
        }
        return new ImageIcon(imagen);
    }


    /** Texto centrado con fuente y color. */
    public static JLabel etiqueta(String texto, int tam, int estilo, Color color, int ancho, int alto) {
        JLabel l = new JLabel(texto, SwingConstants.CENTER);
        l.setFont(fuente(estilo, tam));
        l.setForeground(color);
        l.setPreferredSize(new Dimension(ancho, alto));
        return l;
    }

    /** Panel con FlowLayout de tamaño fijo. Si oscuro es true, tiene fondo opaco. */
    public static JPanel panel(int ancho, int alto, boolean oscuro) {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 6));
        p.setPreferredSize(new Dimension(ancho, alto));
        p.setOpaque(oscuro);
        if (oscuro) {
            p.setBackground(OSCURO);
        }
        return p;
    }

    public static void dialogo(Window padre, String titulo, String html, ImageIcon icono) {
        JDialog d = new JDialog(padre, titulo, Dialog.ModalityType.APPLICATION_MODAL);
        d.setLayout(new FlowLayout(FlowLayout.CENTER, 12, 10));

        if (icono != null) {
            d.add(new JLabel(icono));
        }
        JLabel texto = new JLabel("<html><div style='width:330px;text-align:center'>" + html + "</div></html>");
        texto.setFont(fuente(Font.PLAIN, 15));
        d.add(texto);

        JButton aceptar = new JButton("Aceptar");
        aceptar.addActionListener(e -> d.dispose());
        d.add(aceptar);

        d.pack();
        d.setResizable(false);
        d.setLocationRelativeTo(padre);
        d.setVisible(true); //modal el código espera aquí hasta que se cierra
    }
}
