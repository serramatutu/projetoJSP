package database.dbo;

import java.util.UUID;

/**
 *
 * @author u16187
 */
public class TipoIngresso {
    private UUID id;
    private UUID espetaculo;
    private String nome;
    private float preco;
    private int quantidade;
    
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getEspetaculo() {
        return espetaculo;
    }

    public void setEspetaculo(UUID espetaculo) {
        this.espetaculo = espetaculo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
