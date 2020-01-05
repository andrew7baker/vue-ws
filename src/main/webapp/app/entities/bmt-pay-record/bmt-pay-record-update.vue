<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="vuwsmtApp.bmtPayRecord.home.createOrEditLabel">Create or edit a BmtPayRecord</h2>
                <div>
                    <div class="form-group" v-if="bmtPayRecord.id">
                        <label for="id">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="bmtPayRecord.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="bmt-pay-record-payTime">Pay Time</label>
                        <div class="d-flex">
                            <input id="bmt-pay-record-payTime" type="datetime-local" class="form-control" name="payTime" :class="{'valid': !$v.bmtPayRecord.payTime.$invalid, 'invalid': $v.bmtPayRecord.payTime.$invalid }"
                            
                            :value="convertDateTimeFromServer($v.bmtPayRecord.payTime.$model)"
                            @change="updateInstantField('payTime', $event)"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="bmt-pay-record-payPersonId">Pay Person Id</label>
                        <input type="text" class="form-control" name="payPersonId" id="bmt-pay-record-payPersonId"
                            :class="{'valid': !$v.bmtPayRecord.payPersonId.$invalid, 'invalid': $v.bmtPayRecord.payPersonId.$invalid }" v-model="$v.bmtPayRecord.payPersonId.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="bmt-pay-record-payPersonName">Pay Person Name</label>
                        <input type="text" class="form-control" name="payPersonName" id="bmt-pay-record-payPersonName"
                            :class="{'valid': !$v.bmtPayRecord.payPersonName.$invalid, 'invalid': $v.bmtPayRecord.payPersonName.$invalid }" v-model="$v.bmtPayRecord.payPersonName.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="bmt-pay-record-payAmount">Pay Amount</label>
                        <input type="number" class="form-control" name="payAmount" id="bmt-pay-record-payAmount"
                            :class="{'valid': !$v.bmtPayRecord.payAmount.$invalid, 'invalid': $v.bmtPayRecord.payAmount.$invalid }" v-model.number="$v.bmtPayRecord.payAmount.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label"  for="bmt-pay-record-bmtChangCi">Bmt Chang Ci</label>
                        <select class="form-control" id="bmt-pay-record-bmtChangCi" name="bmtChangCi" v-model="bmtPayRecord.bmtChangCi">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="bmtPayRecord.bmtChangCi && bmtChangCiOption.id === bmtPayRecord.bmtChangCi.id ? bmtPayRecord.bmtChangCi : bmtChangCiOption" v-for="bmtChangCiOption in bmtChangCis" :key="bmtChangCiOption.id">{{bmtChangCiOption.id}}</option>
                        </select>
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span>Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.bmtPayRecord.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span>Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./bmt-pay-record-update.component.ts">
</script>
