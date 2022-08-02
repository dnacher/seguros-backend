package com.software.seguros.seguros.controller;

import com.software.seguros.seguros.persistence.model.Banco;
import com.software.seguros.seguros.service.BancoService;
import com.software.seguros.seguros.utils.UtilsGeneral;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @PutMapping(value = "/mul")
    public List<Banco> updateBanco(@RequestBody List<Banco> bancoes) {
        List<Banco> finalList = new ArrayList<>();
        bancoes.forEach(
                banco -> {
                    finalList.add(this.bancoService.updateBanco(banco));
                });
        return finalList;
    }

    @GetMapping(value = "/")
    public List<Banco> getBanco() {
        return this.bancoService.getBancos();
    }

    @GetMapping(value = "/{id}")
    public Banco getBancoById(@PathVariable Integer id) {
        return this.bancoService.getBancoById(id);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteBanco(@PathVariable Integer id, Banco banco) {
        String msg =
                String.format("The Banco Id %s is different from the Url Id", banco.getId());
        UtilsGeneral.validateUrlIdEqualsBodyId(id, banco.getId(), msg);
        this.bancoService.deleteBanco(banco);
    }
}
