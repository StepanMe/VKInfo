package com.example.vkinfo;

public class VKUser {
    private String id;
    private String firstName;
    private String lastName;
    private String avatarLink;

    public VKUser(String id, String firstName, String lastName, String avatarLink) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatarLink = avatarLink;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAvatarLink() {
        return avatarLink;
    }

    public void setAvatarLink(String avatarLink) {
        this.avatarLink = avatarLink;
    }

}
