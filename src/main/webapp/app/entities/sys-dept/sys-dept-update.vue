<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="vuwsmtApp.sysDept.home.createOrEditLabel">Create or edit a SysDept</h2>
                <div>
                    <div class="form-group" v-if="sysDept.id">
                        <label for="id">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="sysDept.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="sys-dept-parentId">Parent Id</label>
                        <input type="number" class="form-control" name="parentId" id="sys-dept-parentId"
                            :class="{'valid': !$v.sysDept.parentId.$invalid, 'invalid': $v.sysDept.parentId.$invalid }" v-model.number="$v.sysDept.parentId.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="sys-dept-parentIds">Parent Ids</label>
                        <input type="text" class="form-control" name="parentIds" id="sys-dept-parentIds"
                            :class="{'valid': !$v.sysDept.parentIds.$invalid, 'invalid': $v.sysDept.parentIds.$invalid }" v-model="$v.sysDept.parentIds.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="sys-dept-simpleName">Simple Name</label>
                        <input type="text" class="form-control" name="simpleName" id="sys-dept-simpleName"
                            :class="{'valid': !$v.sysDept.simpleName.$invalid, 'invalid': $v.sysDept.simpleName.$invalid }" v-model="$v.sysDept.simpleName.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="sys-dept-fullName">Full Name</label>
                        <input type="text" class="form-control" name="fullName" id="sys-dept-fullName"
                            :class="{'valid': !$v.sysDept.fullName.$invalid, 'invalid': $v.sysDept.fullName.$invalid }" v-model="$v.sysDept.fullName.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="sys-dept-description">Description</label>
                        <input type="text" class="form-control" name="description" id="sys-dept-description"
                            :class="{'valid': !$v.sysDept.description.$invalid, 'invalid': $v.sysDept.description.$invalid }" v-model="$v.sysDept.description.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="sys-dept-version">Version</label>
                        <input type="text" class="form-control" name="version" id="sys-dept-version"
                            :class="{'valid': !$v.sysDept.version.$invalid, 'invalid': $v.sysDept.version.$invalid }" v-model="$v.sysDept.version.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="sys-dept-status">Status</label>
                        <input type="text" class="form-control" name="status" id="sys-dept-status"
                            :class="{'valid': !$v.sysDept.status.$invalid, 'invalid': $v.sysDept.status.$invalid }" v-model="$v.sysDept.status.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="sys-dept-sort">Sort</label>
                        <input type="number" class="form-control" name="sort" id="sys-dept-sort"
                            :class="{'valid': !$v.sysDept.sort.$invalid, 'invalid': $v.sysDept.sort.$invalid }" v-model.number="$v.sysDept.sort.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="sys-dept-createTime">Create Time</label>
                        <div class="d-flex">
                            <input id="sys-dept-createTime" type="datetime-local" class="form-control" name="createTime" :class="{'valid': !$v.sysDept.createTime.$invalid, 'invalid': $v.sysDept.createTime.$invalid }"
                            
                            :value="convertDateTimeFromServer($v.sysDept.createTime.$model)"
                            @change="updateInstantField('createTime', $event)"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="sys-dept-createUser">Create User</label>
                        <input type="number" class="form-control" name="createUser" id="sys-dept-createUser"
                            :class="{'valid': !$v.sysDept.createUser.$invalid, 'invalid': $v.sysDept.createUser.$invalid }" v-model.number="$v.sysDept.createUser.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="sys-dept-updateTime">Update Time</label>
                        <div class="d-flex">
                            <input id="sys-dept-updateTime" type="datetime-local" class="form-control" name="updateTime" :class="{'valid': !$v.sysDept.updateTime.$invalid, 'invalid': $v.sysDept.updateTime.$invalid }"
                            
                            :value="convertDateTimeFromServer($v.sysDept.updateTime.$model)"
                            @change="updateInstantField('updateTime', $event)"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="sys-dept-updateUser">Update User</label>
                        <input type="number" class="form-control" name="updateUser" id="sys-dept-updateUser"
                            :class="{'valid': !$v.sysDept.updateUser.$invalid, 'invalid': $v.sysDept.updateUser.$invalid }" v-model.number="$v.sysDept.updateUser.$model" />
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span>Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.sysDept.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span>Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./sys-dept-update.component.ts">
</script>
