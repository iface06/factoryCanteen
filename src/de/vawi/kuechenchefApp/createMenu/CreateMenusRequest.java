package de.vawi.kuechenchefApp.createMenu;

import de.vawi.kuechenchefApp.entities.Periode;

public class CreateMenusRequest {

    private Periode periode;

    public Periode getPeriode() {
        return periode;
    }

    public void setPeriode(Periode periode) {
        this.periode = periode;
    }
}
