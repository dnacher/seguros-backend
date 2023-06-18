package com.software.seguros.seguros.persistence.repository;

import com.software.seguros.seguros.persistence.model.Banco;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface BancoRepository extends JpaRepository<Banco, Integer>, JpaSpecificationExecutor {
    Optional<Banco> findByUuid(String uuid);
    Integer countBancoByNombre(String nombre);
    List<Banco> findAllByOrderByNombreAsc();

    interface BancoSpecifications {
        static Specification<Banco> byNombre(String nombre){
            return (root, query, criteriaBuilder) ->
                    criteriaBuilder.like(root.get("nombre"), "%" + nombre + "%");
        }
    }
}
