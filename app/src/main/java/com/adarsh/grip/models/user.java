package com.adarsh.grip.models;

public class user {

        private String name;
        private String email;
        private String balance;

        public user(String name, String email, String balance) {
            this.name = name;
            this.email = email;
            this.balance = balance;
        }

        public user() {

        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }
  }
