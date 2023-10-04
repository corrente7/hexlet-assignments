package exercise;

import exercise.model.User;
import exercise.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        // BEGIN
        if (repository.findByEmail(email).isEmpty()) {
            // Если не существует, возвращаем код ответа 404
            throw new UserNotFoundException("User not found");
        }

        User user = repository.findByEmail(email).get();

        String userRole = user.getRole().toString();

        List<SimpleGrantedAuthority> authorities = List.of(
                new SimpleGrantedAuthority(userRole)
        );

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), authorities
        );
        // END
    }
}