<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="sysFileInfo">
                <h2 class="jh-entity-heading"><span>SysFileInfo</span> {{sysFileInfo.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span>File Bucket</span>
                    </dt>
                    <dd>
                        <span>{{sysFileInfo.fileBucket}}</span>
                    </dd>
                    <dt>
                        <span>File Name</span>
                    </dt>
                    <dd>
                        <span>{{sysFileInfo.fileName}}</span>
                    </dd>
                    <dt>
                        <span>File Suffix</span>
                    </dt>
                    <dd>
                        <span>{{sysFileInfo.fileSuffix}}</span>
                    </dd>
                    <dt>
                        <span>File Size Kb</span>
                    </dt>
                    <dd>
                        <span>{{sysFileInfo.fileSizeKb}}</span>
                    </dd>
                    <dt>
                        <span>Final Name</span>
                    </dt>
                    <dd>
                        <span>{{sysFileInfo.finalName}}</span>
                    </dd>
                    <dt>
                        <span>File Path</span>
                    </dt>
                    <dd>
                        <span>{{sysFileInfo.filePath}}</span>
                    </dd>
                    <dt>
                        <span>Create Time</span>
                    </dt>
                    <dd>
                        <span>{{sysFileInfo.createTime | formatDate}}</span>
                    </dd>
                    <dt>
                        <span>Create User</span>
                    </dt>
                    <dd>
                        <span>{{sysFileInfo.createUser}}</span>
                    </dd>
                    <dt>
                        <span>Update Time</span>
                    </dt>
                    <dd>
                        <span>{{sysFileInfo.updateTime | formatDate}}</span>
                    </dd>
                    <dt>
                        <span>Update User</span>
                    </dt>
                    <dd>
                        <span>{{sysFileInfo.updateUser}}</span>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span> Back</span>
                </button>
                <router-link v-if="sysFileInfo.id" :to="{name: 'SysFileInfoEdit', params: {sysFileInfoId: sysFileInfo.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./sys-file-info-details.component.ts">
</script>
