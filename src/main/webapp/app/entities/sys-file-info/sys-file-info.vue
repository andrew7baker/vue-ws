<template>
    <div>
        <h2 id="page-heading">
            <span id="sys-file-info-heading">Sys File Infos</span>
            <router-link :to="{name: 'SysFileInfoCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-sys-file-info">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span >
                    Create a new Sys File Info
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
        <div class="alert alert-warning" v-if="!isFetching && sysFileInfos && sysFileInfos.length === 0">
            <span>No sysFileInfos found</span>
        </div>
        <div class="table-responsive" v-if="sysFileInfos && sysFileInfos.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th v-on:click="changeOrder('id')"><span>ID</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('machineCode')"><span>Machine Code</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('fileBucket')"><span>File Bucket</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('fileName')"><span>File Name</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('fileSuffix')"><span>File Suffix</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('fileSizeKb')"><span>File Size Kb</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('finalName')"><span>Final Name</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('filePath')"><span>File Path</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('createTime')"><span>Create Time</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('createUser')"><span>Create User</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('updateTime')"><span>Update Time</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('updateUser')"><span>Update User</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="sysFileInfo in sysFileInfos"
                    :key="sysFileInfo.id">
                    <td>
                        <router-link :to="{name: 'SysFileInfoView', params: {sysFileInfoId: sysFileInfo.id}}">{{sysFileInfo.id}}</router-link>
                    </td>
                    <td>{{sysFileInfo.machineCode}}</td>
                    <td>{{sysFileInfo.fileBucket}}</td>
                    <td>{{sysFileInfo.fileName}}</td>
                    <td>{{sysFileInfo.fileSuffix}}</td>
                    <td>{{sysFileInfo.fileSizeKb}}</td>
                    <td>{{sysFileInfo.finalName}}</td>
                    <td>{{sysFileInfo.filePath}}</td>
                    <td>{{sysFileInfo.createTime | formatDate}}</td>
                    <td>{{sysFileInfo.createUser}}</td>
                    <td>{{sysFileInfo.updateTime | formatDate}}</td>
                    <td>{{sysFileInfo.updateUser}}</td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'SysFileInfoView', params: {sysFileInfoId: sysFileInfo.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline">View</span>
                            </router-link>
                            <router-link :to="{name: 'SysFileInfoEdit', params: {sysFileInfoId: sysFileInfo.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(sysFileInfo)"
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
            <span slot="modal-title"><span id="vuwsmtApp.sysFileInfo.delete.question">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-sysFileInfo-heading" >Are you sure you want to delete this Sys File Info?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-sysFileInfo" v-on:click="removeSysFileInfo()">Delete</button>
            </div>
        </b-modal>
        <div v-show="sysFileInfos && sysFileInfos.length > 0">
            <div class="row justify-content-center">
                <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
            </div>
            <div class="row justify-content-center">
                <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./sys-file-info.component.ts">
</script>
