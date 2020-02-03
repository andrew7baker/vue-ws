<template>
    <div>
        <h2 id="page-heading">
            <span id="sys-config-heading">Sys Configs</span>
            <router-link :to="{name: 'SysConfigCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-sys-config">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span >
                    Create a new Sys Config
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
        <div class="alert alert-warning" v-if="!isFetching && sysConfigs && sysConfigs.length === 0">
            <span>No sysConfigs found</span>
        </div>
        <div class="table-responsive" v-if="sysConfigs && sysConfigs.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th v-on:click="changeOrder('id')"><span>ID</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('name')"><span>Name</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('code')"><span>Code</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('dictFlag')"><span>Dict Flag</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('dictTypeId')"><span>Dict Type Id</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('value')"><span>Value</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('remark')"><span>Remark</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('createTime')"><span>Create Time</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('createUser')"><span>Create User</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('updateTime')"><span>Update Time</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('updateUser')"><span>Update User</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="sysConfig in sysConfigs"
                    :key="sysConfig.id">
                    <td>
                        <router-link :to="{name: 'SysConfigView', params: {sysConfigId: sysConfig.id}}">{{sysConfig.id}}</router-link>
                    </td>
                    <td>{{sysConfig.name}}</td>
                    <td>{{sysConfig.code}}</td>
                    <td>{{sysConfig.dictFlag}}</td>
                    <td>{{sysConfig.dictTypeId}}</td>
                    <td>{{sysConfig.value}}</td>
                    <td>{{sysConfig.remark}}</td>
                    <td>{{sysConfig.createTime | formatDate}}</td>
                    <td>{{sysConfig.createUser}}</td>
                    <td>{{sysConfig.updateTime | formatDate}}</td>
                    <td>{{sysConfig.updateUser}}</td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'SysConfigView', params: {sysConfigId: sysConfig.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline">View</span>
                            </router-link>
                            <router-link :to="{name: 'SysConfigEdit', params: {sysConfigId: sysConfig.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(sysConfig)"
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
            <span slot="modal-title"><span id="vuwsmtApp.sysConfig.delete.question">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-sysConfig-heading" >Are you sure you want to delete this Sys Config?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-sysConfig" v-on:click="removeSysConfig()">Delete</button>
            </div>
        </b-modal>
        <div v-show="sysConfigs && sysConfigs.length > 0">
            <div class="row justify-content-center">
                <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
            </div>
            <div class="row justify-content-center">
                <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./sys-config.component.ts">
</script>
