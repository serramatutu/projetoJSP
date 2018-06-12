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
    private float altura;
    private float largura;
    private float x;
    private float y;
    private int larguraAssentos;
    private int alturaAssentos;
    
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

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public float getLargura() {
        return largura;
    }

    public void setLargura(float largura) {
        this.largura = largura;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }  
    
    public int getLarguraAssentos() {
        return larguraAssentos;
    }

    public void setLarguraAssentos(int larguraAssentos) {
        this.larguraAssentos = larguraAssentos;
    }

    public int getAlturaAssentos() {
        return alturaAssentos;
    }

    public void setAlturaAssentos(int alturaAssentos) {
        this.alturaAssentos = alturaAssentos;
    }
}
