<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="vuwsmtApp.production.home.createOrEditLabel">Create or edit a Production</h2>
                <div>
                    <div class="form-group" v-if="production.id">
                        <label for="id">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="production.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="production-version">Version</label>
                        <input type="text" class="form-control" name="version" id="production-version"
                            :class="{'valid': !$v.production.version.$invalid, 'invalid': $v.production.version.$invalid }" v-model="$v.production.version.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="production-powerTime">Power Time</label>
                        <input type="number" class="form-control" name="powerTime" id="production-powerTime"
                            :class="{'valid': !$v.production.powerTime.$invalid, 'invalid': $v.production.powerTime.$invalid }" v-model.number="$v.production.powerTime.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="production-placeTime">Place Time</label>
                        <input type="number" class="form-control" name="placeTime" id="production-placeTime"
                            :class="{'valid': !$v.production.placeTime.$invalid, 'invalid': $v.production.placeTime.$invalid }" v-model.number="$v.production.placeTime.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="production-waitTime">Wait Time</label>
                        <input type="number" class="form-control" name="waitTime" id="production-waitTime"
                            :class="{'valid': !$v.production.waitTime.$invalid, 'invalid': $v.production.waitTime.$invalid }" v-model.number="$v.production.waitTime.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="production-runTime">Run Time</label>
                        <input type="number" class="form-control" name="runTime" id="production-runTime"
                            :class="{'valid': !$v.production.runTime.$invalid, 'invalid': $v.production.runTime.$invalid }" v-model.number="$v.production.runTime.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="production-idleTime">Idle Time</label>
                        <input type="number" class="form-control" name="idleTime" id="production-idleTime"
                            :class="{'valid': !$v.production.idleTime.$invalid, 'invalid': $v.production.idleTime.$invalid }" v-model.number="$v.production.idleTime.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="production-inWaitTime">In Wait Time</label>
                        <input type="number" class="form-control" name="inWaitTime" id="production-inWaitTime"
                            :class="{'valid': !$v.production.inWaitTime.$invalid, 'invalid': $v.production.inWaitTime.$invalid }" v-model.number="$v.production.inWaitTime.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="production-wrongStopTime">Wrong Stop Time</label>
                        <input type="number" class="form-control" name="wrongStopTime" id="production-wrongStopTime"
                            :class="{'valid': !$v.production.wrongStopTime.$invalid, 'invalid': $v.production.wrongStopTime.$invalid }" v-model.number="$v.production.wrongStopTime.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="production-errorStopTIme">Error Stop T Ime</label>
                        <input type="number" class="form-control" name="errorStopTIme" id="production-errorStopTIme"
                            :class="{'valid': !$v.production.errorStopTIme.$invalid, 'invalid': $v.production.errorStopTIme.$invalid }" v-model.number="$v.production.errorStopTIme.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="production-wrongStopCount">Wrong Stop Count</label>
                        <input type="number" class="form-control" name="wrongStopCount" id="production-wrongStopCount"
                            :class="{'valid': !$v.production.wrongStopCount.$invalid, 'invalid': $v.production.wrongStopCount.$invalid }" v-model.number="$v.production.wrongStopCount.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="production-errorStopCount">Error Stop Count</label>
                        <input type="number" class="form-control" name="errorStopCount" id="production-errorStopCount"
                            :class="{'valid': !$v.production.errorStopCount.$invalid, 'invalid': $v.production.errorStopCount.$invalid }" v-model.number="$v.production.errorStopCount.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="production-panelInCount">Panel In Count</label>
                        <input type="number" class="form-control" name="panelInCount" id="production-panelInCount"
                            :class="{'valid': !$v.production.panelInCount.$invalid, 'invalid': $v.production.panelInCount.$invalid }" v-model.number="$v.production.panelInCount.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="production-panelCount">Panel Count</label>
                        <input type="number" class="form-control" name="panelCount" id="production-panelCount"
                            :class="{'valid': !$v.production.panelCount.$invalid, 'invalid': $v.production.panelCount.$invalid }" v-model.number="$v.production.panelCount.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="production-pCBCount">P CB Count</label>
                        <input type="number" class="form-control" name="pCBCount" id="production-pCBCount"
                            :class="{'valid': !$v.production.pCBCount.$invalid, 'invalid': $v.production.pCBCount.$invalid }" v-model.number="$v.production.pCBCount.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="production-errorPcb">Error Pcb</label>
                        <input type="number" class="form-control" name="errorPcb" id="production-errorPcb"
                            :class="{'valid': !$v.production.errorPcb.$invalid, 'invalid': $v.production.errorPcb.$invalid }" v-model.number="$v.production.errorPcb.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="production-skipPCB">Skip PCB</label>
                        <input type="number" class="form-control" name="skipPCB" id="production-skipPCB"
                            :class="{'valid': !$v.production.skipPCB.$invalid, 'invalid': $v.production.skipPCB.$invalid }" v-model.number="$v.production.skipPCB.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="production-operationRate">Operation Rate</label>
                        <input type="number" class="form-control" name="operationRate" id="production-operationRate"
                            :class="{'valid': !$v.production.operationRate.$invalid, 'invalid': $v.production.operationRate.$invalid }" v-model.number="$v.production.operationRate.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="production-placementRate">Placement Rate</label>
                        <input type="number" class="form-control" name="placementRate" id="production-placementRate"
                            :class="{'valid': !$v.production.placementRate.$invalid, 'invalid': $v.production.placementRate.$invalid }" v-model.number="$v.production.placementRate.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="production-meanTime">Mean Time</label>
                        <input type="number" class="form-control" name="meanTime" id="production-meanTime"
                            :class="{'valid': !$v.production.meanTime.$invalid, 'invalid': $v.production.meanTime.$invalid }" v-model.number="$v.production.meanTime.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="production-realTime">Real Time</label>
                        <input type="number" class="form-control" name="realTime" id="production-realTime"
                            :class="{'valid': !$v.production.realTime.$invalid, 'invalid': $v.production.realTime.$invalid }" v-model.number="$v.production.realTime.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="production-transferTime">Transfer Time</label>
                        <input type="number" class="form-control" name="transferTime" id="production-transferTime"
                            :class="{'valid': !$v.production.transferTime.$invalid, 'invalid': $v.production.transferTime.$invalid }" v-model.number="$v.production.transferTime.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="production-placeCount">Place Count</label>
                        <input type="number" class="form-control" name="placeCount" id="production-placeCount"
                            :class="{'valid': !$v.production.placeCount.$invalid, 'invalid': $v.production.placeCount.$invalid }" v-model.number="$v.production.placeCount.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="production-theEfficiency">The Efficiency</label>
                        <input type="number" class="form-control" name="theEfficiency" id="production-theEfficiency"
                            :class="{'valid': !$v.production.theEfficiency.$invalid, 'invalid': $v.production.theEfficiency.$invalid }" v-model.number="$v.production.theEfficiency.$model" />
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span>Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.production.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span>Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./production-update.component.ts">
</script>
