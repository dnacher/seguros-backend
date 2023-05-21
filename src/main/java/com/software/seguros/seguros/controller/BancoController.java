package com.software.seguros.seguros.controller;

import com.software.seguros.seguros.persistence.model.Banco;
import com.software.seguros.seguros.service.BancoService;
import com.software.seguros.seguros.utils.UtilsGeneral;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Daniel Nacher
 * 2022-08-02
 */

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/bancos")
public class BancoController {

    private final BancoService bancoService;

    public BancoController(BancoService bancoService) {
        this.bancoService = bancoService;
    }

    @GetMapping(value = "/")
    public List<Banco> getBanco() {
        return this.bancoService.getBancos();
    }

    @GetMapping(value = "/uuid/{uuid}")
    public Banco getBancoById(@PathVariable String uuid) {
        return this.bancoService.getBancoByUuid(uuid);
    }

    @GetMapping(value = "/{id}")
    public Banco getBancoById(@PathVariable Integer id) {
        return this.bancoService.getBancoById(id);
    }

    @GetMapping(value = "/count-by-nombre/{nombre}")
    public Integer countBancoByNombre(@PathVariable String nombre) {
        return this.bancoService.countBancoByNombre(nombre);
    }

    @PostMapping(value = "/")
    public Banco saveBanco(@RequestBody Banco banco) {
        return this.bancoService.saveBanco(banco);
    }

    @PutMapping(value = "/{id}")
    public Banco updateBanco(
            @PathVariable Integer id, @RequestBody Banco banco) {
        String msg =
                String.format("The Banco Id %s is different from the Url Id", banco.getId());
        UtilsGeneral.validateUrlIdEqualsBodyId(id, banco.getId(), msg);
        return this.bancoService.updateBanco(banco);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteBanco(@PathVariable Integer id, Banco banco) {
        String msg =
                String.format("The Banco Id %s is different from the Url Id", banco.getId());
        UtilsGeneral.validateUrlIdEqualsBodyId(id, banco.getId(), msg);
        this.bancoService.deleteBanco(banco);
    }
}
