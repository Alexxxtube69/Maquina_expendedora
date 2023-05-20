package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Slot {
    private int posicio;
    private int quantitat;
    private String codi_producte;

    public void vendre(){
        this.quantitat = this.quantitat-1;
    }

}
