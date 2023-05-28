package com.software.seguros.seguros.controller;

import com.software.seguros.seguros.persistence.model.TipoUsuario;
import com.software.seguros.seguros.service.TipoUsuarioService;
import com.software.seguros.seguros.utils.UtilsGeneral;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Daniel Nacher
 * 2022-08-03
 */

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/tipo-usuarios")
public class TipoUsuarioController {

    private final TipoUsuarioService tipoUsuarioService;

    public TipoUsuarioController(TipoUsuarioService tipoUsuarioService) {
        this.tipoUsuarioService = tipoUsuarioService;
    }

    @GetMapping(value = "/")
    public List<TipoUsuario> getTipoUsuario() {
        return this.tipoUsuarioService.getTipoUsuarios();
    }

    @GetMapping(value = "/uuid/{uuid}")
    public TipoUsuario getTipoUsuarioByUuid(@PathVariable String uuid) {
        return this.tipoUsuarioService.getTipoUsuarioByUuid(uuid);
    }

    @GetMapping(value = "/{id}")
    public TipoUsuario getTipoUsuarioById(@PathVariable Integer id) {
        return this.tipoUsuarioService.getTipoUsuarioById(id);
    }

    @PostMapping(value = "/")
    public TipoUsuario saveTipoUsuario(@RequestBody TipoUsuario tipoUsuario) {
        return this.tipoUsuarioService.saveTipoUsuario(tipoUsuario);
    }

    @PutMapping(value = "/{id}")
    public TipoUsuario updateTipoUsuario(
            @PathVariable Integer id, @RequestBody TipoUsuario tipoUsuario) {
        String msg =
                String.format("The TipoUsuario Id %s is different from the Url Id", tipoUsuario.getId());
        UtilsGeneral.validateUrlIdEqualsBodyId(id, tipoUsuario.getId(), msg);
        return this.tipoUsuarioService.updateTipoUsuario(tipoUsuario);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteTipoUsuario(@PathVariable Integer id, TipoUsuario tipoUsuario) {
        String msg =
                String.format("The TipoUsuario Id %s is different from the Url Id", tipoUsuario.getId());
        UtilsGeneral.validateUrlIdEqualsBodyId(id, tipoUsuario.getId(), msg);
        try{
            this.tipoUsuarioService.deleteTipoUsuario(tipoUsuario);
        }catch (Exception ex){

        }

    }
}
