package com.software.seguros.seguros.controller;

import com.software.seguros.seguros.enums.Logger.LogManagerClass;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Daniel Nacher 2024-04-24 */
@Controller
public class LogoutController {

    private final LogManagerClass log = new LogManagerClass(getClass());

    @GetMapping("/logout")
    public String logout() {
        log.info("entre");
        // Obtiene los detalles del usuario autenticado
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Invalida el token JWT asociado al usuario
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        new SecurityContextLogoutHandler().logout(null, null, authentication);

        // Redirige a la página de inicio o a donde quieras después del logout
        return "";
    }

}
