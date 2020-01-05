<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="vuwsmtApp.bmtChangCi.home.createOrEditLabel">Create or edit a BmtChangCi</h2>
                <div>
                    <div class="form-group" v-if="bmtChangCi.id">
                        <label for="id">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="bmtChangCi.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="bmt-chang-ci-name">Name</label>
                        <input type="text" class="form-control" name="name" id="bmt-chang-ci-name"
                            :class="{'valid': !$v.bmtChangCi.name.$invalid, 'invalid': $v.bmtChangCi.name.$invalid }" v-model="$v.bmtChangCi.name.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="bmt-chang-ci-weekDay">Week Day</label>
                        <input type="text" class="form-control" name="weekDay" id="bmt-chang-ci-weekDay"
                            :class="{'valid': !$v.bmtChangCi.weekDay.$invalid, 'invalid': $v.bmtChangCi.weekDay.$invalid }" v-model="$v.bmtChangCi.weekDay.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="bmt-chang-ci-timeBegin">Time Begin</label>
                        <div class="d-flex">
                            <input id="bmt-chang-ci-timeBegin" type="datetime-local" class="form-control" name="timeBegin" :class="{'valid': !$v.bmtChangCi.timeBegin.$invalid, 'invalid': $v.bmtChangCi.timeBegin.$invalid }"
                            
                            :value="convertDateTimeFromServer($v.bmtChangCi.timeBegin.$model)"
                            @change="updateInstantField('timeBegin', $event)"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="bmt-chang-ci-timeEnd">Time End</label>
                        <div class="d-flex">
                            <input id="bmt-chang-ci-timeEnd" type="datetime-local" class="form-control" name="timeEnd" :class="{'valid': !$v.bmtChangCi.timeEnd.$invalid, 'invalid': $v.bmtChangCi.timeEnd.$invalid }"
                            
                            :value="convertDateTimeFromServer($v.bmtChangCi.timeEnd.$model)"
                            @change="updateInstantField('timeEnd', $event)"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="bmt-chang-ci-owner">Owner</label>
                        <input type="text" class="form-control" name="owner" id="bmt-chang-ci-owner"
                            :class="{'valid': !$v.bmtChangCi.owner.$invalid, 'invalid': $v.bmtChangCi.owner.$invalid }" v-model="$v.bmtChangCi.owner.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="bmt-chang-ci-userId">User Id</label>
                        <input type="number" class="form-control" name="userId" id="bmt-chang-ci-userId"
                            :class="{'valid': !$v.bmtChangCi.userId.$invalid, 'invalid': $v.bmtChangCi.userId.$invalid }" v-model.number="$v.bmtChangCi.userId.$model" />
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span>Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.bmtChangCi.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span>Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./bmt-chang-ci-update.component.ts">
</script>
