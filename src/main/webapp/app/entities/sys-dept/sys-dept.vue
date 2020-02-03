<template>
    <div>
        <h2 id="page-heading">
            <span id="sys-dept-heading">Sys Depts</span>
            <router-link :to="{name: 'SysDeptCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-sys-dept">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span >
                    Create a new Sys Dept
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
        <div class="alert alert-warning" v-if="!isFetching && sysDepts && sysDepts.length === 0">
            <span>No sysDepts found</span>
        </div>
        <div class="table-responsive" v-if="sysDepts && sysDepts.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th v-on:click="changeOrder('id')"><span>ID</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('parentId')"><span>Parent Id</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('parentIds')"><span>Parent Ids</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('simpleName')"><span>Simple Name</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('fullName')"><span>Full Name</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('description')"><span>Description</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('version')"><span>Version</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('status')"><span>Status</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('sort')"><span>Sort</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('createTime')"><span>Create Time</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('createUser')"><span>Create User</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('updateTime')"><span>Update Time</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th v-on:click="changeOrder('updateUser')"><span>Update User</span> <font-awesome-icon icon="sort"></font-awesome-icon></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="sysDept in sysDepts"
                    :key="sysDept.id">
                    <td>
                        <router-link :to="{name: 'SysDeptView', params: {sysDeptId: sysDept.id}}">{{sysDept.id}}</router-link>
                    </td>
                    <td>{{sysDept.parentId}}</td>
                    <td>{{sysDept.parentIds}}</td>
                    <td>{{sysDept.simpleName}}</td>
                    <td>{{sysDept.fullName}}</td>
                    <td>{{sysDept.description}}</td>
                    <td>{{sysDept.version}}</td>
                    <td>{{sysDept.status}}</td>
                    <td>{{sysDept.sort}}</td>
                    <td>{{sysDept.createTime | formatDate}}</td>
                    <td>{{sysDept.createUser}}</td>
                    <td>{{sysDept.updateTime | formatDate}}</td>
                    <td>{{sysDept.updateUser}}</td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'SysDeptView', params: {sysDeptId: sysDept.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline">View</span>
                            </router-link>
                            <router-link :to="{name: 'SysDeptEdit', params: {sysDeptId: sysDept.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(sysDept)"
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
            <span slot="modal-title"><span id="vuwsmtApp.sysDept.delete.question">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-sysDept-heading" >Are you sure you want to delete this Sys Dept?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-sysDept" v-on:click="removeSysDept()">Delete</button>
            </div>
        </b-modal>
        <div v-show="sysDepts && sysDepts.length > 0">
            <div class="row justify-content-center">
                <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
            </div>
            <div class="row justify-content-center">
                <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./sys-dept.component.ts">
</script>
