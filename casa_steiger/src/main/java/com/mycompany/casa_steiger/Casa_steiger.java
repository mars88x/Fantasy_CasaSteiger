package com.mycompany.casa_steiger;

import javax.swing.SwingUtilities;

/**
abre el menú principal.
 */
public class Casa_steiger {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MenuFrame().setVisible(true));
    }
}
