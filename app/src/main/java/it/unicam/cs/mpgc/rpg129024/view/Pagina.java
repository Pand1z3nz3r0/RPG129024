package it.unicam.cs.mpgc.rpg129024.view;

import javafx.scene.Parent;

/**
 * Una schermata del gioco.
 *
 * @author Davide Caliri
 */
public interface Pagina {

    /**
     * @return il contenuto da mostrare nella finestra
     */
    Parent getRadice();

    /**
     * Chiamato dal {@link Navigatore} subito dopo che la pagina è stata
     * mostrata nella finestra.
     * <p>
     * Le pagine che devono fare qualcosa quando diventano visibili, ad esempio
     * prendere il focus per ricevere i tasti premuti, ridefiniscono questo
     * metodo. Di base non fa nulla.
     */
    default void mostrata() {
        // di base non serve fare nulla
    }
}