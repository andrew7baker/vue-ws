package com.mycompany.myapp.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A SysRelationType.
 */
@Entity
@Table(name = "sys_relation_type")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SysRelationType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "type_code")
    private String typeCode;

    @Column(name = "typed_name")
    private String typedName;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public SysRelationType typeCode(String typeCode) {
        this.typeCode = typeCode;
        return this;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypedName() {
        return typedName;
    }

    public SysRelationType typedName(String typedName) {
        this.typedName = typedName;
        return this;
    }

    public void setTypedName(String typedName) {
        this.typedName = typedName;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SysRelationType)) {
            return false;
        }
        return id != null && id.equals(((SysRelationType) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "SysRelationType{" +
            "id=" + getId() +
            ", typeCode='" + getTypeCode() + "'" +
            ", typedName='" + getTypedName() + "'" +
            "}";
    }
}
