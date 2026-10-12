package com.mycompany.casa_steiger;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Pantalla 1: menú principal. Pide el nombre del investigador y pasa
 * ese dato a la pantalla del expediente.
 */
public class MenuFrame extends JFrame {

    private final JTextField txtNombre = new JTextField(16);

    public MenuFrame() {
        setTitle("El Ritual de la Casa Hollow");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Fondo la mansión Con FlowLayout.
        JLabel fondo = new JLabel(Recursos.MENU);
        fondo.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 8));
        fondo.setPreferredSize(new Dimension(800, 600));
        setContentPane(fondo);

        // Título
        JPanel titulo = Util.panel(760, 120, true);
        titulo.add(Util.etiqueta("El Ritual de la Casa Hollow", 34, Font.BOLD, Util.ORO, 740, 52));
        titulo.add(Util.etiqueta("Veintiocho años después, la casa vuelve a llamar.", 15, Font.ITALIC,
                Util.CREMA, 740, 24));
        add(titulo);

        // Espacio para que se vea la mansión
        add(Util.panel(760, 230, false));

        // Nombre del investigador
        JPanel nombre = Util.panel(760, 44, true);
        nombre.add(Util.etiqueta("Nombre del investigador:", 16, Font.BOLD, Util.ORO, 250, 28));
        txtNombre.setFont(Util.fuente(Font.PLAIN, 16));
        nombre.add(txtNombre);
        add(nombre);

        // Botones
        JPanel botones = Util.panel(760, 60, false);
        JButton btnNueva = crearBoton("Nueva investigación", 230);
        JButton btnComoJugar = crearBoton("Cómo jugar", 150);
        JButton btnSalir = crearBoton("Salir", 150);
        btnNueva.addActionListener(e -> empezar());
        btnComoJugar.addActionListener(e -> Util.dialogo(this, "Cómo jugar",
                "Lee el reportaje del periodista y reúne las <b>tres pruebas</b> de la casa: "
                + "el diario, el libro y el símbolo.<br><br>Con las tres pruebas, el final bueno es posible.",
                null));
        btnSalir.addActionListener(e -> System.exit(0));
        botones.add(btnNueva);
        botones.add(btnComoJugar);
        botones.add(btnSalir);
        add(botones);

        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        getRootPane().setDefaultButton(btnNueva);
    }

    private JButton crearBoton(String texto, int ancho) {
        JButton b = new JButton(texto);
        b.setFont(Util.fuente(Font.BOLD, 16));
        b.setPreferredSize(new Dimension(ancho, 42));
        b.setFocusPainted(false);
        return b;
    }

    /**Parámetros: el nombre viaja al expediente. */
    private void empezar() {
        String nombre = txtNombre.getText().trim();
        if (nombre.isEmpty()) {
            nombre = "Investigador";
        }
        new ExpedienteFrame(nombre).setVisible(true);
        dispose();
    }
}
