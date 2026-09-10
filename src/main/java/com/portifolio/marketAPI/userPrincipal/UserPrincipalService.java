package com.portifolio.marketAPI.userPrincipal;

import com.portifolio.marketAPI.entity.User;
import com.portifolio.marketAPI.exception.NotFoundException;
import com.portifolio.marketAPI.repository.UserRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserPrincipalService {

    private final UserRepository userRepository;

    public UserPrincipalService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserPrincipal loadUserByUsername(@NonNull String id, String establishmentId) throws UsernameNotFoundException {

        User user = userRepository.findByIdAndEstablishmentId(id, establishmentId)
                .orElseThrow(() -> new NotFoundException("colaborador não encontrado"));

        return new UserPrincipal(user);

    }
}
