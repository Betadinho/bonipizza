package de.alpha.bonipizza.backingbeans;

import com.sun.faces.context.SessionMap;
import de.alpha.bonipizza.entities.LoginDAO;
import de.alpha.bonipizza.entities.User;
import de.alpha.bonipizza.util.SessionUtils;

import javax.servlet.http.HttpSession;

import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.context.ExternalContext;

import java.io.Serializable;

/**
 * Created by prechtla on 01.06.17.
 */
@ManagedBean(name = "LoginBean")
@SessionScoped
public class LoginBean implements Serializable{
    private static final long serialVersionUID = 3L;

    private String inputName;
    private String inputPassword;
    private String message;

    public String Login() {
        if (LoginDAO.userLoginCheck(inputName, inputPassword)) {
            User user = new User(0, inputName, "", null);
            HttpSession session = SessionUtils.getSession();
            session.setAttribute("username", inputName);
            return "/private/indexAuth.xhtml?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(
                            FacesMessage.SEVERITY_WARN, "Incorrect username or password",
                            "Please enter correct username and password or go register!"));
            return "/login.xhtml";
        }
    }

    public String register() {
        if (LoginDAO.registerUser(inputName, inputPassword)) {
            return "/indexAuth.xhtml?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(
                            FacesMessage.SEVERITY_WARN, "Registration failed: username already taken or invalid",
                            "Please enter a new username"));
            return "login.xhtml";
        }
    }

    // ------- Getter und setter ---------------- //
    public String getInputName() {
        return inputName;
    }

    public void setInputName(String inputNuame) {
        this.inputName = inputNuame;
    }

    public String getInputPassword() {
        return inputPassword;
    }

    public void setInputPassword(String inputPassword) {
        this.inputPassword = inputPassword;
    }

    public String getMessage() { return message; }

    public void setMessage(String message) { this.message = message; }
}
