package ksiprus.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class User {
    private int userId;
    private String login;
    private String password;
    private String name;
    private LocalDate birthDate;
    private LocalDateTime regDate;
    private String role; // "User" или "Admin"


    private User(Builder builder) {
        this.userId = builder.id;
        this.login = builder.login;
        this.password = builder.password;
        this.name = builder.name;
        this.birthDate = builder.birthDate;
        this.regDate = builder.regDate;
        this.role = builder.role;
    }

    // Статический класс Builder
    public static class Builder {
        private int id;
        private String login;
        private String password;
        private String name;
        private LocalDate birthDate;
        private LocalDateTime regDate;
        private String role; // "User" или "Admin"

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder login(String login) {
            this.login = login;
            return this;
        }


        public Builder password(String password) {
            this.password = password;
            return this;
        }


        public Builder name(String name) {
            this.name = name;
            return this;
        }


        public Builder birthDate(LocalDate birthDate) {
            this.birthDate = birthDate;
            return this;
        }


        public Builder regDate(LocalDateTime regDate) {
            this.regDate = regDate;
            return this;
        }


        public Builder role(String role) {
            this.role = role;
            return this;
        }


        public User build() {
            return new User(this);
        }
    }


    public int getUserId() {
        return userId;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public LocalDateTime getRegDate() {
        return regDate;
    }

    public String getRole() {
        return role;
    }
}
