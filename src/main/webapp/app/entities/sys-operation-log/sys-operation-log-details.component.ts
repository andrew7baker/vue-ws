import { Component, Vue, Inject } from 'vue-property-decorator';

import { ISysOperationLog } from '@/shared/model/sys-operation-log.model';
import SysOperationLogService from './sys-operation-log.service';

@Component
export default class SysOperationLogDetails extends Vue {
  @Inject('sysOperationLogService') private sysOperationLogService: () => SysOperationLogService;
  public sysOperationLog: ISysOperationLog = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.sysOperationLogId) {
        vm.retrieveSysOperationLog(to.params.sysOperationLogId);
      }
    });
  }

  public retrieveSysOperationLog(sysOperationLogId) {
    this.sysOperationLogService()
      .find(sysOperationLogId)
      .then(res => {
        this.sysOperationLog = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
