package com.example.oauth2;

import com.example.oauth2.model.Account;
import com.example.oauth2.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Oauth2Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Oauth2Application.class, args);
    }

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (!accountRepository.findByUsername("admin").isPresent()) {
            Account account = new Account();
            account.setUsername("admin");
            account.setPassword(passwordEncoder.encode("123"));
            account.setEnable(true);
            accountRepository.save(account);
        }
    }
}
