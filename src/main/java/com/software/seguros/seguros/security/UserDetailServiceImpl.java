package com.software.seguros.seguros.security;

import com.software.seguros.seguros.persistence.model.Usuario;
import com.software.seguros.seguros.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Daniel Nacher
 * 2023-04-24
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioService.getUsuarioByNombre(username);
        if(usuario==null){
            throw new UsernameNotFoundException("El usuario " + username + " no existe.");
        }else {
            return new UserDetailsImpl(usuario);
        }
    }
}
