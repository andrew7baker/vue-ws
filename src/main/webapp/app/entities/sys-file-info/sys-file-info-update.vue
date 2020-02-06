<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="vuwsmtApp.sysFileInfo.home.createOrEditLabel">Create or edit a SysFileInfo</h2>
                <div>
                    <div class="form-group" v-if="sysFileInfo.id">
                        <label for="id">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="sysFileInfo.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="sys-file-info-machineCode">Machine Code</label>
                        <input type="text" class="form-control" name="machineCode" id="sys-file-info-machineCode"
                            :class="{'valid': !$v.sysFileInfo.machineCode.$invalid, 'invalid': $v.sysFileInfo.machineCode.$invalid }" v-model="$v.sysFileInfo.machineCode.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="sys-file-info-fileBucket">File Bucket</label>
                        <input type="text" class="form-control" name="fileBucket" id="sys-file-info-fileBucket"
                            :class="{'valid': !$v.sysFileInfo.fileBucket.$invalid, 'invalid': $v.sysFileInfo.fileBucket.$invalid }" v-model="$v.sysFileInfo.fileBucket.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="sys-file-info-fileName">File Name</label>
                        <input type="text" class="form-control" name="fileName" id="sys-file-info-fileName"
                            :class="{'valid': !$v.sysFileInfo.fileName.$invalid, 'invalid': $v.sysFileInfo.fileName.$invalid }" v-model="$v.sysFileInfo.fileName.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="sys-file-info-fileSuffix">File Suffix</label>
                        <input type="text" class="form-control" name="fileSuffix" id="sys-file-info-fileSuffix"
                            :class="{'valid': !$v.sysFileInfo.fileSuffix.$invalid, 'invalid': $v.sysFileInfo.fileSuffix.$invalid }" v-model="$v.sysFileInfo.fileSuffix.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="sys-file-info-fileSizeKb">File Size Kb</label>
                        <input type="number" class="form-control" name="fileSizeKb" id="sys-file-info-fileSizeKb"
                            :class="{'valid': !$v.sysFileInfo.fileSizeKb.$invalid, 'invalid': $v.sysFileInfo.fileSizeKb.$invalid }" v-model.number="$v.sysFileInfo.fileSizeKb.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="sys-file-info-finalName">Final Name</label>
                        <input type="text" class="form-control" name="finalName" id="sys-file-info-finalName"
                            :class="{'valid': !$v.sysFileInfo.finalName.$invalid, 'invalid': $v.sysFileInfo.finalName.$invalid }" v-model="$v.sysFileInfo.finalName.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="sys-file-info-filePath">File Path</label>
                        <input type="text" class="form-control" name="filePath" id="sys-file-info-filePath"
                            :class="{'valid': !$v.sysFileInfo.filePath.$invalid, 'invalid': $v.sysFileInfo.filePath.$invalid }" v-model="$v.sysFileInfo.filePath.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="sys-file-info-createTime">Create Time</label>
                        <div class="d-flex">
                            <input id="sys-file-info-createTime" type="datetime-local" class="form-control" name="createTime" :class="{'valid': !$v.sysFileInfo.createTime.$invalid, 'invalid': $v.sysFileInfo.createTime.$invalid }"
                            
                            :value="convertDateTimeFromServer($v.sysFileInfo.createTime.$model)"
                            @change="updateInstantField('createTime', $event)"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="sys-file-info-createUser">Create User</label>
                        <input type="number" class="form-control" name="createUser" id="sys-file-info-createUser"
                            :class="{'valid': !$v.sysFileInfo.createUser.$invalid, 'invalid': $v.sysFileInfo.createUser.$invalid }" v-model.number="$v.sysFileInfo.createUser.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="sys-file-info-updateTime">Update Time</label>
                        <div class="d-flex">
                            <input id="sys-file-info-updateTime" type="datetime-local" class="form-control" name="updateTime" :class="{'valid': !$v.sysFileInfo.updateTime.$invalid, 'invalid': $v.sysFileInfo.updateTime.$invalid }"
                            
                            :value="convertDateTimeFromServer($v.sysFileInfo.updateTime.$model)"
                            @change="updateInstantField('updateTime', $event)"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="sys-file-info-updateUser">Update User</label>
                        <input type="number" class="form-control" name="updateUser" id="sys-file-info-updateUser"
                            :class="{'valid': !$v.sysFileInfo.updateUser.$invalid, 'invalid': $v.sysFileInfo.updateUser.$invalid }" v-model.number="$v.sysFileInfo.updateUser.$model" />
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span>Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.sysFileInfo.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span>Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./sys-file-info-update.component.ts">
</script>
