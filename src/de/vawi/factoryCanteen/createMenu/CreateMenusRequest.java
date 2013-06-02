package de.vawi.factoryCanteen.createMenu;

import de.vawi.factoryCanteen.entities.PeriodeConfiguration;

public class CreateMenusRequest {

    private PeriodeConfiguration periode;

    public PeriodeConfiguration getPeriode() {
        return periode;
    }

    public void setPeriode(PeriodeConfiguration periode) {
        this.periode = periode;
    }
}
