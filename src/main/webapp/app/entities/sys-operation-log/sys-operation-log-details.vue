<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="sysOperationLog">
                <h2 class="jh-entity-heading"><span>SysOperationLog</span> {{sysOperationLog.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span>Log Type</span>
                    </dt>
                    <dd>
                        <span>{{sysOperationLog.logType}}</span>
                    </dd>
                    <dt>
                        <span>Log Name</span>
                    </dt>
                    <dd>
                        <span>{{sysOperationLog.logName}}</span>
                    </dd>
                    <dt>
                        <span>User Id</span>
                    </dt>
                    <dd>
                        <span>{{sysOperationLog.userId}}</span>
                    </dd>
                    <dt>
                        <span>Class Name</span>
                    </dt>
                    <dd>
                        <span>{{sysOperationLog.className}}</span>
                    </dd>
                    <dt>
                        <span>Method</span>
                    </dt>
                    <dd>
                        <span>{{sysOperationLog.method}}</span>
                    </dd>
                    <dt>
                        <span>Succeed</span>
                    </dt>
                    <dd>
                        <span>{{sysOperationLog.succeed}}</span>
                    </dd>
                    <dt>
                        <span>Create Time</span>
                    </dt>
                    <dd>
                        <span>{{sysOperationLog.createTime | formatDate}}</span>
                    </dd>
                    <dt>
                        <span>Message</span>
                    </dt>
                    <dd>
                        <span>{{sysOperationLog.message}}</span>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span> Back</span>
                </button>
                <router-link v-if="sysOperationLog.id" :to="{name: 'SysOperationLogEdit', params: {sysOperationLogId: sysOperationLog.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./sys-operation-log-details.component.ts">
</script>
