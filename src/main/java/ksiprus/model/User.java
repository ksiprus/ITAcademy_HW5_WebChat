package ksiprus.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class User {
    private int id;
    private String login;
    private String password;
    private String name;
    private LocalDate birthDate;
    private LocalDateTime regDate;
    private String role; // "User" или "Admin"

    private User(Builder builder) {
        this.id = builder.id;
        this.login = builder.login;
        this.password = builder.password;
        this.name = builder.name;
        this.birthDate = builder.birthDate;
        this.regDate = builder.regDate;
        this.role = builder.role;
    }
    public static class Builder {
        private int id;
        private String login;
        private String password;
        private String name;
        private LocalDate birthDate;
        private LocalDateTime regDate;
        private String role; // "User" или "Admin"

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDateTime getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDateTime regDate) {
        this.regDate = regDate;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}