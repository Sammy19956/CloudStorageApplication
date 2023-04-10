package com.udacity.jwdnd.course1.cloudstorage.request;

public class CredentialsForm {
    private Integer credentialId;
    private String url;
    private String userName;
    private String key;
    private String password;
    private String decryptedPassword;
    private Integer userId;

    // Constructor
    public CredentialsForm(Integer credentialId, String url, String userName,
                           String key, String password, String decryptedPassword, Integer userId) {
        this.credentialId = credentialId;
        this.url = url;
        this.userName = userName;
        this.key = key;
        this.password = password;
        this.decryptedPassword = decryptedPassword;
        this.userId = userId;
    }

    // Getter and Setter methods
    public Integer getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(Integer credentialId) {
        this.credentialId = credentialId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDecryptedPassword() { return decryptedPassword; }

    public void setDecryptedPassword(String decryptedPassword) {
        this.decryptedPassword = decryptedPassword;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    // Override toString() method
    @Override
    public String toString() {
        return "Credential Id: " + this.credentialId +
               " Url: " + this.url +
               " User Name: " + this.userName +
               " Key: "+ this.key +
               " Password: " + this.password +
               " DecryptedPassword: " + this.decryptedPassword +
               " User Id: " + this.userId;
    }
}
