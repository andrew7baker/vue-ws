package com.mycompany.myapp.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A SysDictType.
 */
@Entity
@Table(name = "sys_dict_type")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SysDictType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "system_flag")
    private String systemFlag;

    @Column(name = "status")
    private String status;

    @Column(name = "sort")
    private Integer sort;

    @Column(name = "description")
    private String description;

    @Column(name = "create_time")
    private Instant createTime;

    @Column(name = "create_user")
    private Long createUser;

    @Column(name = "update_time")
    private Instant updateTime;

    @Column(name = "update_user")
    private Long updateUser;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public SysDictType code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public SysDictType name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSystemFlag() {
        return systemFlag;
    }

    public SysDictType systemFlag(String systemFlag) {
        this.systemFlag = systemFlag;
        return this;
    }

    public void setSystemFlag(String systemFlag) {
        this.systemFlag = systemFlag;
    }

    public String getStatus() {
        return status;
    }

    public SysDictType status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getSort() {
        return sort;
    }

    public SysDictType sort(Integer sort) {
        this.sort = sort;
        return this;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getDescription() {
        return description;
    }

    public SysDictType description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public SysDictType createTime(Instant createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public SysDictType createUser(Long createUser) {
        this.createUser = createUser;
        return this;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Instant getUpdateTime() {
        return updateTime;
    }

    public SysDictType updateTime(Instant updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateUser() {
        return updateUser;
    }

    public SysDictType updateUser(Long updateUser) {
        this.updateUser = updateUser;
        return this;
    }

    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SysDictType)) {
            return false;
        }
        return id != null && id.equals(((SysDictType) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "SysDictType{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", systemFlag='" + getSystemFlag() + "'" +
            ", status='" + getStatus() + "'" +
            ", sort=" + getSort() +
            ", description='" + getDescription() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", createUser=" + getCreateUser() +
            ", updateTime='" + getUpdateTime() + "'" +
            ", updateUser=" + getUpdateUser() +
            "}";
    }
}
