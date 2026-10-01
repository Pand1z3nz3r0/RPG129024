package it.unicam.cs.mpgc.rpg129024.Model.Abilita;

import it.unicam.cs.mpgc.rpg129024.Model.Avventuriero.Avventuriero;
import it.unicam.cs.mpgc.rpg129024.Model.Avventuriero.Personaggio;

public interface Abilita {
        public String getNome();

        public String getDescrizione();

        public int getRicarica();


        /**
         * Applica l'effetto dell'abilità.
         *
         * @param utilizzatore l'avventuriero che usa l'abilità
         * @param bersaglio    il personaggio che la subisce
         * @return i danni inflitti (0 se l'abilità non fa danni)
         */
        int usa(Avventuriero utilizzatore, Personaggio bersaglio);
}
