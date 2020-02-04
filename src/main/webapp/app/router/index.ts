import Vue from 'vue';
import Component from 'vue-class-component';
Component.registerHooks([
  'beforeRouteEnter',
  'beforeRouteLeave',
  'beforeRouteUpdate' // for vue-router 2.2+
]);
import Router from 'vue-router';
const Home = () => import('../core/home/home.vue');
const Error = () => import('../core/error/error.vue');
const Register = () => import('../account/register/register.vue');
const Activate = () => import('../account/activate/activate.vue');
const ResetPasswordInit = () => import('../account/reset-password/init/reset-password-init.vue');
const ResetPasswordFinish = () => import('../account/reset-password/finish/reset-password-finish.vue');
const ChangePassword = () => import('../account/change-password/change-password.vue');
const Settings = () => import('../account/settings/settings.vue');
const JhiUserManagementComponent = () => import('../admin/user-management/user-management.vue');
const JhiUserManagementViewComponent = () => import('../admin/user-management/user-management-view.vue');
const JhiUserManagementEditComponent = () => import('../admin/user-management/user-management-edit.vue');
const JhiConfigurationComponent = () => import('../admin/configuration/configuration.vue');
const JhiDocsComponent = () => import('../admin/docs/docs.vue');
const JhiHealthComponent = () => import('../admin/health/health.vue');
const JhiLogsComponent = () => import('../admin/logs/logs.vue');
const JhiAuditsComponent = () => import('../admin/audits/audits.vue');
const JhiMetricsComponent = () => import('../admin/metrics/metrics.vue');
const JhiTrackerComponent = () => import('../admin/tracker/tracker.vue');
/* tslint:disable */
// prettier-ignore
const BmtChangCi = () => import('../entities/bmt-chang-ci/bmt-chang-ci.vue');
// prettier-ignore
const BmtChangCiUpdate = () => import('../entities/bmt-chang-ci/bmt-chang-ci-update.vue');
// prettier-ignore
const BmtChangCiDetails = () => import('../entities/bmt-chang-ci/bmt-chang-ci-details.vue');
// prettier-ignore
const BmtPayRecord = () => import('../entities/bmt-pay-record/bmt-pay-record.vue');
// prettier-ignore
const BmtPayRecordUpdate = () => import('../entities/bmt-pay-record/bmt-pay-record-update.vue');
// prettier-ignore
const BmtPayRecordDetails = () => import('../entities/bmt-pay-record/bmt-pay-record-details.vue');
// prettier-ignore
const Production = () => import('../entities/production/production.vue');
// prettier-ignore
const ProductionUpdate = () => import('../entities/production/production-update.vue');
// prettier-ignore
const ProductionDetails = () => import('../entities/production/production-details.vue');
// prettier-ignore
const SysConfig = () => import('../entities/sys-config/sys-config.vue');
// prettier-ignore
const SysConfigUpdate = () => import('../entities/sys-config/sys-config-update.vue');
// prettier-ignore
const SysConfigDetails = () => import('../entities/sys-config/sys-config-details.vue');
// prettier-ignore
const SysDict = () => import('../entities/sys-dict/sys-dict.vue');
// prettier-ignore
const SysDictUpdate = () => import('../entities/sys-dict/sys-dict-update.vue');
// prettier-ignore
const SysDictDetails = () => import('../entities/sys-dict/sys-dict-details.vue');
// prettier-ignore
const SysDictType = () => import('../entities/sys-dict-type/sys-dict-type.vue');
// prettier-ignore
const SysDictTypeUpdate = () => import('../entities/sys-dict-type/sys-dict-type-update.vue');
// prettier-ignore
const SysDictTypeDetails = () => import('../entities/sys-dict-type/sys-dict-type-details.vue');
// prettier-ignore
const SysDept = () => import('../entities/sys-dept/sys-dept.vue');
// prettier-ignore
const SysDeptUpdate = () => import('../entities/sys-dept/sys-dept-update.vue');
// prettier-ignore
const SysDeptDetails = () => import('../entities/sys-dept/sys-dept-details.vue');
// prettier-ignore
const SysRelation = () => import('../entities/sys-relation/sys-relation.vue');
// prettier-ignore
const SysRelationUpdate = () => import('../entities/sys-relation/sys-relation-update.vue');
// prettier-ignore
const SysRelationDetails = () => import('../entities/sys-relation/sys-relation-details.vue');
// prettier-ignore
const SysRelationType = () => import('../entities/sys-relation-type/sys-relation-type.vue');
// prettier-ignore
const SysRelationTypeUpdate = () => import('../entities/sys-relation-type/sys-relation-type-update.vue');
// prettier-ignore
const SysRelationTypeDetails = () => import('../entities/sys-relation-type/sys-relation-type-details.vue');
// prettier-ignore
const SysFileInfo = () => import('../entities/sys-file-info/sys-file-info.vue');
// prettier-ignore
const SysFileInfoUpdate = () => import('../entities/sys-file-info/sys-file-info-update.vue');
// prettier-ignore
const SysFileInfoDetails = () => import('../entities/sys-file-info/sys-file-info-details.vue');
// prettier-ignore
const SysOperationLog = () => import('../entities/sys-operation-log/sys-operation-log.vue');
// prettier-ignore
const SysOperationLogUpdate = () => import('../entities/sys-operation-log/sys-operation-log-update.vue');
// prettier-ignore
const SysOperationLogDetails = () => import('../entities/sys-operation-log/sys-operation-log-details.vue');
// jhipster-needle-add-entity-to-router-import - JHipster will import entities to the router here

