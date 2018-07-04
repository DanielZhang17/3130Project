package com.project.csci3130.dalrs;
import com.google.firebase.database.Exclude;

public class User {

    private String id;
    private String Email;
    private String DisplayName;
    public String UID;

    public String getId() {
        return id;
    }

    public String getEmail() {
        return Email;
    }

    public String getDisplayName() {
        return DisplayName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setDisplayName(String displayName) {
        DisplayName = displayName;
    }
    @Exclude

    public String getUID() {
        return UID;
    }
    @Exclude

    public void setUID(String UID) {
        this.UID = UID;
    }
    public User(){

    }
    public User(String id, String email, String displayName, String UID) {
        this.id = id;
        Email = email;
        DisplayName = displayName;
        this.UID = UID;
    }

}
