<template>
    <div>
        <h2 id="page-heading">
            <span id="sys-relation-type-heading">Sys Relation Types</span>
            <router-link :to="{name: 'SysRelationTypeCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-sys-relation-type">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span >
                    Create a new Sys Relation Type
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
        <div class="alert alert-warning" v-if="!isFetching && sysRelationTypes && sysRelationTypes.length === 0">
            <span>No sysRelationTypes found</span>
        </div>
        <div class="table-responsive" v-if="sysRelationTypes && sysRelationTypes.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th v-on:click="changeOrder('id')"><span>ID</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('typeCode')"><span>Type Code</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('typedName')"><span>Typed Name</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="sysRelationType in sysRelationTypes"
                    :key="sysRelationType.id">
                    <td>
                        <router-link :to="{name: 'SysRelationTypeView', params: {sysRelationTypeId: sysRelationType.id}}">{{sysRelationType.id}}</router-link>
                    </td>
                    <td>{{sysRelationType.typeCode}}</td>
                    <td>{{sysRelationType.typedName}}</td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'SysRelationTypeView', params: {sysRelationTypeId: sysRelationType.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline">View</span>
                            </router-link>
                            <router-link :to="{name: 'SysRelationTypeEdit', params: {sysRelationTypeId: sysRelationType.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(sysRelationType)"
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
            <span slot="modal-title"><span id="vuwsmtApp.sysRelationType.delete.question">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-sysRelationType-heading" >Are you sure you want to delete this Sys Relation Type?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-sysRelationType" v-on:click="removeSysRelationType()">Delete</button>
            </div>
        </b-modal>
        <div v-show="sysRelationTypes && sysRelationTypes.length > 0">
            <div class="row justify-content-center">
                <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
            </div>
            <div class="row justify-content-center">
                <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./sys-relation-type.component.ts">
</script>
