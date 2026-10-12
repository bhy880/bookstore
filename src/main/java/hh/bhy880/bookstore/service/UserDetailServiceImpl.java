package hh.bhy880.bookstore.service;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import hh.bhy880.bookstore.domain.User;
import hh.bhy880.bookstore.domain.UserRepository;

@Service 
public class UserDetailServiceImpl implements UserDetailsService {

    private  final UserRepository repository;

    public UserDetailServiceImpl(UserRepository userRepository) {

        this.repository = userRepository;
    }

    @Override 
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User currUser = repository.findByUsername(username);
            if(currUser == null){
                throw new  UsernameNotFoundException(username);
            }
        UserDetails user = new org.springframework.security.core.userdetails.User(username, currUser.getPassword(),
            AuthorityUtils.createAuthorityList( currUser.getRole()));
        return  user;
    }



}
