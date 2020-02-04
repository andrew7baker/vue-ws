<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="vuwsmtApp.sysRelationType.home.createOrEditLabel">Create or edit a SysRelationType</h2>
                <div>
                    <div class="form-group" v-if="sysRelationType.id">
                        <label for="id">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="sysRelationType.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="sys-relation-type-typeCode">Type Code</label>
                        <input type="text" class="form-control" name="typeCode" id="sys-relation-type-typeCode"
                            :class="{'valid': !$v.sysRelationType.typeCode.$invalid, 'invalid': $v.sysRelationType.typeCode.$invalid }" v-model="$v.sysRelationType.typeCode.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="sys-relation-type-typedName">Typed Name</label>
                        <input type="text" class="form-control" name="typedName" id="sys-relation-type-typedName"
                            :class="{'valid': !$v.sysRelationType.typedName.$invalid, 'invalid': $v.sysRelationType.typedName.$invalid }" v-model="$v.sysRelationType.typedName.$model" />
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span>Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.sysRelationType.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span>Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./sys-relation-type-update.component.ts">
</script>
