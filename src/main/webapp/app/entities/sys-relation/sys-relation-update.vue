<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="vuwsmtApp.sysRelation.home.createOrEditLabel">Create or edit a SysRelation</h2>
                <div>
                    <div class="form-group" v-if="sysRelation.id">
                        <label for="id">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="sysRelation.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="sys-relation-fromId">From Id</label>
                        <input type="number" class="form-control" name="fromId" id="sys-relation-fromId"
                            :class="{'valid': !$v.sysRelation.fromId.$invalid, 'invalid': $v.sysRelation.fromId.$invalid }" v-model.number="$v.sysRelation.fromId.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="sys-relation-toId">To Id</label>
                        <input type="number" class="form-control" name="toId" id="sys-relation-toId"
                            :class="{'valid': !$v.sysRelation.toId.$invalid, 'invalid': $v.sysRelation.toId.$invalid }" v-model.number="$v.sysRelation.toId.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="sys-relation-typeCode">Type Code</label>
                        <input type="text" class="form-control" name="typeCode" id="sys-relation-typeCode"
                            :class="{'valid': !$v.sysRelation.typeCode.$invalid, 'invalid': $v.sysRelation.typeCode.$invalid }" v-model="$v.sysRelation.typeCode.$model" />
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span>Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.sysRelation.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span>Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./sys-relation-update.component.ts">
</script>
