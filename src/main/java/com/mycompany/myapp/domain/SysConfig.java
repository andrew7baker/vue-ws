package com.mycompany.myapp.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A SysConfig.
 */
@Entity
@Table(name = "sys_config")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SysConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "dict_flag")
    private String dictFlag;

    @Column(name = "dict_type_id")
    private Long dictTypeId;

    @Column(name = "value")
    private String value;

    @Column(name = "remark")
    private String remark;

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

    public String getName() {
        return name;
    }

    public SysConfig name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public SysConfig code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDictFlag() {
        return dictFlag;
    }

    public SysConfig dictFlag(String dictFlag) {
        this.dictFlag = dictFlag;
        return this;
    }

    public void setDictFlag(String dictFlag) {
        this.dictFlag = dictFlag;
    }

    public Long getDictTypeId() {
        return dictTypeId;
    }

    public SysConfig dictTypeId(Long dictTypeId) {
        this.dictTypeId = dictTypeId;
        return this;
    }

    public void setDictTypeId(Long dictTypeId) {
        this.dictTypeId = dictTypeId;
    }

    public String getValue() {
        return value;
    }

    public SysConfig value(String value) {
        this.value = value;
        return this;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRemark() {
        return remark;
    }

    public SysConfig remark(String remark) {
        this.remark = remark;
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public SysConfig createTime(Instant createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public SysConfig createUser(Long createUser) {
        this.createUser = createUser;
        return this;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Instant getUpdateTime() {
        return updateTime;
    }

    public SysConfig updateTime(Instant updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateUser() {
        return updateUser;
    }

    public SysConfig updateUser(Long updateUser) {
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
        if (!(o instanceof SysConfig)) {
            return false;
        }
        return id != null && id.equals(((SysConfig) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "SysConfig{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", code='" + getCode() + "'" +
            ", dictFlag='" + getDictFlag() + "'" +
            ", dictTypeId=" + getDictTypeId() +
            ", value='" + getValue() + "'" +
            ", remark='" + getRemark() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", createUser=" + getCreateUser() +
            ", updateTime='" + getUpdateTime() + "'" +
            ", updateUser=" + getUpdateUser() +
            "}";
    }
}
