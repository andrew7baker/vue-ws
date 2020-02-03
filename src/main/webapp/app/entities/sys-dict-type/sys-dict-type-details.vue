<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="sysDictType">
                <h2 class="jh-entity-heading"><span>SysDictType</span> {{sysDictType.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span>Code</span>
                    </dt>
                    <dd>
                        <span>{{sysDictType.code}}</span>
                    </dd>
                    <dt>
                        <span>Name</span>
                    </dt>
                    <dd>
                        <span>{{sysDictType.name}}</span>
                    </dd>
                    <dt>
                        <span>System Flag</span>
                    </dt>
                    <dd>
                        <span>{{sysDictType.systemFlag}}</span>
                    </dd>
                    <dt>
                        <span>Status</span>
                    </dt>
                    <dd>
                        <span>{{sysDictType.status}}</span>
                    </dd>
                    <dt>
                        <span>Sort</span>
                    </dt>
                    <dd>
                        <span>{{sysDictType.sort}}</span>
                    </dd>
                    <dt>
                        <span>Description</span>
                    </dt>
                    <dd>
                        <span>{{sysDictType.description}}</span>
                    </dd>
                    <dt>
                        <span>Create Time</span>
                    </dt>
                    <dd>
                        <span>{{sysDictType.createTime | formatDate}}</span>
                    </dd>
                    <dt>
                        <span>Create User</span>
                    </dt>
                    <dd>
                        <span>{{sysDictType.createUser}}</span>
                    </dd>
                    <dt>
                        <span>Update Time</span>
                    </dt>
                    <dd>
                        <span>{{sysDictType.updateTime | formatDate}}</span>
                    </dd>
                    <dt>
                        <span>Update User</span>
                    </dt>
                    <dd>
                        <span>{{sysDictType.updateUser}}</span>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span> Back</span>
                </button>
                <router-link v-if="sysDictType.id" :to="{name: 'SysDictTypeEdit', params: {sysDictTypeId: sysDictType.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./sys-dict-type-details.component.ts">
</script>
