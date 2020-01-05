<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="bmtPayRecord">
                <h2 class="jh-entity-heading"><span>BmtPayRecord</span> {{bmtPayRecord.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span>Pay Time</span>
                    </dt>
                    <dd>
                        <span>{{bmtPayRecord.payTime | formatDate}}</span>
                    </dd>
                    <dt>
                        <span>Pay Person Id</span>
                    </dt>
                    <dd>
                        <span>{{bmtPayRecord.payPersonId}}</span>
                    </dd>
                    <dt>
                        <span>Pay Person Name</span>
                    </dt>
                    <dd>
                        <span>{{bmtPayRecord.payPersonName}}</span>
                    </dd>
                    <dt>
                        <span>Pay Amount</span>
                    </dt>
                    <dd>
                        <span>{{bmtPayRecord.payAmount}}</span>
                    </dd>
                    <dt>
                        <span>Bmt Chang Ci</span>
                    </dt>
                    <dd>
                        <div v-if="bmtPayRecord.bmtChangCi">
                            <router-link :to="{name: 'BmtChangCiView', params: {bmtChangCiId: bmtPayRecord.bmtChangCi.id}}">{{bmtPayRecord.bmtChangCi.id}}</router-link>
                        </div>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span> Back</span>
                </button>
                <router-link v-if="bmtPayRecord.id" :to="{name: 'BmtPayRecordEdit', params: {bmtPayRecordId: bmtPayRecord.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./bmt-pay-record-details.component.ts">
</script>
