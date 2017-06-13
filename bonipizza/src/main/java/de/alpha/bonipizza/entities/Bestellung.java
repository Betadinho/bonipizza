package de.alpha.bonipizza.entities;

import javax.faces.bean.ManagedBean;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by prechtla on 18.05.17.
 */
@SuppressWarnings("JpaAttributeMemberSignatureInspection")
@Entity
@Table(name="orders")
public class Bestellung implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private int bestellungID;
    private String besitzer;
    private int bestellNr;
    private String kommentar;
    private float preis;

    public Bestellung() {}

    public Bestellung(int bestellungID, String besitzer, int bestellNr, String kommentar, float preis) {
        this.bestellungID = bestellungID;
        this.besitzer = besitzer;
        this.bestellNr = bestellNr;
        this.kommentar = kommentar;
        this.preis = preis;
    }

    public Bestellung(String besitzer, int bestellNr, String kommentar, float preis) {
        this.bestellungID = 0;
        this.besitzer = besitzer;
        this.bestellNr = bestellNr;
        this.kommentar = kommentar;
        this.preis = preis;
    }

    // ----- getter/Setter
    public Integer getBestellungID() { return bestellungID; }
    public void setBestellungID(Integer bestellungID) { this.bestellungID = bestellungID; }

    public String getBesitzer() { return besitzer; }
    public void setBesitzer(String besitzer) { this.besitzer = besitzer; }

    public int getBestellNr() { return bestellNr; }
    public void setBestellNr(int bestellNr) { this.bestellNr = bestellNr; }

    public String getKommentar() { return kommentar; }
    public void setKommentar(String kommentar) { this.kommentar = kommentar; }

    public float getPreis() { return preis; }
    public void setPreis(float preis) { this.preis = preis; }

    @Override
    public String toString() {
        //return "{ bestellungID=" + bestellungID + ", besitzer=" + besitzer + ", bestellNr=" + bestellNr + ", kommentar=" + kommentar + ", preis=" + preis + ", bestellDatum=" + (new SimpleDateFormat("yyyy-mm-dd")).format(bestellDatum) + " }";
        return "{ bestellungID=" + bestellungID + ", besitzer=" + besitzer + ", bestellNr=" + bestellNr + ", kommentar=" + kommentar + ", preis=" + preis + " }";
    }

//    @Override
//    public boolean equals( Object obj ) {
//        if( this == obj ) { return true; }
//        if( obj == null ) { return false; }
//        if( getClass() != obj.getClass() ) { return false; }
//        Bestellung other = (Bestellung) obj;
//        if( bestellungID == 0 ) { return other.bestellungID == 0; }
//        return bestellungID.equals( other.bestellungID );
//    }

//    @Override
//    public int hashCode() { //hehehehehehe, 31
//        return 31 + (( bestellungID == 0 ) ? 0 : bestellungID.hashCode());
//    }
}
