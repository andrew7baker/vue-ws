import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength } from 'vuelidate/lib/validators';
import format from 'date-fns/format';
import parse from 'date-fns/parse';
import parseISO from 'date-fns/parseISO';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import AlertService from '@/shared/alert/alert.service';
import { ISysConfig, SysConfig } from '@/shared/model/sys-config.model';
import SysConfigService from './sys-config.service';

const validations: any = {
  sysConfig: {
    name: {},
    code: {},
    dictFlag: {},
    dictTypeId: {},
    value: {},
    remark: {},
    createTime: {},
    createUser: {},
    updateTime: {},
    updateUser: {}
  }
};

@Component({
  validations
})
export default class SysConfigUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('sysConfigService') private sysConfigService: () => SysConfigService;
  public sysConfig: ISysConfig = new SysConfig();
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.sysConfigId) {
        vm.retrieveSysConfig(to.params.sysConfigId);
      }
    });
  }

  public save(): void {
    this.isSaving = true;
    if (this.sysConfig.id) {
      this.sysConfigService()
        .update(this.sysConfig)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A SysConfig is updated with identifier ' + param.id;
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.sysConfigService()
        .create(this.sysConfig)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A SysConfig is created with identifier ' + param.id;
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
      this.sysConfig[field] = parse(event.target.value, DATE_TIME_LONG_FORMAT, new Date());
    } else {
      this.sysConfig[field] = null;
    }
  }

  public updateZonedDateTimeField(field, event) {
    if (event.target.value) {
      this.sysConfig[field] = parse(event.target.value, DATE_TIME_LONG_FORMAT, new Date());
    } else {
      this.sysConfig[field] = null;
    }
  }

  public retrieveSysConfig(sysConfigId): void {
    this.sysConfigService()
      .find(sysConfigId)
      .then(res => {
        res.createTime = new Date(res.createTime);
        res.updateTime = new Date(res.updateTime);
        this.sysConfig = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
