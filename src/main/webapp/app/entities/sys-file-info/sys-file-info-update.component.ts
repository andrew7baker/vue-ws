import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength } from 'vuelidate/lib/validators';
import format from 'date-fns/format';
import parse from 'date-fns/parse';
import parseISO from 'date-fns/parseISO';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import AlertService from '@/shared/alert/alert.service';
import { ISysFileInfo, SysFileInfo } from '@/shared/model/sys-file-info.model';
import SysFileInfoService from './sys-file-info.service';

const validations: any = {
  sysFileInfo: {
    fileBucket: {},
    fileName: {},
    fileSuffix: {},
    fileSizeKb: {},
    finalName: {},
    filePath: {},
    createTime: {},
    createUser: {},
    updateTime: {},
    updateUser: {}
  }
};

@Component({
  validations
})
export default class SysFileInfoUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('sysFileInfoService') private sysFileInfoService: () => SysFileInfoService;
  public sysFileInfo: ISysFileInfo = new SysFileInfo();
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.sysFileInfoId) {
        vm.retrieveSysFileInfo(to.params.sysFileInfoId);
      }
    });
  }

  public save(): void {
    this.isSaving = true;
    if (this.sysFileInfo.id) {
      this.sysFileInfoService()
        .update(this.sysFileInfo)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A SysFileInfo is updated with identifier ' + param.id;
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.sysFileInfoService()
        .create(this.sysFileInfo)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A SysFileInfo is created with identifier ' + param.id;
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
      this.sysFileInfo[field] = parse(event.target.value, DATE_TIME_LONG_FORMAT, new Date());
    } else {
      this.sysFileInfo[field] = null;
    }
  }

  public updateZonedDateTimeField(field, event) {
    if (event.target.value) {
      this.sysFileInfo[field] = parse(event.target.value, DATE_TIME_LONG_FORMAT, new Date());
    } else {
      this.sysFileInfo[field] = null;
    }
  }

  public retrieveSysFileInfo(sysFileInfoId): void {
    this.sysFileInfoService()
      .find(sysFileInfoId)
      .then(res => {
        res.createTime = new Date(res.createTime);
        res.updateTime = new Date(res.updateTime);
        this.sysFileInfo = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
