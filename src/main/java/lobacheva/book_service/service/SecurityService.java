package lobacheva.book_service.service;

import lobacheva.book_service.exception.EntityNotFoundException;
import lobacheva.book_service.model.User;
import lobacheva.book_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SecurityService implements UserDetailsService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(username).orElseThrow(()
                -> new EntityNotFoundException("User with usermame=" + username + " was not found"));
        user.setEnabled(true);
        log.info("User with usermame=" + username + " is enabled");
        return user;
    }

}