Vue.use(Router);

// prettier-ignore
export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home
    },
    {
      path: '/forbidden',
      name: 'Forbidden',
      component: Error,
      meta: { error403: true }
    },
    {
      path: '/not-found',
      name: 'NotFound',
      component: Error,
      meta: { error404: true }
    },
    {
      path: '/register',
      name: 'Register',
      component: Register
    },
    {
      path: '/activate',
      name: 'Activate',
      component: Activate
    },
    {
      path: '/reset/request',
      name: 'ResetPasswordInit',
      component: ResetPasswordInit
    },
    {
      path: '/reset/finish',
      name: 'ResetPasswordFinish',
      component: ResetPasswordFinish
    },
    {
      path: '/account/password',
      name: 'ChangePassword',
      component: ChangePassword,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/account/settings',
      name: 'Settings',
      component: Settings,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/admin/user-management',
      name: 'JhiUser',
      component: JhiUserManagementComponent,
      meta: { authorities: ['ROLE_ADMIN'] }
    },
    {
      path: '/admin/user-management/new',
      name: 'JhiUserCreate',
      component: JhiUserManagementEditComponent,
      meta: { authorities: ['ROLE_ADMIN'] }
    },
    {
      path: '/admin/user-management/:userId/edit',
      name: 'JhiUserEdit',
      component: JhiUserManagementEditComponent,
      meta: { authorities: ['ROLE_ADMIN'] }
    },
    {
      path: '/admin/user-management/:userId/view',
      name: 'JhiUserView',
      component: JhiUserManagementViewComponent,
      meta: { authorities: ['ROLE_ADMIN'] }
    },
    {
      path: '/admin/docs',
      name: 'JhiDocsComponent',
      component: JhiDocsComponent,
      meta: { authorities: ['ROLE_ADMIN'] }
    },
    {
      path: '/admin/audits',
      name: 'JhiAuditsComponent',
      component: JhiAuditsComponent,
      meta: { authorities: ['ROLE_ADMIN'] }
    },
    {
      path: '/admin/jhi-health',
      name: 'JhiHealthComponent',
      component: JhiHealthComponent,
      meta: { authorities: ['ROLE_ADMIN'] }
    },
    {
      path: '/admin/logs',
      name: 'JhiLogsComponent',
      component: JhiLogsComponent,
      meta: { authorities: ['ROLE_ADMIN'] }
    },
    {
      path: '/admin/jhi-metrics',
      name: 'JhiMetricsComponent',
      component: JhiMetricsComponent,
      meta: { authorities: ['ROLE_ADMIN'] }
    },
    {
      path: '/admin/jhi-configuration',
      name: 'JhiConfigurationComponent',
      component: JhiConfigurationComponent,
      meta: { authorities: ['ROLE_ADMIN'] }
    }
