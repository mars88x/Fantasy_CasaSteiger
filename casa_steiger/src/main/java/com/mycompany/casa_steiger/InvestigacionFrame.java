package com.mycompany.casa_steiger;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;

/**
 * Pantalla 3: el investigador visita tres lugares y reúne las pruebas.
 * Recibe el nombre y el estado de las pruebas, y los pasa al ritual final.
 */
public class InvestigacionFrame extends JFrame {

    private static final String[] LUGARES = {"Dormitorio", "Biblioteca", "Sótano"};
    // Cada línea va con <br> para que quepa en la tarjeta (220 px)
    private static final String[] DESCRIPCIONES = {
        "La habitación de la madre.<br>Quizá guarde su diario.",
        "Estanterías cubiertas de polvo.<br>Un libro ritual espera<br>entre las velas.",
        "Bajo la casa, un símbolo<br>dibujado en el suelo."
    };
    private static final String[] PISTAS = {"Diario de la madre", "Libro ritual", "Símbolo de invocación"};
    private static final String[] TEXTOS_PISTA = {
        "En sus páginas, la madre escribió que buscaba contactar con una entidad. "
            + "Elías Marrow asistió al ritual como periodista infiltrado.",
        "El libro explica cómo cerrar el portal. También cuenta que Elías intentó detenerlo, y fracasó.",
        "El símbolo identifica a la entidad. Al publicar el reportaje, Elías intentaba advertir "
            + "a alguien antes de que volviera a intentarlo."
    };
    private static final ImageIcon[] ICONOS_PRUEBA = {Recursos.DIARIO, Recursos.LIBRO, Recursos.SIMBOLO};

    private final String nombre;
    private final boolean[] pruebas;
    private final JLabel[] imagenes = new JLabel[3];
    private final JLabel[] iconos = new JLabel[3];
    private final JButton[] botones = new JButton[3];
    private final JLabel contador = Util.etiqueta("", 16, Font.BOLD, Util.ORO, 740, 26);

    public InvestigacionFrame(String nombre, boolean[] pruebas) {
        this.nombre = nombre;
        this.pruebas = pruebas;

        setTitle("Investigación de la mansión");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel fondo = new JLabel(Recursos.BIBLIOTECA);
        fondo.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 8));
        fondo.setPreferredSize(new Dimension(800, 600));
        setContentPane(fondo);

        // Título
        add(Util.etiqueta("Investigación de la mansión", 26, Font.BOLD, Util.ORO, 760, 44));

        // Contador de pruebas
        JPanel fila = Util.panel(760, 30, false);
        fila.add(contador);
        add(fila);
        actualizarContador();

        // Tres tarjetas, una por lugar
        JPanel tarjetas = Util.panel(760, 380, false);
        for (int i = 0; i < 3; i++) {
            tarjetas.add(crearTarjeta(i));
        }
        add(tarjetas);

        // Ir al ritual final
        JPanel pie = Util.panel(760, 56, false);
        JButton ritual = new JButton("Ir al ritual final");
        ritual.setFont(Util.fuente(Font.BOLD, 16));
        ritual.setPreferredSize(new Dimension(230, 42));
        ritual.setFocusPainted(false);
        // Paso de parámetros: nombre y pruebas viajan al ritual final
        ritual.addActionListener(e -> {
            new FinalFrame(nombre, pruebas).setVisible(true);
            dispose();
        });
        pie.add(ritual);
        add(pie);

        cargarFotoDeInternet();

        pack();
        setResizable(false);
        setLocationRelativeTo(null);
    }

    /** Tarjeta de un lugar: foto, nombre, descripción, icono de prueba y botón. */
    private JPanel crearTarjeta(int i) {
        JPanel tarjeta = Util.panel(240, 370, true);

        ImageIcon foto = i == 0 ? Recursos.DORMITORIO : i == 1 ? Recursos.BIBLIOTECA : Recursos.SOTANO;
        imagenes[i] = new JLabel(Util.ajustar(foto, 220, 130));
        imagenes[i].setPreferredSize(new Dimension(220, 130));

        JLabel nombreLugar = Util.etiqueta(LUGARES[i], 18, Font.BOLD, Util.ORO, 220, 26);

        JLabel descripcion = new JLabel("<html><center>" + DESCRIPCIONES[i] + "</center></html>",
                SwingConstants.CENTER);
        descripcion.setFont(Util.fuente(Font.PLAIN, 13));
        descripcion.setForeground(Util.CREMA);
        descripcion.setPreferredSize(new Dimension(220, 60));

        iconos[i] = new JLabel("", SwingConstants.CENTER);
        iconos[i].setPreferredSize(new Dimension(220, 64));

        botones[i] = new JButton("Investigar");
        botones[i].setPreferredSize(new Dimension(160, 34));
        botones[i].setFocusPainted(false);
        final int indice = i;
        botones[i].addActionListener(e -> investigar(indice));

        tarjeta.add(imagenes[i]);
        tarjeta.add(nombreLugar);
        tarjeta.add(descripcion);
        tarjeta.add(iconos[i]);
        tarjeta.add(botones[i]);
        actualizarTarjeta(i);
        return tarjeta;
    }

    private void investigar(int i) {
        if (pruebas[i]) {
            Util.dialogo(this, "Ya revisado", "Ya examinaste " + LUGARES[i].toLowerCase() + ".", null);
            return;
        }
        pruebas[i] = true;
        actualizarTarjeta(i);
        actualizarContador();
        Util.dialogo(this, "Pista: " + PISTAS[i], TEXTOS_PISTA[i], Util.ajustar(ICONOS_PRUEBA[i], 96, 96));
    }

    private void actualizarTarjeta(int i) {
        if (pruebas[i]) {
            iconos[i].setIcon(Util.ajustar(ICONOS_PRUEBA[i], 64, 64));
            botones[i].setText("Revisado");
            botones[i].setEnabled(false);
        } else {
            iconos[i].setIcon(Util.ajustar(Recursos.INTERROGANTE, 64, 64));
            botones[i].setText("Investigar");
            botones[i].setEnabled(true);
        }
    }

    private void actualizarContador() {
        int total = 0;
        for (boolean p : pruebas) {
            if (p) {
                total++;
            }
        }
        contador.setText("Pruebas encontradas: " + total + " / 3");
    }

    /**
     * La imagen del sótano
     * para no bloquear la ventana. Si falla, se queda la imagen de reserva del proyecto.
     */
    private void cargarFotoDeInternet() {
        new SwingWorker<ImageIcon, Void>() {
            @Override
            protected ImageIcon doInBackground() throws Exception {
                return Util.descargar(Recursos.URL_SOTANO);
            }

            @Override
            protected void done() {
                try {
                    imagenes[2].setIcon(Util.ajustar(get(), 220, 130));
                } catch (Exception ex) {
                    // Sin conexión se mantiene sotano.jpg
                }
            }
        }.execute();
    }
}
