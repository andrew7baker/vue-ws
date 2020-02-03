package com.mycompany.myapp.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A SysDict.
 */
@Entity
@Table(name = "sys_dict")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SysDict implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "dic_type_id")
    private Long dicTypeId;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "parent_ids")
    private String parentIds;

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

    public Long getDicTypeId() {
        return dicTypeId;
    }

    public SysDict dicTypeId(Long dicTypeId) {
        this.dicTypeId = dicTypeId;
        return this;
    }

    public void setDicTypeId(Long dicTypeId) {
        this.dicTypeId = dicTypeId;
    }

    public String getCode() {
        return code;
    }

    public SysDict code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public SysDict name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public SysDict parentId(Long parentId) {
        this.parentId = parentId;
        return this;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getParentIds() {
        return parentIds;
    }

    public SysDict parentIds(String parentIds) {
        this.parentIds = parentIds;
        return this;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    public String getStatus() {
        return status;
    }

    public SysDict status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getSort() {
        return sort;
    }

    public SysDict sort(Integer sort) {
        this.sort = sort;
        return this;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getDescription() {
        return description;
    }

    public SysDict description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public SysDict createTime(Instant createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public SysDict createUser(Long createUser) {
        this.createUser = createUser;
        return this;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Instant getUpdateTime() {
        return updateTime;
    }

    public SysDict updateTime(Instant updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateUser() {
        return updateUser;
    }

    public SysDict updateUser(Long updateUser) {
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
        if (!(o instanceof SysDict)) {
            return false;
        }
        return id != null && id.equals(((SysDict) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "SysDict{" +
            "id=" + getId() +
            ", dicTypeId=" + getDicTypeId() +
            ", code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", parentId=" + getParentId() +
            ", parentIds='" + getParentIds() + "'" +
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
