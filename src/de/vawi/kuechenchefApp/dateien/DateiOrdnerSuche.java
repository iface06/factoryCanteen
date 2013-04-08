package de.vawi.kuechenchefApp.dateien;

import java.io.File;
import java.util.*;
import javax.swing.*;
import java.lang.*;

/**
 * Auf diese Klasse können sämtliche Importe zugreifen, damit sie wissen, wo die
 * Dateien liegen, die eingelesen werden sollen.
 *
 * @author Struebe
 * @version 26.01.2013
 */
public class DateiOrdnerSuche {

    /**
     * Diese Methode fragt den Anwender nach einem Pfad zu einem Ordner, in dem
     * die Dateien liegen, die eingelsen werden sollen.
     *
     * @return Gibt den Pfad wider, in dem die einzulesenenden Dateien liegen.
     */
    public String dateiOrdnerSuche() {

        ImageIcon icon = new ImageIcon("bild.jpg");
        JOptionPane.showMessageDialog(null, "Bitte Suchen Sie den Ordner, in dem Sie die Preislisten-Dateien, die Rezept-Datei und die Speisen-Datei abgelegt haben.", "KuechenChefApp", JOptionPane.INFORMATION_MESSAGE, icon);

        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal = fc.showOpenDialog(null);
        File ordner;
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            ordner = fc.getSelectedFile();
            return ordner.getAbsolutePath().toString();
        } else {
            return null;
        }

    }
}