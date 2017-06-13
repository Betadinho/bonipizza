/**
 * Created by prechtla on 01.06.17.
 */

package de.alpha.bonipizza.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@SuppressWarnings("JpaAttributeMemberSignatureInspection")
@Entity
@Table(name="users")
public class User implements Serializable {
    private static final long serialVersionUID = 2L;

    @Id
    private int usrID;
    private String usrName;
    private String usrPassword;
    private byte[] usrSeasoning;


    public User() {} //Empty Constructor
    public User(int usrID, String usrName, String usrPassword, byte[] usrSeasoning) {
        this.usrID = usrID;
        this.usrName = usrName;
        this.usrPassword = usrPassword;
        this.usrSeasoning = usrSeasoning;
    }

    //getter und setter
    public int getUsrID() { return usrID; }
    public void setUsrID(int usrID) { this.usrID = usrID; }

    public String getUsrName() { return usrName; }
    public void setUsrName(String usrName) { this.usrName = usrName; }

    public String getUsrPassword() { return usrPassword; }
    public void setUsrPassword(String usrPassword) { this.usrPassword = usrPassword; }

    public byte[] getUsrSeasoning() { return usrSeasoning; }
    public void setUsrSeasoning(byte[] usrSeasoning) { this.usrSeasoning = usrSeasoning; }

    @Override
    public String toString() {
        return "{ usrID=" + this.usrID + ", usrName=" + this.usrName + ", usrPassword=" + this.usrPassword + " }";
    }
}

