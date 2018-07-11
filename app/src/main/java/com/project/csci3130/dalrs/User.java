package com.project.csci3130.dalrs;
import com.google.firebase.database.Exclude;

/**
 * The type User.
 */
public class User {

    private String id;
    private String Email;
    private String DisplayName;
    /**
     * The Uid.
     */
    public String UID;

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return Email;
    }

    /**
     * Gets display name.
     *
     * @return the display name
     */
    public String getDisplayName() {
        return DisplayName;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        Email = email;
    }

    /**
     * Sets display name.
     *
     * @param displayName the display name
     */
    public void setDisplayName(String displayName) {
        DisplayName = displayName;
    }

    /**
     * Gets uid.
     *
     * @return the uid
     */
    @Exclude

    public String getUID() {
        return UID;
    }

    /**
     * Sets uid.
     *
     * @param UID the uid
     */
    @Exclude

    public void setUID(String UID) {
        this.UID = UID;
    }

    /**
     * Instantiates a new User.
     */
    public User(){

    }

    /**
     * Instantiates a new User.
     *
     * @param id          the id
     * @param email       the email
     * @param displayName the display name
     * @param UID         the uid
     */
    public User(String id, String email, String displayName, String UID) {
        this.id = id;
        Email = email;
        DisplayName = displayName;
        this.UID = UID;
    }

}
