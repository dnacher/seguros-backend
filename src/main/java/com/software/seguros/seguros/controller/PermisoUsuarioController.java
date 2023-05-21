package com.software.seguros.seguros.controller;

import com.software.seguros.seguros.persistence.model.PermisoUsuario;
import com.software.seguros.seguros.persistence.model.TipoUsuario;
import com.software.seguros.seguros.service.PermisoUsuarioService;
import com.software.seguros.seguros.utils.UtilsGeneral;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Daniel Nacher
 * 2022-08-03
 */

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/permiso-usuarios")
public class PermisoUsuarioController {

    private final PermisoUsuarioService permisoUsuarioService;

    public PermisoUsuarioController(PermisoUsuarioService permisoUsuarioService) {
        this.permisoUsuarioService = permisoUsuarioService;
    }

    @GetMapping(value = "/")
    public List<PermisoUsuario> getPermisoUsuario() {
        return this.permisoUsuarioService.getPermisoUsuarios();
    }

    @GetMapping(value = "/uuid/{uuid}")
    public PermisoUsuario getPermisoUsuarioByUuid(@PathVariable String uuid) {
        return this.permisoUsuarioService.getPermisoUsuarioByUuid(uuid);
    }

    @GetMapping(value = "/{id}")
    public PermisoUsuario getPermisoUsuarioById(@PathVariable Integer id) {
        return this.permisoUsuarioService.getPermisoUsuarioById(id);
    }

    @PostMapping(value = "/tipo-usuario")
    public List<PermisoUsuario> findAllByTipoUsuario(@RequestBody TipoUsuario tipoUsuario) {
        return this.permisoUsuarioService.findAllByTipoUsuario(tipoUsuario);
    }

    @PostMapping(value = "/")
    public PermisoUsuario savePermisoUsuario(@RequestBody PermisoUsuario permisoUsuario) {
        return this.permisoUsuarioService.savePermisoUsuario(permisoUsuario);
    }

    @PutMapping(value = "/{id}")
    public PermisoUsuario updatePermisoUsuario(
            @PathVariable Integer id, @RequestBody PermisoUsuario permisoUsuario) {
        String msg =
                String.format("The PermisoUsuario Id %s is different from the Url Id", permisoUsuario.getId());
        UtilsGeneral.validateUrlIdEqualsBodyId(id, permisoUsuario.getId(), msg);
        return this.permisoUsuarioService.updatePermisoUsuario(permisoUsuario);
    }

    @DeleteMapping(value = "/{id}")
    public void deletePermisoUsuario(@PathVariable Integer id, PermisoUsuario permisoUsuario) {
        String msg =
                String.format("The PermisoUsuario Id %s is different from the Url Id", permisoUsuario.getId());
        UtilsGeneral.validateUrlIdEqualsBodyId(id, permisoUsuario.getId(), msg);
        this.permisoUsuarioService.deletePermisoUsuario(permisoUsuario);
    }

    @DeleteMapping(value = "/tipo-usuario")
    public void deleteByTipoUsuario(@RequestBody TipoUsuario tipoUsuario) {
        this.permisoUsuarioService.deleteByTipoUsuario(tipoUsuario);
    }
}
