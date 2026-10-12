package com.mycompany.casa_steiger;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Pantalla 4: el ritual final. La decisión y las pruebas reunidas determinan el desenlace.
 */
public class FinalFrame extends JFrame {

    private final String nombre;
    private final boolean[] pruebas;

    public FinalFrame(String nombre, boolean[] pruebas) {
        this.nombre = nombre;
        this.pruebas = pruebas;

        setTitle("El ritual final");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel fondo = new JLabel(Recursos.SOTANO);
        fondo.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 8));
        fondo.setPreferredSize(new Dimension(800, 600));
        setContentPane(fondo);

        int total = 0;
        for (boolean p : pruebas) {
            if (p) {
                total++;
            }
        }

        JPanel cabecera = Util.panel(760, 120, true);
        cabecera.add(Util.etiqueta("El ritual final", 30, Font.BOLD, Util.ORO, 740, 44));
        JLabel texto = new JLabel("<html><center>Estás frente al portal, en el centro del pentagrama.<br>"
                + "Tienes <b>" + total + " de 3</b> pruebas. ¿Qué haces, " + nombre + "?</center></html>",
                javax.swing.SwingConstants.CENTER);
        texto.setFont(Util.fuente(Font.PLAIN, 16));
        texto.setForeground(Util.CREMA);
        texto.setPreferredSize(new Dimension(740, 52));
        cabecera.add(texto);
        add(cabecera);

        add(Util.panel(760, 300, false));

        JPanel botones = Util.panel(760, 70, false);
        JButton cerrar = crearBoton("Cerrar el portal");
        JButton completar = crearBoton("Completar el ritual");
        JButton escapar = crearBoton("Intentar escapar");
        cerrar.addActionListener(e -> finalizar("cerrar"));
        completar.addActionListener(e -> finalizar("completar"));
        escapar.addActionListener(e -> finalizar("escapar"));
        botones.add(cerrar);
        botones.add(completar);
        botones.add(escapar);
        add(botones);

        pack();
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private JButton crearBoton(String texto) {
        JButton b = new JButton(texto);
        b.setFont(Util.fuente(Font.BOLD, 15));
        b.setPreferredSize(new Dimension(220, 44));
        b.setFocusPainted(false);
        return b;
    }

    /** Muestra el desenlace en un JDialog y vuelve al menú. */
    private void finalizar(String decision) {
        boolean completas = pruebas[0] && pruebas[1] && pruebas[2];
        String titulo;
        String texto;
        ImageIcon icono;

        switch (decision) {
            case "cerrar":
                if (completas) {
                    titulo = "Final bueno: el portal sellado";
                    texto = "Con las tres pruebas, cierras el portal siguiendo el libro. La entidad se desvanece "
                            + "y la casa queda en silencio. Elías Marrow recupera su memoria y, por fin, puede "
                            + "contar la verdad.";
                    icono = Recursos.LIBRO;
                } else {
                    titulo = "Final malo: un cierre a medias";
                    texto = "Cierras el portal sin conocer toda la verdad. La entidad se debilita, pero no "
                            + "desaparece. Cuando vuelva, la casa lo recordará.";
                    icono = Recursos.SIMBOLO;
                }
                break;
            case "completar":
                titulo = "Final malo: la invocación";
                texto = "Intentas completar el ritual sin conocer la verdad. La entidad atraviesa el portal "
                        + "y la investigación termina en desastre.";
                icono = Recursos.SIMBOLO;
                break;
            default:
                titulo = "Final alternativo: la huida";
                texto = "Decides escapar antes de terminar. Sobrevives, pero el misterio de la familia Hollow "
                        + "y de Elías Marrow queda sin resolver.";
                icono = Recursos.DIARIO;
                break;
        }

        Util.dialogo(this, titulo, texto, Util.ajustar(icono, 128, 128));
        new MenuFrame().setVisible(true);
        dispose();
    }
}
