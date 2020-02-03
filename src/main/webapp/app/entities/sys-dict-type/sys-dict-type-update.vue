<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="vuwsmtApp.sysDictType.home.createOrEditLabel">Create or edit a SysDictType</h2>
                <div>
                    <div class="form-group" v-if="sysDictType.id">
                        <label for="id">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="sysDictType.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="sys-dict-type-code">Code</label>
                        <input type="text" class="form-control" name="code" id="sys-dict-type-code"
                            :class="{'valid': !$v.sysDictType.code.$invalid, 'invalid': $v.sysDictType.code.$invalid }" v-model="$v.sysDictType.code.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="sys-dict-type-name">Name</label>
                        <input type="text" class="form-control" name="name" id="sys-dict-type-name"
                            :class="{'valid': !$v.sysDictType.name.$invalid, 'invalid': $v.sysDictType.name.$invalid }" v-model="$v.sysDictType.name.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="sys-dict-type-systemFlag">System Flag</label>
                        <input type="text" class="form-control" name="systemFlag" id="sys-dict-type-systemFlag"
                            :class="{'valid': !$v.sysDictType.systemFlag.$invalid, 'invalid': $v.sysDictType.systemFlag.$invalid }" v-model="$v.sysDictType.systemFlag.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="sys-dict-type-status">Status</label>
                        <input type="text" class="form-control" name="status" id="sys-dict-type-status"
                            :class="{'valid': !$v.sysDictType.status.$invalid, 'invalid': $v.sysDictType.status.$invalid }" v-model="$v.sysDictType.status.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="sys-dict-type-sort">Sort</label>
                        <input type="number" class="form-control" name="sort" id="sys-dict-type-sort"
                            :class="{'valid': !$v.sysDictType.sort.$invalid, 'invalid': $v.sysDictType.sort.$invalid }" v-model.number="$v.sysDictType.sort.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="sys-dict-type-description">Description</label>
                        <input type="text" class="form-control" name="description" id="sys-dict-type-description"
                            :class="{'valid': !$v.sysDictType.description.$invalid, 'invalid': $v.sysDictType.description.$invalid }" v-model="$v.sysDictType.description.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="sys-dict-type-createTime">Create Time</label>
                        <div class="d-flex">
                            <input id="sys-dict-type-createTime" type="datetime-local" class="form-control" name="createTime" :class="{'valid': !$v.sysDictType.createTime.$invalid, 'invalid': $v.sysDictType.createTime.$invalid }"
                            
                            :value="convertDateTimeFromServer($v.sysDictType.createTime.$model)"
                            @change="updateInstantField('createTime', $event)"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="sys-dict-type-createUser">Create User</label>
                        <input type="number" class="form-control" name="createUser" id="sys-dict-type-createUser"
                            :class="{'valid': !$v.sysDictType.createUser.$invalid, 'invalid': $v.sysDictType.createUser.$invalid }" v-model.number="$v.sysDictType.createUser.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="sys-dict-type-updateTime">Update Time</label>
                        <div class="d-flex">
                            <input id="sys-dict-type-updateTime" type="datetime-local" class="form-control" name="updateTime" :class="{'valid': !$v.sysDictType.updateTime.$invalid, 'invalid': $v.sysDictType.updateTime.$invalid }"
                            
                            :value="convertDateTimeFromServer($v.sysDictType.updateTime.$model)"
                            @change="updateInstantField('updateTime', $event)"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="sys-dict-type-updateUser">Update User</label>
                        <input type="number" class="form-control" name="updateUser" id="sys-dict-type-updateUser"
                            :class="{'valid': !$v.sysDictType.updateUser.$invalid, 'invalid': $v.sysDictType.updateUser.$invalid }" v-model.number="$v.sysDictType.updateUser.$model" />
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span>Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.sysDictType.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span>Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./sys-dict-type-update.component.ts">
</script>
