<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="vuwsmtApp.sysDict.home.createOrEditLabel">Create or edit a SysDict</h2>
                <div>
                    <div class="form-group" v-if="sysDict.id">
                        <label for="id">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="sysDict.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="sys-dict-dicTypeId">Dic Type Id</label>
                        <input type="number" class="form-control" name="dicTypeId" id="sys-dict-dicTypeId"
                            :class="{'valid': !$v.sysDict.dicTypeId.$invalid, 'invalid': $v.sysDict.dicTypeId.$invalid }" v-model.number="$v.sysDict.dicTypeId.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="sys-dict-code">Code</label>
                        <input type="text" class="form-control" name="code" id="sys-dict-code"
                            :class="{'valid': !$v.sysDict.code.$invalid, 'invalid': $v.sysDict.code.$invalid }" v-model="$v.sysDict.code.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="sys-dict-name">Name</label>
                        <input type="text" class="form-control" name="name" id="sys-dict-name"
                            :class="{'valid': !$v.sysDict.name.$invalid, 'invalid': $v.sysDict.name.$invalid }" v-model="$v.sysDict.name.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="sys-dict-parentId">Parent Id</label>
                        <input type="number" class="form-control" name="parentId" id="sys-dict-parentId"
                            :class="{'valid': !$v.sysDict.parentId.$invalid, 'invalid': $v.sysDict.parentId.$invalid }" v-model.number="$v.sysDict.parentId.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="sys-dict-parentIds">Parent Ids</label>
                        <input type="text" class="form-control" name="parentIds" id="sys-dict-parentIds"
                            :class="{'valid': !$v.sysDict.parentIds.$invalid, 'invalid': $v.sysDict.parentIds.$invalid }" v-model="$v.sysDict.parentIds.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="sys-dict-status">Status</label>
                        <input type="text" class="form-control" name="status" id="sys-dict-status"
                            :class="{'valid': !$v.sysDict.status.$invalid, 'invalid': $v.sysDict.status.$invalid }" v-model="$v.sysDict.status.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="sys-dict-sort">Sort</label>
                        <input type="number" class="form-control" name="sort" id="sys-dict-sort"
                            :class="{'valid': !$v.sysDict.sort.$invalid, 'invalid': $v.sysDict.sort.$invalid }" v-model.number="$v.sysDict.sort.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="sys-dict-description">Description</label>
                        <input type="text" class="form-control" name="description" id="sys-dict-description"
                            :class="{'valid': !$v.sysDict.description.$invalid, 'invalid': $v.sysDict.description.$invalid }" v-model="$v.sysDict.description.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="sys-dict-createTime">Create Time</label>
                        <div class="d-flex">
                            <input id="sys-dict-createTime" type="datetime-local" class="form-control" name="createTime" :class="{'valid': !$v.sysDict.createTime.$invalid, 'invalid': $v.sysDict.createTime.$invalid }"
                            
                            :value="convertDateTimeFromServer($v.sysDict.createTime.$model)"
                            @change="updateInstantField('createTime', $event)"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="sys-dict-createUser">Create User</label>
                        <input type="number" class="form-control" name="createUser" id="sys-dict-createUser"
                            :class="{'valid': !$v.sysDict.createUser.$invalid, 'invalid': $v.sysDict.createUser.$invalid }" v-model.number="$v.sysDict.createUser.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="sys-dict-updateTime">Update Time</label>
                        <div class="d-flex">
                            <input id="sys-dict-updateTime" type="datetime-local" class="form-control" name="updateTime" :class="{'valid': !$v.sysDict.updateTime.$invalid, 'invalid': $v.sysDict.updateTime.$invalid }"
                            
                            :value="convertDateTimeFromServer($v.sysDict.updateTime.$model)"
                            @change="updateInstantField('updateTime', $event)"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="sys-dict-updateUser">Update User</label>
                        <input type="number" class="form-control" name="updateUser" id="sys-dict-updateUser"
                            :class="{'valid': !$v.sysDict.updateUser.$invalid, 'invalid': $v.sysDict.updateUser.$invalid }" v-model.number="$v.sysDict.updateUser.$model" />
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span>Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.sysDict.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span>Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./sys-dict-update.component.ts">
</script>
