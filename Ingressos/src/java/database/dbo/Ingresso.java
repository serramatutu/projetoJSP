package database.dbo;

import java.util.UUID;

/**
 *
 * @author u16187
 */
public class Ingresso {
    private UUID tipoIngresso;
    private UUID edicaoEspetaculo;
    private UUID assento;
    private UUID espectador;
    private int statusIngresso;
    
    public UUID getTipoIngresso() {
        return tipoIngresso;
    }

    public void setTipoIngresso(UUID tipoIngresso) {
        this.tipoIngresso = tipoIngresso;
    }

    public UUID getEdicaoEspetaculo() {
        return edicaoEspetaculo;
    }

    public void setEdicaoEspetaculo(UUID edicaoEspetaculo) {
        this.edicaoEspetaculo = edicaoEspetaculo;
    }

    public UUID getAssento() {
        return assento;
    }

    public void setAssento(UUID assento) {
        this.assento = assento;
    }

    public UUID getEspectador() {
        return espectador;
    }

    public void setEspectador(UUID espectador) {
        this.espectador = espectador;
    }

    public int getStatusIngresso() {
        return statusIngresso;
    }

    public void setStatusIngresso(int statusIngresso) {
        this.statusIngresso = statusIngresso;
    }
}
