package com.mycompany.myapp.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A SysOperationLog.
 */
@Entity
@Table(name = "sys_operation_log")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SysOperationLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "log_type")
    private String logType;

    @Column(name = "log_name")
    private String logName;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "class_name")
    private String className;

    @Column(name = "method")
    private String method;

    @Column(name = "succeed")
    private String succeed;

    @Column(name = "create_time")
    private Instant createTime;

    @Column(name = "message")
    private String message;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogType() {
        return logType;
    }

    public SysOperationLog logType(String logType) {
        this.logType = logType;
        return this;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public String getLogName() {
        return logName;
    }

    public SysOperationLog logName(String logName) {
        this.logName = logName;
        return this;
    }

    public void setLogName(String logName) {
        this.logName = logName;
    }

    public Long getUserId() {
        return userId;
    }

    public SysOperationLog userId(Long userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getClassName() {
        return className;
    }

    public SysOperationLog className(String className) {
        this.className = className;
        return this;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethod() {
        return method;
    }

    public SysOperationLog method(String method) {
        this.method = method;
        return this;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getSucceed() {
        return succeed;
    }

    public SysOperationLog succeed(String succeed) {
        this.succeed = succeed;
        return this;
    }

    public void setSucceed(String succeed) {
        this.succeed = succeed;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public SysOperationLog createTime(Instant createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public String getMessage() {
        return message;
    }

    public SysOperationLog message(String message) {
        this.message = message;
        return this;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SysOperationLog)) {
            return false;
        }
        return id != null && id.equals(((SysOperationLog) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "SysOperationLog{" +
            "id=" + getId() +
            ", logType='" + getLogType() + "'" +
            ", logName='" + getLogName() + "'" +
            ", userId=" + getUserId() +
            ", className='" + getClassName() + "'" +
            ", method='" + getMethod() + "'" +
            ", succeed='" + getSucceed() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", message='" + getMessage() + "'" +
            "}";
    }
}
