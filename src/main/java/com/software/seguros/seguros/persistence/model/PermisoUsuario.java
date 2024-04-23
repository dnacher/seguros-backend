package com.software.seguros.seguros.persistence.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "permiso_usuario")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class PermisoUsuario extends AbstractDomainEntity {

    public PermisoUsuario(TipoUsuario tipoUsuario, String pagina, Integer permiso) {
        this.tipoUsuario = tipoUsuario;
        this.pagina = pagina;
        this.permiso = permiso;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "tipo_usuario")
    private TipoUsuario tipoUsuario;

    @Column(name="pagina")
    private String pagina;

    @Column(name="permiso")
    private Integer permiso;

    public String toStringLog() {
        return "PermisoUsuario{" +
                "id=" + id +
                ", tipoUsuario=" + tipoUsuario +
                ", pagina='" + pagina + '\'' +
                ", permiso=" + permiso +
                '}';
    }
}
