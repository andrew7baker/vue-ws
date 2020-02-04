import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength } from 'vuelidate/lib/validators';
import format from 'date-fns/format';
import parse from 'date-fns/parse';
import parseISO from 'date-fns/parseISO';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import AlertService from '@/shared/alert/alert.service';
import { ISysOperationLog, SysOperationLog } from '@/shared/model/sys-operation-log.model';
import SysOperationLogService from './sys-operation-log.service';

const validations: any = {
  sysOperationLog: {
    logType: {},
    logName: {},
    userId: {},
    className: {},
    method: {},
    succeed: {},
    createTime: {},
    message: {}
  }
};

@Component({
  validations
})
export default class SysOperationLogUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('sysOperationLogService') private sysOperationLogService: () => SysOperationLogService;
  public sysOperationLog: ISysOperationLog = new SysOperationLog();
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.sysOperationLogId) {
        vm.retrieveSysOperationLog(to.params.sysOperationLogId);
      }
    });
  }

  public save(): void {
    this.isSaving = true;
    if (this.sysOperationLog.id) {
      this.sysOperationLogService()
        .update(this.sysOperationLog)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A SysOperationLog is updated with identifier ' + param.id;
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.sysOperationLogService()
        .create(this.sysOperationLog)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A SysOperationLog is created with identifier ' + param.id;
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public convertDateTimeFromServer(date: Date): string {
    if (date) {
      return format(date, DATE_TIME_LONG_FORMAT);
    }
    return null;
  }

  public updateInstantField(field, event) {
    if (event.target.value) {
      this.sysOperationLog[field] = parse(event.target.value, DATE_TIME_LONG_FORMAT, new Date());
    } else {
      this.sysOperationLog[field] = null;
    }
  }

  public updateZonedDateTimeField(field, event) {
    if (event.target.value) {
      this.sysOperationLog[field] = parse(event.target.value, DATE_TIME_LONG_FORMAT, new Date());
    } else {
      this.sysOperationLog[field] = null;
    }
  }

  public retrieveSysOperationLog(sysOperationLogId): void {
    this.sysOperationLogService()
      .find(sysOperationLogId)
      .then(res => {
        res.createTime = new Date(res.createTime);
        this.sysOperationLog = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
