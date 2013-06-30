package de.vawi.factoryCanteen.app.createMenu;

import de.vawi.factoryCanteen.app.entities.PeriodeConfiguration;

public class CreateMenusRequest {

    private PeriodeConfiguration periode;

    public PeriodeConfiguration getPeriode() {
        return periode;
    }

    public void setPeriode(PeriodeConfiguration periode) {
        this.periode = periode;
    }
}
