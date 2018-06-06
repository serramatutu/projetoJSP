package database.dbo;

import java.util.UUID;
import java.time.LocalDateTime;
/**
 *
 * @author u16187
 */
public class EdicaoEspetaculo {
    private UUID id;
    private UUID espetaculo;
    private UUID localEvento;
    private LocalDateTime dataEspetaculo;
    
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

    public LocalDateTime getDataEspetaculo() {
        return dataEspetaculo;
    }

    public void setDataEspetaculo(LocalDateTime dataEspetaculo) {
        this.dataEspetaculo = dataEspetaculo;
    }  
    
    public UUID getLocalEvento() {
        return localEvento;
    }
    
    public void setLocalEvento(UUID localEvento) {
        this.localEvento = localEvento;
    }
}
