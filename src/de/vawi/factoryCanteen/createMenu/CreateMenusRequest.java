package de.vawi.factoryCanteen.createMenu;

import de.vawi.factoryCanteen.entities.Periode;

public class CreateMenusRequest {

    private Periode periode;

    public Periode getPeriode() {
        return periode;
    }

    public void setPeriode(Periode periode) {
        this.periode = periode;
    }
}
