package database.dbo;

import java.util.UUID;

/**
 *
 * @author u16187
 */
public class LocalEvento {
    private UUID id;
    private String nome;
    private String CEP;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCEP() {
        return CEP;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }
}
