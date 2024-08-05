//package ru.geekbrains.spring.market.auth.services;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import ru.geekbrains.spring.market.auth.entities.Role;
//import ru.geekbrains.spring.market.auth.entities.User;
//import ru.geekbrains.spring.market.auth.repositories.UserRepository;
//
//import java.util.Collection;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Service
//@RequiredArgsConstructor
//public class UserService implements UserDetailsService {
//
//    private final UserRepository userRepository;
//
//    public Optional<User> findByUsername(String username){
//        return userRepository.findByUsername(username);
//
//    }
//
//    // по имени пользователя отдаем Спрингу небольшую информацию о юзере (которую потом Спринг положит в контекст)
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        User user = findByUsername(username).get();//orElseThrow(() ->
//        //new UsernameNotFoundException(String.format("User '%s' not found", username)));
//        return new org.springframework.security.core.userdetails.User
//                (user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
//
//
//    }
//
//    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
//        return roles.stream().map(role->new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
//
//    }
//
//}
