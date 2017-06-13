package de.alpha.bonipizza.backingbeans;

import de.alpha.bonipizza.entities.Bestellung;
import de.alpha.bonipizza.entities.BestellungDAO;

import java.util.*;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 * Created by prechtla on 18.05.17.
 * Work in Progress (FUNKTIONALITÄT IST DA; STLY FEHLT)
 *
 */
/*
    TODO: Den shit sexyfien (CSS, CSS, CSS!)
    TODO: DATUM und damit korrespondierend wöchentliche Löschung aller bestelungen rein bamsen. (EASY)
    TODO: Der auskommentierte Block zum Testdaten einfügen zerstört wohl die Session mit nem ExceptioninitializeerError(To many connections)
    TODO: Ich weiß nicht ob die Methode addBestellung irgendeinen nutzen hat. GGF. weg damit, sonst umfunktionierren oder so
    TODO: Dashboard implementieren
        todo: Methode zum hinzufügen von testdaten ins dahsboard (als shaltfläche/button) implementieren
            todo: mit variabler anzahl einträge die erstellt werden soll

 */


@ManagedBean(name = "BestellungBean")
@ViewScoped
public class BestellungBean {
    private static final String KEY_IN_SESSION = "einzelElement";
    private List<Bestellung> datenListe;
    private Bestellung einzelElement;
    private Map<Bestellung, Boolean> merkerMap = new HashMap<Bestellung, Boolean>();

    public BestellungBean() {
        einzelElement = new Bestellung();
        //Datenliste mit bullshit füllen (Zerstört momentan wohl die Session mit nem ExceptioninitializeerError(To many connections))
//            for (int i = 0; i < 75; i++) {
//                Bestellung mb = new Bestellung(i, "AP", 12, "Example n shit", 12 );
//                datenListe.add(mb);
//                BestellungDAO.createBestellung(mb);
//                datenListe = BestellungDAO.getAlleBestellungen();
//            }
    }

    //CRUD Methoden
    public String createNewElement() {
        datenListe = BestellungDAO.getAlleBestellungen();

        einzelElement = new Bestellung("", 0, "", 0);

        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(KEY_IN_SESSION, einzelElement);
        return "newOrder.xhtml";
    }

    public void bestellungEntfernen(Bestellung einzelelement) { //Bestellung löschen
        datenListe.remove(einzelelement);
        BestellungDAO.deleteBestellung(einzelelement);
        datenListe = BestellungDAO.getAlleBestellungen();
    }

    public String bestellungSpeichern() {
        datenListe.add(einzelElement);
        BestellungDAO.createBestellung(einzelElement);
        datenListe = BestellungDAO.getAlleBestellungen();
        //einzelElement = new Bestellung();  //Umarbeiten
        return "/private/indexAuth.xhtml?faces-redirect=true";
    }

    public String bestellungBearbeiten(Bestellung eE) {
        datenListe = BestellungDAO.getAlleBestellungen();
        einzelElement = eE;
        int id = einzelElement.getBestellungID();
        String bes = einzelElement.getBesitzer();
        int bsNr = einzelElement.getBestellNr();
        String komm = einzelElement.getKommentar();
        float pr = einzelElement.getPreis();

        einzelElement = new Bestellung(id, bes, bsNr, komm, pr);

        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put( KEY_IN_SESSION, einzelElement);
        return "newOrder.xhtml";
    }


    //----- Setter & Getter
    public List<Bestellung> getDatenListe() {
        datenListe = BestellungDAO.getAlleBestellungen();
        return datenListe;
    }

    public Bestellung getEinzelElement() {
        return this.einzelElement;
    }

    public void setDatenListe(List<Bestellung> datenListe) {
        this.datenListe = datenListe;
    }

    public void setEinzelElement(Bestellung einzelElement) {
        this.einzelElement = einzelElement;
    }
}
