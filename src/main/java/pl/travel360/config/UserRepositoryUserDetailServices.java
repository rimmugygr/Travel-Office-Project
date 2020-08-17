package pl.travel360.config;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.travel360.model.User;
import pl.travel360.repository.UserRepository;

import java.util.Collection;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class UserRepositoryUserDetailServices implements UserDetailsService {
    private final UserRepository userRep;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRep.getOneByUsername(s)
                .map(BridgeUser::new)
                .orElseThrow(() -> new UsernameNotFoundException(s));
    }


    static class BridgeUser extends User implements UserDetails {

        public BridgeUser(User user) {
            super(user);
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return this.getUserAuthorities().stream()
                    .map(a -> new SimpleGrantedAuthority(a.getName()))
                    .collect(Collectors.toList());
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }



}

