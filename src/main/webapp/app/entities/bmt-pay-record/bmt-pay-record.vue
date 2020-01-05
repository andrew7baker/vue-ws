<template>
    <div>
        <h2 id="page-heading">
            <span id="bmt-pay-record-heading">Bmt Pay Records</span>
            <router-link :to="{name: 'BmtPayRecordCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-bmt-pay-record">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span >
                    Create a new Bmt Pay Record
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
        <div class="alert alert-warning" v-if="!isFetching && bmtPayRecords && bmtPayRecords.length === 0">
            <span>No bmtPayRecords found</span>
        </div>
        <div class="table-responsive" v-if="bmtPayRecords && bmtPayRecords.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th v-on:click="changeOrder('id')"><span>ID</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('payTime')"><span>Pay Time</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('payPersonId')"><span>Pay Person Id</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('payPersonName')"><span>Pay Person Name</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('payAmount')"><span>Pay Amount</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('bmtChangCi.id')"><span>Bmt Chang Ci</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="bmtPayRecord in bmtPayRecords"
                    :key="bmtPayRecord.id">
                    <td>
                        <router-link :to="{name: 'BmtPayRecordView', params: {bmtPayRecordId: bmtPayRecord.id}}">{{bmtPayRecord.id}}</router-link>
                    </td>
                    <td>{{bmtPayRecord.payTime | formatDate}}</td>
                    <td>{{bmtPayRecord.payPersonId}}</td>
                    <td>{{bmtPayRecord.payPersonName}}</td>
                    <td>{{bmtPayRecord.payAmount}}</td>
                    <td>
                        <div v-if="bmtPayRecord.bmtChangCi">
                            <router-link :to="{name: 'BmtChangCiView', params: {bmtChangCiId: bmtPayRecord.bmtChangCi.id}}">{{bmtPayRecord.bmtChangCi.id}}</router-link>
                        </div>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'BmtPayRecordView', params: {bmtPayRecordId: bmtPayRecord.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline">View</span>
                            </router-link>
                            <router-link :to="{name: 'BmtPayRecordEdit', params: {bmtPayRecordId: bmtPayRecord.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(bmtPayRecord)"
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
            <span slot="modal-title"><span id="vuwsmtApp.bmtPayRecord.delete.question">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-bmtPayRecord-heading" >Are you sure you want to delete this Bmt Pay Record?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-bmtPayRecord" v-on:click="removeBmtPayRecord()">Delete</button>
            </div>
        </b-modal>
        <div v-show="bmtPayRecords && bmtPayRecords.length > 0">
            <div class="row justify-content-center">
                <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
            </div>
            <div class="row justify-content-center">
                <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./bmt-pay-record.component.ts">
</script>
