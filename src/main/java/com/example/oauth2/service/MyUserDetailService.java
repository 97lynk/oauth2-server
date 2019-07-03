package com.example.oauth2.service;

import com.example.oauth2.model.Account;
import com.example.oauth2.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        System.out.println("Load user " + s);
        Optional<Account> accountOptional = accountRepository.findByUsername(s);

        accountOptional.orElseThrow(() -> new UsernameNotFoundException("Not found user with user name = " + s));

        Account account = accountOptional.get();
        return new User(account.getUsername(), account.getPassword(), AuthorityUtils.createAuthorityList("ADMIN"));
    }
}
