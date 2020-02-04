<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="vuwsmtApp.sysOperationLog.home.createOrEditLabel">Create or edit a SysOperationLog</h2>
                <div>
                    <div class="form-group" v-if="sysOperationLog.id">
                        <label for="id">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="sysOperationLog.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="sys-operation-log-logType">Log Type</label>
                        <input type="text" class="form-control" name="logType" id="sys-operation-log-logType"
                            :class="{'valid': !$v.sysOperationLog.logType.$invalid, 'invalid': $v.sysOperationLog.logType.$invalid }" v-model="$v.sysOperationLog.logType.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="sys-operation-log-logName">Log Name</label>
                        <input type="text" class="form-control" name="logName" id="sys-operation-log-logName"
                            :class="{'valid': !$v.sysOperationLog.logName.$invalid, 'invalid': $v.sysOperationLog.logName.$invalid }" v-model="$v.sysOperationLog.logName.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="sys-operation-log-userId">User Id</label>
                        <input type="number" class="form-control" name="userId" id="sys-operation-log-userId"
                            :class="{'valid': !$v.sysOperationLog.userId.$invalid, 'invalid': $v.sysOperationLog.userId.$invalid }" v-model.number="$v.sysOperationLog.userId.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="sys-operation-log-className">Class Name</label>
                        <input type="text" class="form-control" name="className" id="sys-operation-log-className"
                            :class="{'valid': !$v.sysOperationLog.className.$invalid, 'invalid': $v.sysOperationLog.className.$invalid }" v-model="$v.sysOperationLog.className.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="sys-operation-log-method">Method</label>
                        <input type="text" class="form-control" name="method" id="sys-operation-log-method"
                            :class="{'valid': !$v.sysOperationLog.method.$invalid, 'invalid': $v.sysOperationLog.method.$invalid }" v-model="$v.sysOperationLog.method.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="sys-operation-log-succeed">Succeed</label>
                        <input type="text" class="form-control" name="succeed" id="sys-operation-log-succeed"
                            :class="{'valid': !$v.sysOperationLog.succeed.$invalid, 'invalid': $v.sysOperationLog.succeed.$invalid }" v-model="$v.sysOperationLog.succeed.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="sys-operation-log-createTime">Create Time</label>
                        <div class="d-flex">
                            <input id="sys-operation-log-createTime" type="datetime-local" class="form-control" name="createTime" :class="{'valid': !$v.sysOperationLog.createTime.$invalid, 'invalid': $v.sysOperationLog.createTime.$invalid }"
                            
                            :value="convertDateTimeFromServer($v.sysOperationLog.createTime.$model)"
                            @change="updateInstantField('createTime', $event)"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="sys-operation-log-message">Message</label>
                        <input type="text" class="form-control" name="message" id="sys-operation-log-message"
                            :class="{'valid': !$v.sysOperationLog.message.$invalid, 'invalid': $v.sysOperationLog.message.$invalid }" v-model="$v.sysOperationLog.message.$model" />
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span>Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.sysOperationLog.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span>Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./sys-operation-log-update.component.ts">
</script>
