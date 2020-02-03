<template>
    <div>
        <h2 id="page-heading">
            <span id="sys-dict-type-heading">Sys Dict Types</span>
            <router-link :to="{name: 'SysDictTypeCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-sys-dict-type">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span >
                    Create a new Sys Dict Type
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
        <div class="alert alert-warning" v-if="!isFetching && sysDictTypes && sysDictTypes.length === 0">
            <span>No sysDictTypes found</span>
        </div>
        <div class="table-responsive" v-if="sysDictTypes && sysDictTypes.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th v-on:click="changeOrder('id')"><span>ID</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('code')"><span>Code</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('name')"><span>Name</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('systemFlag')"><span>System Flag</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('status')"><span>Status</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('sort')"><span>Sort</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('description')"><span>Description</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('createTime')"><span>Create Time</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('createUser')"><span>Create User</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('updateTime')"><span>Update Time</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('updateUser')"><span>Update User</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="sysDictType in sysDictTypes"
                    :key="sysDictType.id">
                    <td>
                        <router-link :to="{name: 'SysDictTypeView', params: {sysDictTypeId: sysDictType.id}}">{{sysDictType.id}}</router-link>
                    </td>
                    <td>{{sysDictType.code}}</td>
                    <td>{{sysDictType.name}}</td>
                    <td>{{sysDictType.systemFlag}}</td>
                    <td>{{sysDictType.status}}</td>
                    <td>{{sysDictType.sort}}</td>
                    <td>{{sysDictType.description}}</td>
                    <td>{{sysDictType.createTime | formatDate}}</td>
                    <td>{{sysDictType.createUser}}</td>
                    <td>{{sysDictType.updateTime | formatDate}}</td>
                    <td>{{sysDictType.updateUser}}</td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'SysDictTypeView', params: {sysDictTypeId: sysDictType.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline">View</span>
                            </router-link>
                            <router-link :to="{name: 'SysDictTypeEdit', params: {sysDictTypeId: sysDictType.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(sysDictType)"
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
            <span slot="modal-title"><span id="vuwsmtApp.sysDictType.delete.question">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-sysDictType-heading" >Are you sure you want to delete this Sys Dict Type?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-sysDictType" v-on:click="removeSysDictType()">Delete</button>
            </div>
        </b-modal>
        <div v-show="sysDictTypes && sysDictTypes.length > 0">
            <div class="row justify-content-center">
                <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
            </div>
            <div class="row justify-content-center">
                <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./sys-dict-type.component.ts">
</script>
