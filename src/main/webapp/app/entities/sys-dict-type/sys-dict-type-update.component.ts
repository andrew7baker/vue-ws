import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength } from 'vuelidate/lib/validators';
import format from 'date-fns/format';
import parse from 'date-fns/parse';
import parseISO from 'date-fns/parseISO';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import AlertService from '@/shared/alert/alert.service';
import { ISysDictType, SysDictType } from '@/shared/model/sys-dict-type.model';
import SysDictTypeService from './sys-dict-type.service';

const validations: any = {
  sysDictType: {
    code: {},
    name: {},
    systemFlag: {},
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
export default class SysDictTypeUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('sysDictTypeService') private sysDictTypeService: () => SysDictTypeService;
  public sysDictType: ISysDictType = new SysDictType();
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.sysDictTypeId) {
        vm.retrieveSysDictType(to.params.sysDictTypeId);
      }
    });
  }

  public save(): void {
    this.isSaving = true;
    if (this.sysDictType.id) {
      this.sysDictTypeService()
        .update(this.sysDictType)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A SysDictType is updated with identifier ' + param.id;
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.sysDictTypeService()
        .create(this.sysDictType)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A SysDictType is created with identifier ' + param.id;
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
      this.sysDictType[field] = parse(event.target.value, DATE_TIME_LONG_FORMAT, new Date());
    } else {
      this.sysDictType[field] = null;
    }
  }

  public updateZonedDateTimeField(field, event) {
    if (event.target.value) {
      this.sysDictType[field] = parse(event.target.value, DATE_TIME_LONG_FORMAT, new Date());
    } else {
      this.sysDictType[field] = null;
    }
  }

  public retrieveSysDictType(sysDictTypeId): void {
    this.sysDictTypeService()
      .find(sysDictTypeId)
      .then(res => {
        res.createTime = new Date(res.createTime);
        res.updateTime = new Date(res.updateTime);
        this.sysDictType = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
