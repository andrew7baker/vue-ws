package com.mycompany.myapp.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Production.
 */
@Entity
@Table(name = "production")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Production implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "version")
    private String version;

    @Column(name = "power_time")
    private Integer powerTime;

    @Column(name = "place_time")
    private Integer placeTime;

    @Column(name = "wait_time")
    private Integer waitTime;

    @Column(name = "run_time")
    private Integer runTime;

    @Column(name = "stop_time")
    private Integer stopTime;

    @Column(name = "idle_time")
    private Integer idleTime;

    @Column(name = "in_wait_time")
    private Integer inWaitTime;

    @Column(name = "out_wait_time")
    private Integer outWaitTime;

    @Column(name = "trans_time")
    private Integer transTime;

    @Column(name = "wrong_stop_time")
    private Integer wrongStopTime;

    @Column(name = "error_stop_t_ime")
    private Integer errorStopTIme;

    @Column(name = "wrong_stop_count")
    private Integer wrongStopCount;

    @Column(name = "error_stop_count")
    private Integer errorStopCount;

    @Column(name = "panel_in_count")
    private Integer panelInCount;

    @Column(name = "panel_out_count")
    private Integer panelOutCount;

    @Column(name = "panel_count")
    private Integer panelCount;

    @Column(name = "p_cb_count")
    private Integer pCBCount;

    @Column(name = "error_pcb")
    private Integer errorPcb;

    @Column(name = "skip_pcb")
    private Integer skipPCB;

    @Column(name = "operation_rate")
    private Float operationRate;

    @Column(name = "placement_rate")
    private Float placementRate;

    @Column(name = "mean_time")
    private Float meanTime;

    @Column(name = "real_time")
    private Float realTime;

    @Column(name = "transfer_time")
    private Float transferTime;

    @Column(name = "place_count")
    private Integer placeCount;

    @Column(name = "the_efficiency")
    private Float theEfficiency;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public Production version(String version) {
        this.version = version;
        return this;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getPowerTime() {
        return powerTime;
    }

    public Production powerTime(Integer powerTime) {
        this.powerTime = powerTime;
        return this;
    }

    public void setPowerTime(Integer powerTime) {
        this.powerTime = powerTime;
    }

    public Integer getPlaceTime() {
        return placeTime;
    }

    public Production placeTime(Integer placeTime) {
        this.placeTime = placeTime;
        return this;
    }

    public void setPlaceTime(Integer placeTime) {
        this.placeTime = placeTime;
    }

    public Integer getWaitTime() {
        return waitTime;
    }

    public Production waitTime(Integer waitTime) {
        this.waitTime = waitTime;
        return this;
    }

    public void setWaitTime(Integer waitTime) {
        this.waitTime = waitTime;
    }

    public Integer getRunTime() {
        return runTime;
    }

    public Production runTime(Integer runTime) {
        this.runTime = runTime;
        return this;
    }

    public void setRunTime(Integer runTime) {
        this.runTime = runTime;
    }

    public Integer getStopTime() {
        return stopTime;
    }

    public Production stopTime(Integer stopTime) {
        this.stopTime = stopTime;
        return this;
    }

    public void setStopTime(Integer stopTime) {
        this.stopTime = stopTime;
    }

    public Integer getIdleTime() {
        return idleTime;
    }

    public Production idleTime(Integer idleTime) {
        this.idleTime = idleTime;
        return this;
    }

    public void setIdleTime(Integer idleTime) {
        this.idleTime = idleTime;
    }

    public Integer getInWaitTime() {
        return inWaitTime;
    }

    public Production inWaitTime(Integer inWaitTime) {
        this.inWaitTime = inWaitTime;
        return this;
    }

    public void setInWaitTime(Integer inWaitTime) {
        this.inWaitTime = inWaitTime;
    }

    public Integer getOutWaitTime() {
        return outWaitTime;
    }

    public Production outWaitTime(Integer outWaitTime) {
        this.outWaitTime = outWaitTime;
        return this;
    }

    public void setOutWaitTime(Integer outWaitTime) {
        this.outWaitTime = outWaitTime;
    }

    public Integer getTransTime() {
        return transTime;
    }

    public Production transTime(Integer transTime) {
        this.transTime = transTime;
        return this;
    }

    public void setTransTime(Integer transTime) {
        this.transTime = transTime;
    }

    public Integer getWrongStopTime() {
        return wrongStopTime;
    }

    public Production wrongStopTime(Integer wrongStopTime) {
        this.wrongStopTime = wrongStopTime;
        return this;
    }

    public void setWrongStopTime(Integer wrongStopTime) {
        this.wrongStopTime = wrongStopTime;
    }

    public Integer getErrorStopTIme() {
        return errorStopTIme;
    }

    public Production errorStopTIme(Integer errorStopTIme) {
        this.errorStopTIme = errorStopTIme;
        return this;
    }

    public void setErrorStopTIme(Integer errorStopTIme) {
        this.errorStopTIme = errorStopTIme;
    }

    public Integer getWrongStopCount() {
        return wrongStopCount;
    }

    public Production wrongStopCount(Integer wrongStopCount) {
        this.wrongStopCount = wrongStopCount;
        return this;
    }

    public void setWrongStopCount(Integer wrongStopCount) {
        this.wrongStopCount = wrongStopCount;
    }

    public Integer getErrorStopCount() {
        return errorStopCount;
    }

    public Production errorStopCount(Integer errorStopCount) {
        this.errorStopCount = errorStopCount;
        return this;
    }

    public void setErrorStopCount(Integer errorStopCount) {
        this.errorStopCount = errorStopCount;
    }

    public Integer getPanelInCount() {
        return panelInCount;
    }

    public Production panelInCount(Integer panelInCount) {
        this.panelInCount = panelInCount;
        return this;
    }

    public void setPanelInCount(Integer panelInCount) {
        this.panelInCount = panelInCount;
    }

    public Integer getPanelOutCount() {
        return panelOutCount;
    }

    public Production panelOutCount(Integer panelOutCount) {
        this.panelOutCount = panelOutCount;
        return this;
    }

    public void setPanelOutCount(Integer panelOutCount) {
        this.panelOutCount = panelOutCount;
    }

    public Integer getPanelCount() {
        return panelCount;
    }

    public Production panelCount(Integer panelCount) {
        this.panelCount = panelCount;
        return this;
    }

    public void setPanelCount(Integer panelCount) {
        this.panelCount = panelCount;
    }

    public Integer getpCBCount() {
        return pCBCount;
    }

    public Production pCBCount(Integer pCBCount) {
        this.pCBCount = pCBCount;
        return this;
    }

    public void setpCBCount(Integer pCBCount) {
        this.pCBCount = pCBCount;
    }

    public Integer getErrorPcb() {
        return errorPcb;
    }

    public Production errorPcb(Integer errorPcb) {
        this.errorPcb = errorPcb;
        return this;
    }

    public void setErrorPcb(Integer errorPcb) {
        this.errorPcb = errorPcb;
    }

    public Integer getSkipPCB() {
        return skipPCB;
    }

    public Production skipPCB(Integer skipPCB) {
        this.skipPCB = skipPCB;
        return this;
    }

    public void setSkipPCB(Integer skipPCB) {
        this.skipPCB = skipPCB;
    }

    public Float getOperationRate() {
        return operationRate;
    }

    public Production operationRate(Float operationRate) {
        this.operationRate = operationRate;
        return this;
    }

    public void setOperationRate(Float operationRate) {
        this.operationRate = operationRate;
    }

    public Float getPlacementRate() {
        return placementRate;
    }

    public Production placementRate(Float placementRate) {
        this.placementRate = placementRate;
        return this;
    }

    public void setPlacementRate(Float placementRate) {
        this.placementRate = placementRate;
    }

    public Float getMeanTime() {
        return meanTime;
    }

    public Production meanTime(Float meanTime) {
        this.meanTime = meanTime;
        return this;
    }

    public void setMeanTime(Float meanTime) {
        this.meanTime = meanTime;
    }

    public Float getRealTime() {
        return realTime;
    }

    public Production realTime(Float realTime) {
        this.realTime = realTime;
        return this;
    }

    public void setRealTime(Float realTime) {
        this.realTime = realTime;
    }

    public Float getTransferTime() {
        return transferTime;
    }

    public Production transferTime(Float transferTime) {
        this.transferTime = transferTime;
        return this;
    }

    public void setTransferTime(Float transferTime) {
        this.transferTime = transferTime;
    }

    public Integer getPlaceCount() {
        return placeCount;
    }

    public Production placeCount(Integer placeCount) {
        this.placeCount = placeCount;
        return this;
    }

    public void setPlaceCount(Integer placeCount) {
        this.placeCount = placeCount;
    }

    public Float getTheEfficiency() {
        return theEfficiency;
    }

    public Production theEfficiency(Float theEfficiency) {
        this.theEfficiency = theEfficiency;
        return this;
    }

    public void setTheEfficiency(Float theEfficiency) {
        this.theEfficiency = theEfficiency;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Production)) {
            return false;
        }
        return id != null && id.equals(((Production) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Production{" +
            "id=" + getId() +
            ", version='" + getVersion() + "'" +
            ", powerTime=" + getPowerTime() +
            ", placeTime=" + getPlaceTime() +
            ", waitTime=" + getWaitTime() +
            ", runTime=" + getRunTime() +
            ", stopTime=" + getStopTime() +
            ", idleTime=" + getIdleTime() +
            ", inWaitTime=" + getInWaitTime() +
            ", outWaitTime=" + getOutWaitTime() +
            ", transTime=" + getTransTime() +
            ", wrongStopTime=" + getWrongStopTime() +
            ", errorStopTIme=" + getErrorStopTIme() +
            ", wrongStopCount=" + getWrongStopCount() +
            ", errorStopCount=" + getErrorStopCount() +
            ", panelInCount=" + getPanelInCount() +
            ", panelOutCount=" + getPanelOutCount() +
            ", panelCount=" + getPanelCount() +
            ", pCBCount=" + getpCBCount() +
            ", errorPcb=" + getErrorPcb() +
            ", skipPCB=" + getSkipPCB() +
            ", operationRate=" + getOperationRate() +
            ", placementRate=" + getPlacementRate() +
            ", meanTime=" + getMeanTime() +
            ", realTime=" + getRealTime() +
            ", transferTime=" + getTransferTime() +
            ", placeCount=" + getPlaceCount() +
            ", theEfficiency=" + getTheEfficiency() +
            "}";
    }
}
