<template>
    <div>
        <h2 id="page-heading">
            <span id="sys-operation-log-heading">Sys Operation Logs</span>
            <router-link :to="{name: 'SysOperationLogCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-sys-operation-log">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span >
                    Create a new Sys Operation Log
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
        <div class="alert alert-warning" v-if="!isFetching && sysOperationLogs && sysOperationLogs.length === 0">
            <span>No sysOperationLogs found</span>
        </div>
        <div class="table-responsive" v-if="sysOperationLogs && sysOperationLogs.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th v-on:click="changeOrder('id')"><span>ID</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('logType')"><span>Log Type</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('logName')"><span>Log Name</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('userId')"><span>User Id</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('className')"><span>Class Name</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('method')"><span>Method</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('succeed')"><span>Succeed</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('createTime')"><span>Create Time</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('message')"><span>Message</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="sysOperationLog in sysOperationLogs"
                    :key="sysOperationLog.id">
                    <td>
                        <router-link :to="{name: 'SysOperationLogView', params: {sysOperationLogId: sysOperationLog.id}}">{{sysOperationLog.id}}</router-link>
                    </td>
                    <td>{{sysOperationLog.logType}}</td>
                    <td>{{sysOperationLog.logName}}</td>
                    <td>{{sysOperationLog.userId}}</td>
                    <td>{{sysOperationLog.className}}</td>
                    <td>{{sysOperationLog.method}}</td>
                    <td>{{sysOperationLog.succeed}}</td>
                    <td>{{sysOperationLog.createTime | formatDate}}</td>
                    <td>{{sysOperationLog.message}}</td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'SysOperationLogView', params: {sysOperationLogId: sysOperationLog.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline">View</span>
                            </router-link>
                            <router-link :to="{name: 'SysOperationLogEdit', params: {sysOperationLogId: sysOperationLog.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(sysOperationLog)"
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
            <span slot="modal-title"><span id="vuwsmtApp.sysOperationLog.delete.question">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-sysOperationLog-heading" >Are you sure you want to delete this Sys Operation Log?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-sysOperationLog" v-on:click="removeSysOperationLog()">Delete</button>
            </div>
        </b-modal>
        <div v-show="sysOperationLogs && sysOperationLogs.length > 0">
            <div class="row justify-content-center">
                <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
            </div>
            <div class="row justify-content-center">
                <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./sys-operation-log.component.ts">
</script>
