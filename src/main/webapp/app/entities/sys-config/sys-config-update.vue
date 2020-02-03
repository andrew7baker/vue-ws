<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="vuwsmtApp.sysConfig.home.createOrEditLabel">Create or edit a SysConfig</h2>
                <div>
                    <div class="form-group" v-if="sysConfig.id">
                        <label for="id">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="sysConfig.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="sys-config-name">Name</label>
                        <input type="text" class="form-control" name="name" id="sys-config-name"
                            :class="{'valid': !$v.sysConfig.name.$invalid, 'invalid': $v.sysConfig.name.$invalid }" v-model="$v.sysConfig.name.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="sys-config-code">Code</label>
                        <input type="text" class="form-control" name="code" id="sys-config-code"
                            :class="{'valid': !$v.sysConfig.code.$invalid, 'invalid': $v.sysConfig.code.$invalid }" v-model="$v.sysConfig.code.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="sys-config-dictFlag">Dict Flag</label>
                        <input type="text" class="form-control" name="dictFlag" id="sys-config-dictFlag"
                            :class="{'valid': !$v.sysConfig.dictFlag.$invalid, 'invalid': $v.sysConfig.dictFlag.$invalid }" v-model="$v.sysConfig.dictFlag.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="sys-config-dictTypeId">Dict Type Id</label>
                        <input type="number" class="form-control" name="dictTypeId" id="sys-config-dictTypeId"
                            :class="{'valid': !$v.sysConfig.dictTypeId.$invalid, 'invalid': $v.sysConfig.dictTypeId.$invalid }" v-model.number="$v.sysConfig.dictTypeId.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="sys-config-value">Value</label>
                        <input type="text" class="form-control" name="value" id="sys-config-value"
                            :class="{'valid': !$v.sysConfig.value.$invalid, 'invalid': $v.sysConfig.value.$invalid }" v-model="$v.sysConfig.value.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="sys-config-remark">Remark</label>
                        <input type="text" class="form-control" name="remark" id="sys-config-remark"
                            :class="{'valid': !$v.sysConfig.remark.$invalid, 'invalid': $v.sysConfig.remark.$invalid }" v-model="$v.sysConfig.remark.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="sys-config-createTime">Create Time</label>
                        <div class="d-flex">
                            <input id="sys-config-createTime" type="datetime-local" class="form-control" name="createTime" :class="{'valid': !$v.sysConfig.createTime.$invalid, 'invalid': $v.sysConfig.createTime.$invalid }"
                            
                            :value="convertDateTimeFromServer($v.sysConfig.createTime.$model)"
                            @change="updateInstantField('createTime', $event)"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="sys-config-createUser">Create User</label>
                        <input type="number" class="form-control" name="createUser" id="sys-config-createUser"
                            :class="{'valid': !$v.sysConfig.createUser.$invalid, 'invalid': $v.sysConfig.createUser.$invalid }" v-model.number="$v.sysConfig.createUser.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="sys-config-updateTime">Update Time</label>
                        <div class="d-flex">
                            <input id="sys-config-updateTime" type="datetime-local" class="form-control" name="updateTime" :class="{'valid': !$v.sysConfig.updateTime.$invalid, 'invalid': $v.sysConfig.updateTime.$invalid }"
                            
                            :value="convertDateTimeFromServer($v.sysConfig.updateTime.$model)"
                            @change="updateInstantField('updateTime', $event)"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="sys-config-updateUser">Update User</label>
                        <input type="number" class="form-control" name="updateUser" id="sys-config-updateUser"
                            :class="{'valid': !$v.sysConfig.updateUser.$invalid, 'invalid': $v.sysConfig.updateUser.$invalid }" v-model.number="$v.sysConfig.updateUser.$model" />
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span>Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.sysConfig.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span>Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./sys-config-update.component.ts">
</script>
