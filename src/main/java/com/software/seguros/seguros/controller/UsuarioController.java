package com.software.seguros.seguros.controller;

import com.software.seguros.seguros.persistence.model.Usuario;
import com.software.seguros.seguros.service.UsuarioService;
import com.software.seguros.seguros.utils.UtilsGeneral;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Daniel Nacher
 * 2022-08-03
 */

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping(value = "/")
    public List<Usuario> getUsuario() {
        return this.usuarioService.getUsuarios();
    }

    @GetMapping(value = "/{id}")
    public Usuario getUsuarioById(@PathVariable Integer id) {
        return this.usuarioService.getUsuarioById(id);
    }

    @PostMapping(value = "/")
    public Usuario saveUsuario(@RequestBody Usuario usuario) {
        return this.usuarioService.saveUsuario(usuario);
    }

    @PutMapping(value = "/{id}")
    public Usuario updateUsuario(
            @PathVariable Integer id, @RequestBody Usuario usuario) {
        String msg =
                String.format("The Usuario Id %s is different from the Url Id", usuario.getId());
        UtilsGeneral.validateUrlIdEqualsBodyId(id, usuario.getId(), msg);
        return this.usuarioService.updateUsuario(usuario);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteUsuario(@PathVariable Integer id, Usuario usuario) {
        String msg =
                String.format("The Usuario Id %s is different from the Url Id", usuario.getId());
        UtilsGeneral.validateUrlIdEqualsBodyId(id, usuario.getId(), msg);
        this.usuarioService.deleteUsuario(usuario);
    }
}
