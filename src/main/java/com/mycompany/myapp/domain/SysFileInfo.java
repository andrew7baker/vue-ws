package com.mycompany.myapp.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A SysFileInfo.
 */
@Entity
@Table(name = "sys_file_info")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SysFileInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "machine_code")
    private String machineCode;

    @Column(name = "file_bucket")
    private String fileBucket;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_suffix")
    private String fileSuffix;

    @Column(name = "file_size_kb")
    private Long fileSizeKb;

    @Column(name = "final_name")
    private String finalName;

    @Column(name = "file_path")
    private String filePath;

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

    public String getMachineCode() {
        return machineCode;
    }

    public SysFileInfo machineCode(String machineCode) {
        this.machineCode = machineCode;
        return this;
    }

    public void setMachineCode(String machineCode) {
        this.machineCode = machineCode;
    }

    public String getFileBucket() {
        return fileBucket;
    }

    public SysFileInfo fileBucket(String fileBucket) {
        this.fileBucket = fileBucket;
        return this;
    }

    public void setFileBucket(String fileBucket) {
        this.fileBucket = fileBucket;
    }

    public String getFileName() {
        return fileName;
    }

    public SysFileInfo fileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileSuffix() {
        return fileSuffix;
    }

    public SysFileInfo fileSuffix(String fileSuffix) {
        this.fileSuffix = fileSuffix;
        return this;
    }

    public void setFileSuffix(String fileSuffix) {
        this.fileSuffix = fileSuffix;
    }

    public Long getFileSizeKb() {
        return fileSizeKb;
    }

    public SysFileInfo fileSizeKb(Long fileSizeKb) {
        this.fileSizeKb = fileSizeKb;
        return this;
    }

    public void setFileSizeKb(Long fileSizeKb) {
        this.fileSizeKb = fileSizeKb;
    }

    public String getFinalName() {
        return finalName;
    }

    public SysFileInfo finalName(String finalName) {
        this.finalName = finalName;
        return this;
    }

    public void setFinalName(String finalName) {
        this.finalName = finalName;
    }

    public String getFilePath() {
        return filePath;
    }

    public SysFileInfo filePath(String filePath) {
        this.filePath = filePath;
        return this;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public SysFileInfo createTime(Instant createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public SysFileInfo createUser(Long createUser) {
        this.createUser = createUser;
        return this;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Instant getUpdateTime() {
        return updateTime;
    }

    public SysFileInfo updateTime(Instant updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateUser() {
        return updateUser;
    }

    public SysFileInfo updateUser(Long updateUser) {
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
        if (!(o instanceof SysFileInfo)) {
            return false;
        }
        return id != null && id.equals(((SysFileInfo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "SysFileInfo{" +
            "id=" + getId() +
            ", machineCode='" + getMachineCode() + "'" +
            ", fileBucket='" + getFileBucket() + "'" +
            ", fileName='" + getFileName() + "'" +
            ", fileSuffix='" + getFileSuffix() + "'" +
            ", fileSizeKb=" + getFileSizeKb() +
            ", finalName='" + getFinalName() + "'" +
            ", filePath='" + getFilePath() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", createUser=" + getCreateUser() +
            ", updateTime='" + getUpdateTime() + "'" +
            ", updateUser=" + getUpdateUser() +
            "}";
    }
}
