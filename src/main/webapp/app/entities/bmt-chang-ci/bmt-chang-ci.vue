<template>
    <div>
        <h2 id="page-heading">
            <span id="bmt-chang-ci-heading">Bmt Chang Cis</span>
            <router-link :to="{name: 'BmtChangCiCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-bmt-chang-ci">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span >
                    Create a new Bmt Chang Ci
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
        <div class="alert alert-warning" v-if="!isFetching && bmtChangCis && bmtChangCis.length === 0">
            <span>No bmtChangCis found</span>
        </div>
        <div class="table-responsive" v-if="bmtChangCis && bmtChangCis.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th v-on:click="changeOrder('id')"><span>ID</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('name')"><span>Name</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('weekDay')"><span>Week Day</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('timeBegin')"><span>Time Begin</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('timeEnd')"><span>Time End</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('owner')"><span>Owner</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('userId')"><span>User Id</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="bmtChangCi in bmtChangCis"
                    :key="bmtChangCi.id">
                    <td>
                        <router-link :to="{name: 'BmtChangCiView', params: {bmtChangCiId: bmtChangCi.id}}">{{bmtChangCi.id}}</router-link>
                    </td>
                    <td>{{bmtChangCi.name}}</td>
                    <td>{{bmtChangCi.weekDay}}</td>
                    <td>{{bmtChangCi.timeBegin | formatDate}}</td>
                    <td>{{bmtChangCi.timeEnd | formatDate}}</td>
                    <td>{{bmtChangCi.owner}}</td>
                    <td>{{bmtChangCi.userId}}</td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'BmtChangCiView', params: {bmtChangCiId: bmtChangCi.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline">View</span>
                            </router-link>
                            <router-link :to="{name: 'BmtChangCiEdit', params: {bmtChangCiId: bmtChangCi.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(bmtChangCi)"
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
            <span slot="modal-title"><span id="vuwsmtApp.bmtChangCi.delete.question">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-bmtChangCi-heading" >Are you sure you want to delete this Bmt Chang Ci?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-bmtChangCi" v-on:click="removeBmtChangCi()">Delete</button>
            </div>
        </b-modal>
        <div v-show="bmtChangCis && bmtChangCis.length > 0">
            <div class="row justify-content-center">
                <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
            </div>
            <div class="row justify-content-center">
                <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./bmt-chang-ci.component.ts">
</script>