,
    {
      path: '/admin/jhi-tracker',
      name: 'JhiTrackerComponent',
      component: JhiTrackerComponent,
      meta: { authorities: ['ROLE_ADMIN'] }
    }
    ,
    {
      path: '/bmt-chang-ci',
      name: 'BmtChangCi',
      component: BmtChangCi,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/bmt-chang-ci/new',
      name: 'BmtChangCiCreate',
      component: BmtChangCiUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/bmt-chang-ci/:bmtChangCiId/edit',
      name: 'BmtChangCiEdit',
      component: BmtChangCiUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/bmt-chang-ci/:bmtChangCiId/view',
      name: 'BmtChangCiView',
      component: BmtChangCiDetails,
      meta: { authorities: ['ROLE_USER'] }
    }
    ,
    {
      path: '/bmt-pay-record',
      name: 'BmtPayRecord',
      component: BmtPayRecord,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/bmt-pay-record/new',
      name: 'BmtPayRecordCreate',
      component: BmtPayRecordUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/bmt-pay-record/:bmtPayRecordId/edit',
      name: 'BmtPayRecordEdit',
      component: BmtPayRecordUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/bmt-pay-record/:bmtPayRecordId/view',
      name: 'BmtPayRecordView',
      component: BmtPayRecordDetails,
      meta: { authorities: ['ROLE_USER'] }
    }
    ,
    {
      path: '/production',
      name: 'Production',
      component: Production,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/production/new',
      name: 'ProductionCreate',
      component: ProductionUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/production/:productionId/edit',
      name: 'ProductionEdit',
      component: ProductionUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/production/:productionId/view',
      name: 'ProductionView',
      component: ProductionDetails,
      meta: { authorities: ['ROLE_USER'] }
    }
    ,
    {
      path: '/sys-config',
      name: 'SysConfig',
      component: SysConfig,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/sys-config/new',
      name: 'SysConfigCreate',
      component: SysConfigUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/sys-config/:sysConfigId/edit',
      name: 'SysConfigEdit',
      component: SysConfigUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/sys-config/:sysConfigId/view',
      name: 'SysConfigView',
      component: SysConfigDetails,
      meta: { authorities: ['ROLE_USER'] }
    }
    ,
    {
      path: '/sys-dict',
      name: 'SysDict',
      component: SysDict,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/sys-dict/new',
      name: 'SysDictCreate',
      component: SysDictUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/sys-dict/:sysDictId/edit',
      name: 'SysDictEdit',
      component: SysDictUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/sys-dict/:sysDictId/view',
      name: 'SysDictView',
      component: SysDictDetails,
      meta: { authorities: ['ROLE_USER'] }
    }
    ,
    {
      path: '/sys-dict-type',
      name: 'SysDictType',
      component: SysDictType,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/sys-dict-type/new',
      name: 'SysDictTypeCreate',
      component: SysDictTypeUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/sys-dict-type/:sysDictTypeId/edit',
      name: 'SysDictTypeEdit',
      component: SysDictTypeUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/sys-dict-type/:sysDictTypeId/view',
      name: 'SysDictTypeView',
      component: SysDictTypeDetails,
      meta: { authorities: ['ROLE_USER'] }
    }
    ,
    {
      path: '/sys-dept',
      name: 'SysDept',
      component: SysDept,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/sys-dept/new',
      name: 'SysDeptCreate',
      component: SysDeptUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/sys-dept/:sysDeptId/edit',
      name: 'SysDeptEdit',
      component: SysDeptUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/sys-dept/:sysDeptId/view',
      name: 'SysDeptView',
      component: SysDeptDetails,
      meta: { authorities: ['ROLE_USER'] }
    }
    ,
    {
      path: '/sys-relation',
      name: 'SysRelation',
      component: SysRelation,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/sys-relation/new',
      name: 'SysRelationCreate',
      component: SysRelationUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/sys-relation/:sysRelationId/edit',
      name: 'SysRelationEdit',
      component: SysRelationUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/sys-relation/:sysRelationId/view',
      name: 'SysRelationView',
      component: SysRelationDetails,
      meta: { authorities: ['ROLE_USER'] }
    }
    ,
    {
      path: '/sys-relation-type',
      name: 'SysRelationType',
      component: SysRelationType,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/sys-relation-type/new',
      name: 'SysRelationTypeCreate',
      component: SysRelationTypeUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/sys-relation-type/:sysRelationTypeId/edit',
      name: 'SysRelationTypeEdit',
      component: SysRelationTypeUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/sys-relation-type/:sysRelationTypeId/view',
      name: 'SysRelationTypeView',
      component: SysRelationTypeDetails,
      meta: { authorities: ['ROLE_USER'] }
    }
    ,
    {
      path: '/sys-file-info',
      name: 'SysFileInfo',
      component: SysFileInfo,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/sys-file-info/new',
      name: 'SysFileInfoCreate',
      component: SysFileInfoUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/sys-file-info/:sysFileInfoId/edit',
      name: 'SysFileInfoEdit',
      component: SysFileInfoUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/sys-file-info/:sysFileInfoId/view',
      name: 'SysFileInfoView',
      component: SysFileInfoDetails,
      meta: { authorities: ['ROLE_USER'] }
    }
    ,
    {
      path: '/sys-operation-log',
      name: 'SysOperationLog',
      component: SysOperationLog,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/sys-operation-log/new',
      name: 'SysOperationLogCreate',
      component: SysOperationLogUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/sys-operation-log/:sysOperationLogId/edit',
      name: 'SysOperationLogEdit',
      component: SysOperationLogUpdate,
      meta: { authorities: ['ROLE_USER'] }
    },
    {
      path: '/sys-operation-log/:sysOperationLogId/view',
      name: 'SysOperationLogView',
      component: SysOperationLogDetails,
      meta: { authorities: ['ROLE_USER'] }
    }
    // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
  ]
});
