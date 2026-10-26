package it.unicam.cs.mpgc.rpg129024.persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Implementazione di {@link DiarioRepository} che salva il diario in un file JSON.
 * <p>
 * Per impostazione predefinita il file si trova in
 * {@code <cartella utente>/.lumen/diario.json}, così funziona su qualunque
 * computer indipendentemente da dove viene avviato il gioco.
 *
 * @author Davide Caliri
 */
public class JsonDiarioRepository implements DiarioRepository {

    private static final Path FILE_PREDEFINITO =
            Path.of(System.getProperty("user.home"), ".lumen", "diario.json");

    private final Path file;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Crea un repository che usa il file predefinito.
     */
    public JsonDiarioRepository() {
        this(FILE_PREDEFINITO);
    }

    /**
     * Crea un repository che usa il file indicato.
     *
     * @param file il percorso del file JSON
     */
    public JsonDiarioRepository(Path file) {
        this.file = Objects.requireNonNull(file, "Il file non può essere null");
    }

    @Override
    public void salva(VoceDiario voce) {
        Objects.requireNonNull(voce, "La voce non può essere null");
        List<VoceDiario> voci = new ArrayList<>(caricaTutte());
        voci.add(voce);
        try {
            Files.createDirectories(file.getParent());
            try (Writer writer = Files.newBufferedWriter(file, StandardCharsets.UTF_8)) {
                gson.toJson(voci.toArray(new VoceDiario[0]), writer);
            }
        } catch (IOException e) {
            throw new PersistenzaException("Impossibile salvare il diario", e);
        }
    }

    @Override
    public List<VoceDiario> caricaTutte() {
        if (!Files.exists(file)) {
            return List.of();
        }
        try (Reader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
            VoceDiario[] voci = gson.fromJson(reader, VoceDiario[].class);
            return voci == null ? List.of() : List.of(voci);
        } catch (IOException | JsonParseException e) {
            throw new PersistenzaException("Impossibile leggere il diario", e);
        }
    }
}