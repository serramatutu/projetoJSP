package database.dbo;

import java.util.UUID;

/**
 *
 * @author u16187
 */
public class Setor {
    private UUID id;
    private UUID localEvento;
    private String nome;
    
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getLocalEvento() {
        return localEvento;
    }

    public void setLocalEvento(UUID localEvento) {
        this.localEvento = localEvento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
