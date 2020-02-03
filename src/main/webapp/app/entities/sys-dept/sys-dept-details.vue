<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="sysDept">
                <h2 class="jh-entity-heading"><span>SysDept</span> {{sysDept.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span>Parent Id</span>
                    </dt>
                    <dd>
                        <span>{{sysDept.parentId}}</span>
                    </dd>
                    <dt>
                        <span>Parent Ids</span>
                    </dt>
                    <dd>
                        <span>{{sysDept.parentIds}}</span>
                    </dd>
                    <dt>
                        <span>Simple Name</span>
                    </dt>
                    <dd>
                        <span>{{sysDept.simpleName}}</span>
                    </dd>
                    <dt>
                        <span>Full Name</span>
                    </dt>
                    <dd>
                        <span>{{sysDept.fullName}}</span>
                    </dd>
                    <dt>
                        <span>Description</span>
                    </dt>
                    <dd>
                        <span>{{sysDept.description}}</span>
                    </dd>
                    <dt>
                        <span>Version</span>
                    </dt>
                    <dd>
                        <span>{{sysDept.version}}</span>
                    </dd>
                    <dt>
                        <span>Status</span>
                    </dt>
                    <dd>
                        <span>{{sysDept.status}}</span>
                    </dd>
                    <dt>
                        <span>Sort</span>
                    </dt>
                    <dd>
                        <span>{{sysDept.sort}}</span>
                    </dd>
                    <dt>
                        <span>Create Time</span>
                    </dt>
                    <dd>
                        <span>{{sysDept.createTime | formatDate}}</span>
                    </dd>
                    <dt>
                        <span>Create User</span>
                    </dt>
                    <dd>
                        <span>{{sysDept.createUser}}</span>
                    </dd>
                    <dt>
                        <span>Update Time</span>
                    </dt>
                    <dd>
                        <span>{{sysDept.updateTime | formatDate}}</span>
                    </dd>
                    <dt>
                        <span>Update User</span>
                    </dt>
                    <dd>
                        <span>{{sysDept.updateUser}}</span>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span> Back</span>
                </button>
                <router-link v-if="sysDept.id" :to="{name: 'SysDeptEdit', params: {sysDeptId: sysDept.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./sys-dept-details.component.ts">
</script>
