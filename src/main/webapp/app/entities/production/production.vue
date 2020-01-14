<template>
    <div>
        <h2 id="page-heading">
            <span id="production-heading">Productions</span>
            <router-link :to="{name: 'ProductionCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-production">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span >
                    Create a new Production
                </span>
            </router-link>
        </h2>
        <b-alert :show="dismissCountDown"
            dismissible
            :variant="alertType"
            @dismissed="dismissCountDown=0"
            @dismiss-count-down="countDownChanged">
            {{alertMessage}}
        </b-alert>
        <br/>
        <div class="alert alert-warning" v-if="!isFetching && productions && productions.length === 0">
            <span>No productions found</span>
        </div>
        <div class="table-responsive" v-if="productions && productions.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th v-on:click="changeOrder('id')"><span>ID</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('version')"><span>Version</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('powerTime')"><span>Power Time</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('placeTime')"><span>Place Time</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('waitTime')"><span>Wait Time</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('runTime')"><span>Run Time</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('idleTime')"><span>Idle Time</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('inWaitTime')"><span>In Wait Time</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('wrongStopTime')"><span>Wrong Stop Time</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('errorStopTIme')"><span>Error Stop T Ime</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('wrongStopCount')"><span>Wrong Stop Count</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('errorStopCount')"><span>Error Stop Count</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('panelInCount')"><span>Panel In Count</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('panelCount')"><span>Panel Count</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('pCBCount')"><span>P CB Count</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('errorPcb')"><span>Error Pcb</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('skipPCB')"><span>Skip PCB</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('operationRate')"><span>Operation Rate</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('placementRate')"><span>Placement Rate</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('meanTime')"><span>Mean Time</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('realTime')"><span>Real Time</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('transferTime')"><span>Transfer Time</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('placeCount')"><span>Place Count</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('theEfficiency')"><span>The Efficiency</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="production in productions"
                    :key="production.id">
                    <td>
                        <router-link :to="{name: 'ProductionView', params: {productionId: production.id}}">{{production.id}}</router-link>
                    </td>
                    <td>{{production.version}}</td>
                    <td>{{production.powerTime}}</td>
                    <td>{{production.placeTime}}</td>
                    <td>{{production.waitTime}}</td>
                    <td>{{production.runTime}}</td>
                    <td>{{production.idleTime}}</td>
                    <td>{{production.inWaitTime}}</td>
                    <td>{{production.wrongStopTime}}</td>
                    <td>{{production.errorStopTIme}}</td>
                    <td>{{production.wrongStopCount}}</td>
                    <td>{{production.errorStopCount}}</td>
                    <td>{{production.panelInCount}}</td>
                    <td>{{production.panelCount}}</td>
                    <td>{{production.pCBCount}}</td>
                    <td>{{production.errorPcb}}</td>
                    <td>{{production.skipPCB}}</td>
                    <td>{{production.operationRate}}</td>
                    <td>{{production.placementRate}}</td>
                    <td>{{production.meanTime}}</td>
                    <td>{{production.realTime}}</td>
                    <td>{{production.transferTime}}</td>
                    <td>{{production.placeCount}}</td>
                    <td>{{production.theEfficiency}}</td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'ProductionView', params: {productionId: production.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline">View</span>
                            </router-link>
                            <router-link :to="{name: 'ProductionEdit', params: {productionId: production.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(production)"
                                   variant="danger"
                                   class="btn btn-sm"
                                   v-b-modal.removeEntity>
                                <font-awesome-icon icon="times"></font-awesome-icon>
                                <span class="d-none d-md-inline">Delete</span>
                            </b-button>
                        </div>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <b-modal ref="removeEntity" id="removeEntity" >
            <span slot="modal-title"><span id="vuwsmtApp.production.delete.question">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-production-heading" >Are you sure you want to delete this Production?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-production" v-on:click="removeProduction()">Delete</button>
            </div>
        </b-modal>
        <div v-show="productions && productions.length > 0">
            <div class="row justify-content-center">
                <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
            </div>
            <div class="row justify-content-center">
                <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./production.component.ts">
</script>
