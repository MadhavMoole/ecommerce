package org.example.ecommerce.database.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", nullable = false)
        private Long id;

        @Column(name = "username", nullable = false, unique = true)
        private String username;

        @JsonIgnore
        @Column(name = "password", nullable = false, length = 1000)
        private String password;

        @Column(name = "email", nullable = false, unique = true, length = 320)
        private String email;

        @Column(name = "first_name", nullable = false)
        private String firstname;

        @Column(name = "last_name", nullable = false)
        private String lastname;

        @JsonIgnore
        @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
        private List<Address> addresses = new ArrayList<>();

        @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<VerificationToken>  verificationTokens = new ArrayList<>();

        public List<VerificationToken> getVerificationTokens() {
                return verificationTokens;
        }

        public void setVerificationTokens(List<VerificationToken> verificationTokens) {
                this.verificationTokens = verificationTokens;
        }

        public List<Address> getAddresses() {
                return addresses;
        }

        public void setAddresses(List<Address> addresses) {
                this.addresses = addresses;
        }

        public String getLastname() {
                return lastname;
        }

        public void setLastname(String lastname) {
                this.lastname = lastname;
        }

        public String getfirstname() {
                return firstname;
        }

        public void setfirstname(String firstname) {
                this.firstname = firstname;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
        }

        public String getUsername() {
                return username;
        }

        public void setUsername(String username) {
                this.username = username;
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }
}
