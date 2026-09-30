# 🕯️ Lumen

> *Nel buio di Ombrafonda, l'unica luce è la tua.*

**Lumen** è un gioco di ruolo a turni sviluppato in **Java** con interfaccia grafica **JavaFX**

---

## 📜 La storia

Da giorni nessuna luce brilla più sulle torri di **Roccafiorita**. **Morvath, il Signore delle Ombre**, ha rapito la principessa **Elyra** e l'ha trascinata nelle profondità di **Ombrafonda**, dove il buio divora chi osa entrare.

Il re ha scelto te, coraggioso avventuriero, armato solo della tua forza e di una piccola lanterna. Affronta le creature che si nascondono nell'oscurità, diventa più forte a ogni vittoria e trova la porta che conduce al cuore del dungeon.

---

## 🎮 Il gioco in breve

- Scegli il nome e il tipo del tuo avventuriero (**Barbaro** o **Mago**; il **Tank** è in arrivo)
- Esplora il dungeon muovendoti casella per casella: la lanterna illumina solo ciò che ti circonda
- Affronta i mostri (**Bandito**, **Zombie**, **Scheletro**) in combattimenti a turni, scegliendo tra attacco base e abilità speciale
- Ogni vittoria ti rende più forte
- Ogni avventura, completata, persa o abbandonata, viene registrata nel **Diario delle avventure**

---
## Esecuzione

Requisiti: **Git** e **Java 25** (se Java 25 non è presente, Gradle lo scarica automaticamente).

```bash
git clone https://github.com/Pand1z3nz3r0/RPG129024.git
cd RPG129024
./gradlew build
./gradlew run
```

> Su Windows usare `gradlew.bat build` e `gradlew.bat run` al posto di `./gradlew`.
---

## Documentazione

La documentazione completa del progetto (funzionalità, responsabilità, classi, persistenza, estendibilità) è nella **[Wiki](../../wiki)**.

## Uso di strumenti di AI

Nella realizzazione del progetto è stato utilizzato **Claude** (Anthropic) come supporto per la configurazione dell'ambiente (Gradle, JavaFX, Git), per la discussione delle scelte di progettazione e per la generazione degli sprite dei personaggi.
La dichiarazione dettagliata è nella pagina [Dichiarazione uso AI](../../wiki/Dichiarazione-uso-AI) della Wiki.