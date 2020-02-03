import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength } from 'vuelidate/lib/validators';
import format from 'date-fns/format';
import parse from 'date-fns/parse';
import parseISO from 'date-fns/parseISO';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import AlertService from '@/shared/alert/alert.service';
import { ISysDict, SysDict } from '@/shared/model/sys-dict.model';
import SysDictService from './sys-dict.service';

const validations: any = {
  sysDict: {
    dicTypeId: {},
    code: {},
    name: {},
    parentId: {},
    parentIds: {},
    status: {},
    sort: {},
    description: {},
    createTime: {},
    createUser: {},
    updateTime: {},
    updateUser: {}
  }
};

@Component({
  validations
})
export default class SysDictUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('sysDictService') private sysDictService: () => SysDictService;
  public sysDict: ISysDict = new SysDict();
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.sysDictId) {
        vm.retrieveSysDict(to.params.sysDictId);
      }
    });
  }

  public save(): void {
    this.isSaving = true;
    if (this.sysDict.id) {
      this.sysDictService()
        .update(this.sysDict)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A SysDict is updated with identifier ' + param.id;
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.sysDictService()
        .create(this.sysDict)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A SysDict is created with identifier ' + param.id;
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
      this.sysDict[field] = parse(event.target.value, DATE_TIME_LONG_FORMAT, new Date());
    } else {
      this.sysDict[field] = null;
    }
  }

  public updateZonedDateTimeField(field, event) {
    if (event.target.value) {
      this.sysDict[field] = parse(event.target.value, DATE_TIME_LONG_FORMAT, new Date());
    } else {
      this.sysDict[field] = null;
    }
  }

  public retrieveSysDict(sysDictId): void {
    this.sysDictService()
      .find(sysDictId)
      .then(res => {
        res.createTime = new Date(res.createTime);
        res.updateTime = new Date(res.updateTime);
        this.sysDict = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
