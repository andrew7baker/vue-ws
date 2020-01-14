<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="production">
                <h2 class="jh-entity-heading"><span>Production</span> {{production.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span>Version</span>
                    </dt>
                    <dd>
                        <span>{{production.version}}</span>
                    </dd>
                    <dt>
                        <span>Power Time</span>
                    </dt>
                    <dd>
                        <span>{{production.powerTime}}</span>
                    </dd>
                    <dt>
                        <span>Place Time</span>
                    </dt>
                    <dd>
                        <span>{{production.placeTime}}</span>
                    </dd>
                    <dt>
                        <span>Wait Time</span>
                    </dt>
                    <dd>
                        <span>{{production.waitTime}}</span>
                    </dd>
                    <dt>
                        <span>Run Time</span>
                    </dt>
                    <dd>
                        <span>{{production.runTime}}</span>
                    </dd>
                    <dt>
                        <span>Idle Time</span>
                    </dt>
                    <dd>
                        <span>{{production.idleTime}}</span>
                    </dd>
                    <dt>
                        <span>In Wait Time</span>
                    </dt>
                    <dd>
                        <span>{{production.inWaitTime}}</span>
                    </dd>
                    <dt>
                        <span>Wrong Stop Time</span>
                    </dt>
                    <dd>
                        <span>{{production.wrongStopTime}}</span>
                    </dd>
                    <dt>
                        <span>Error Stop T Ime</span>
                    </dt>
                    <dd>
                        <span>{{production.errorStopTIme}}</span>
                    </dd>
                    <dt>
                        <span>Wrong Stop Count</span>
                    </dt>
                    <dd>
                        <span>{{production.wrongStopCount}}</span>
                    </dd>
                    <dt>
                        <span>Error Stop Count</span>
                    </dt>
                    <dd>
                        <span>{{production.errorStopCount}}</span>
                    </dd>
                    <dt>
                        <span>Panel In Count</span>
                    </dt>
                    <dd>
                        <span>{{production.panelInCount}}</span>
                    </dd>
                    <dt>
                        <span>Panel Count</span>
                    </dt>
                    <dd>
                        <span>{{production.panelCount}}</span>
                    </dd>
                    <dt>
                        <span>P CB Count</span>
                    </dt>
                    <dd>
                        <span>{{production.pCBCount}}</span>
                    </dd>
                    <dt>
                        <span>Error Pcb</span>
                    </dt>
                    <dd>
                        <span>{{production.errorPcb}}</span>
                    </dd>
                    <dt>
                        <span>Skip PCB</span>
                    </dt>
                    <dd>
                        <span>{{production.skipPCB}}</span>
                    </dd>
                    <dt>
                        <span>Operation Rate</span>
                    </dt>
                    <dd>
                        <span>{{production.operationRate}}</span>
                    </dd>
                    <dt>
                        <span>Placement Rate</span>
                    </dt>
                    <dd>
                        <span>{{production.placementRate}}</span>
                    </dd>
                    <dt>
                        <span>Mean Time</span>
                    </dt>
                    <dd>
                        <span>{{production.meanTime}}</span>
                    </dd>
                    <dt>
                        <span>Real Time</span>
                    </dt>
                    <dd>
                        <span>{{production.realTime}}</span>
                    </dd>
                    <dt>
                        <span>Transfer Time</span>
                    </dt>
                    <dd>
                        <span>{{production.transferTime}}</span>
                    </dd>
                    <dt>
                        <span>Place Count</span>
                    </dt>
                    <dd>
                        <span>{{production.placeCount}}</span>
                    </dd>
                    <dt>
                        <span>The Efficiency</span>
                    </dt>
                    <dd>
                        <span>{{production.theEfficiency}}</span>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span> Back</span>
                </button>
                <router-link v-if="production.id" :to="{name: 'ProductionEdit', params: {productionId: production.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./production-details.component.ts">
</script>
