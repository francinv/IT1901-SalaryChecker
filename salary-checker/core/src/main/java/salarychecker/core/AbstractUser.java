package salarychecker.core;

import java.util.ArrayList;
import java.util.Collection;

public abstract class AbstractUser {

    protected String firstname;
    protected String lastname;
    protected String email;
    protected String password;

    

    protected UserValidation userValidation = new UserValidation();
    protected Collection<IUserObserver> userObs = new ArrayList<IUserObserver>();

    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        userValidation.checkValidFirstname(firstname);
        this.firstname = firstname;
        for (IUserObserver IUserObserver : userObs) {
            IUserObserver.userInfoStringChanged((User) this, firstname);
        }
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        userValidation.checkValidLastname(lastname);
        this.lastname = lastname;
        for (IUserObserver IUserObserver : userObs) {
            IUserObserver.userInfoStringChanged((User) this, lastname);
        }
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        userValidation.checkValidEmail(email);
        this.email = email;
        for (IUserObserver IUserObserver : userObs) {
            IUserObserver.userInfoStringChanged((User) this, email);
        }
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        userValidation.checkValidPassword(password);
        this.password = password;
        for (IUserObserver IUserObserver : userObs) {
            IUserObserver.userInfoStringChanged((User) this, password);
        }
    }


    public void addObserver(IUserObserver uObserver){
        userObs.add(uObserver);
    }

    public void removeObserver(IUserObserver uObserver){
        userObs.remove(uObserver);
    }

    public Collection<IUserObserver> getUserObs() {
        return new ArrayList<>(userObs);
    }



    @Override
    public String toString() {
        return "{" +
            " firstname='" + getFirstname() + "'" +
            ", lastname='" + getLastname() + "'" +
            ", email='" + getEmail() + "'" +
            ", password='" + getPassword() + "'" +
            "}";
    }

    

}
