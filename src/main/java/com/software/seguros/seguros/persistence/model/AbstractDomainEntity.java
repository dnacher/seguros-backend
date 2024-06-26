package com.software.seguros.seguros.persistence.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * Daniel Nacher
 * 2022-08-02
 */
@MappedSuperclass
public abstract class AbstractDomainEntity {

    @Column(
            name = "uuid",
            updatable = false,
            nullable = false,
            unique = true
    )
    private String uuid = UUID.randomUUID().toString();

    @JsonIgnore
    @Column(name = "created", nullable = false, updatable = false)
    private LocalDateTime created;

    @JsonIgnore
    @Column(name = "updated")
    private LocalDateTime updated;

    @JsonIgnore
    @Column(name = "removed")
    private LocalDateTime removed;

    protected AbstractDomainEntity() {
    }

    @PrePersist
    protected void onPrePersist() {
        this.created = LocalDateTime.now();
    }

    @PreUpdate
    protected void onPreUpdate() {
        if(this.getUuid()==null){
            this.setUuid(UUID.randomUUID().toString());
        }
        if(this.getCreated()==null){
            this.setCreated(LocalDateTime.now());
        }
        this.updated = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractDomainEntity that = (AbstractDomainEntity) o;
        return Objects.equals(uuid, that.uuid) && Objects.equals(created, that.created) && Objects.equals(updated, that.updated) && Objects.equals(removed, that.removed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, created, updated, removed);
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public LocalDateTime getRemoved() {
        return removed;
    }

    public void setRemoved(LocalDateTime removed) {
        this.removed = removed;
    }

    public String logString() {
        return "uuid='" + uuid + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                ", removed=" + removed;
    }
}
