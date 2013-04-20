package de.vawi.kuechenchefApp;

import de.vawi.kuechenchefApp.menues.MenusCreator;
import de.vawi.kuechenchefApp.menues.Menu;
import de.vawi.kuechenchefApp.purchaseList.PurchaseListCreator;
import de.vawi.kuechenchefApp.purchaseList.PurchaseList;
import de.vawi.kuechenchefApp.costs.CostReportCreator;
import de.vawi.kuechenchefApp.costs.CostReport;
import java.util.*;

/**
 * Diese Klasse steuert den Prozess für die Erstellung der Speisepläne, Einkaufsliste und Kostenaufstellung.
 * @author Beer 
 * @version 30.01.2013
 */
class ProzessSteuerung{

    private MenusCreator speiseplanErsteller;
    private PurchaseListCreator einkaufslistenErsteller;
    private CostReportCreator kostanaufstellungErsteller;
    
    private PurchaseList einkaufsliste;
    private List<Menu> speiseplaene = new ArrayList<>();
    private CostReport kostenUbersicht;
    
    /**
     * Übergeben des SpeiseplanErsteller.
     * 
     * @param  ersteller    Ersteller-Klasse für den Speiseplan Sonja, Tobias, Matthias
     * 
     */
    public void setSpeiseplanErsteller(MenusCreator ersteller){
        this.speiseplanErsteller = ersteller;
    }
    
    /**
     * Übergeben des EinkaufslistenErsteller.
     * 
     * @param  ersteller     Ersteller-Klasse für die Einkaufsliste
     */
    public void setEinkaufslistenErsteller(PurchaseListCreator ersteller){
        this.einkaufslistenErsteller = ersteller;
    }
    
    /**
     * Übergeben des CostReportCreator.
     * 
     * @param  ersteller  Ersteller-Klasse für die Kostenaufstellung
     */
    public void setKostenaufstellungErsteller(CostReportCreator ersteller){
        this.kostanaufstellungErsteller = ersteller;
    }
    
    /**
     * Organisiert den Ablauf der Erstellung der Speisepläne, der dazugehörigen Einkaufsliste und die daraus resultierende Kostenaufstellung.
     * Funktionert nur wenn vorher die entsprechenden Ersteller für die Speisepläne, Einkaufsliste und Kostenaufstellung gesetzt wurden.
     */
    public void start() {
        erzeugeSpeiseplaene();
        erzeugeEinkaufsliste();
        erzeugeKostenUbersicht();
    }

    
    /**
     * Nach dem Ausführung der Methode "start", kann hier die Kostenaufstellung geholt werden.
     * 
     * @return     Kostenaufstellung
     */
    public CostReport getKostenUbersicht() {
        return kostenUbersicht;
    }
    
    
    /**
     * Nach dem Ausführung der Methode "start", können hier die Speisepläne geholt werden.
     * 
     * @return     Liste von Speiseplänen
     */
    public List<Menu> getSpeiseplaene(){
        return this.speiseplaene;
    }
    
    /**
     * Nach dem Ausführung der Methode "start", kann hier die Einkaufsliste geholt werden.
     * 
     * @return     Einkaufsliste
     */
    public PurchaseList getEinkaufsliste(){
        return this.einkaufsliste;
    }

    private void erzeugeSpeiseplaene() {
        speiseplaene.addAll(speiseplanErsteller.erzeuge());
    }

    private void erzeugeEinkaufsliste() {
        einkaufslistenErsteller.add(speiseplaene.get(0));
        einkaufslistenErsteller.add(speiseplaene.get(1));
        this.einkaufsliste = einkaufslistenErsteller.erzeuge();
    }

    private void erzeugeKostenUbersicht() {
        kostanaufstellungErsteller.setEinkaufsliste(einkaufsliste);
        kostenUbersicht = kostanaufstellungErsteller.erstelle();
    }
    
    
    
}
