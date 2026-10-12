package com.mycompany.casa_steiger;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * Pantalla 2: el reportaje que encuentra el investigador y sus objetivos.
 * Recibe el nombre del investigador y pasa el estado inicial (sin pruebas)
 * a la pantalla de investigación.
 */
public class ExpedienteFrame extends JFrame {

    public ExpedienteFrame(String nombre) {
        setTitle("Expediente paranormal");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel fondo = new JLabel(Recursos.MENU);
        fondo.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 8));
        fondo.setPreferredSize(new Dimension(800, 600));
        setContentPane(fondo);

        // Cabecera
        JPanel cabecera = Util.panel(760, 72, true);
        cabecera.add(Util.etiqueta("Expediente paranormal", 26, Font.BOLD, Util.ORO, 740, 38));
        cabecera.add(Util.etiqueta("Investigador: " + nombre, 14, Font.ITALIC, Util.CREMA, 740, 20));
        add(cabecera);

        // Reportaje encontrado en la hemeroteca
        JPanel reportaje = Util.panel(760, 250, true);
        reportaje.add(Util.etiqueta("Lo que la casa se llevó", 22, Font.BOLD, Util.ORO, 740, 30));
        reportaje.add(Util.etiqueta("Por Elías Marrow · Diario del Valle, 2000", 13, Font.ITALIC,
                Util.CREMA, 740, 20));
        // JTextArea con ajuste de línea
        JTextArea cuerpo = new JTextArea("En el año 2000, la familia Steiger desapareció de su mansión a las "
                + "afueras del pueblo. La policía halló símbolos en las paredes, velas apagadas y un libro en un "
                + "idioma desconocido, pero no encontró cuerpos ni pistas claras. El caso quedó archivado.\n\n"
                + "Elías Marrow firmó este reportaje. Después de publicarlo, nunca volvió a escribir nada.");
        cuerpo.setFont(Util.fuente(Font.PLAIN, 15));
        cuerpo.setForeground(Util.CREMA);
        cuerpo.setBackground(Util.OSCURO);
        cuerpo.setLineWrap(true);
        cuerpo.setWrapStyleWord(true);
        cuerpo.setEditable(false);
        cuerpo.setFocusable(false);
        cuerpo.setPreferredSize(new Dimension(720, 150));
        reportaje.add(cuerpo);
        add(reportaje);

        // Objetivos
        JPanel objetivos = Util.panel(760, 150, true);
        objetivos.add(Util.etiqueta("Objetivos", 18, Font.BOLD, Util.ORO, 740, 26));
        objetivos.add(Util.etiqueta("1. Encontrar el diario de la madre.", 15, Font.PLAIN, Util.CREMA, 740, 24));
        objetivos.add(Util.etiqueta("2. Investigar el símbolo del sótano.", 15, Font.PLAIN, Util.CREMA, 740, 24));
        objetivos.add(Util.etiqueta("3. Descubrir por qué Elías Marrow desapareció.", 15, Font.PLAIN,
                Util.CREMA, 740, 24));
        add(objetivos);

        // Botones
        JPanel botones = Util.panel(760, 60, false);
        JButton comenzar = crearBoton("Comenzar investigación", 240);
        JButton volver = crearBoton("Volver al menú", 180);
        //Parámetros el estado inicial (sin pruebas) viaja a la siguiente pantalla
        comenzar.addActionListener(e -> {
            new InvestigacionFrame(nombre, new boolean[3]).setVisible(true);
            dispose();
        });
        volver.addActionListener(e -> {
            new MenuFrame().setVisible(true);
            dispose();
        });
        botones.add(comenzar);
        botones.add(volver);
        add(botones);

        pack();
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private JButton crearBoton(String texto, int ancho) {
        JButton b = new JButton(texto);
        b.setFont(Util.fuente(Font.BOLD, 15));
        b.setPreferredSize(new Dimension(ancho, 42));
        b.setFocusPainted(false);
        return b;
    }
}
