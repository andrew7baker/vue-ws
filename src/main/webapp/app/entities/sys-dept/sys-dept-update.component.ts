import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength } from 'vuelidate/lib/validators';
import format from 'date-fns/format';
import parse from 'date-fns/parse';
import parseISO from 'date-fns/parseISO';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import AlertService from '@/shared/alert/alert.service';
import { ISysDept, SysDept } from '@/shared/model/sys-dept.model';
import SysDeptService from './sys-dept.service';

const validations: any = {
  sysDept: {
    parentId: {},
    parentIds: {},
    simpleName: {},
    fullName: {},
    description: {},
    version: {},
    status: {},
    sort: {},
    createTime: {},
    createUser: {},
    updateTime: {},
    updateUser: {}
  }
};

@Component({
  validations
})
export default class SysDeptUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('sysDeptService') private sysDeptService: () => SysDeptService;
  public sysDept: ISysDept = new SysDept();
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.sysDeptId) {
        vm.retrieveSysDept(to.params.sysDeptId);
      }
    });
  }

  public save(): void {
    this.isSaving = true;
    if (this.sysDept.id) {
      this.sysDeptService()
        .update(this.sysDept)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A SysDept is updated with identifier ' + param.id;
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.sysDeptService()
        .create(this.sysDept)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A SysDept is created with identifier ' + param.id;
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
      this.sysDept[field] = parse(event.target.value, DATE_TIME_LONG_FORMAT, new Date());
    } else {
      this.sysDept[field] = null;
    }
  }

  public updateZonedDateTimeField(field, event) {
    if (event.target.value) {
      this.sysDept[field] = parse(event.target.value, DATE_TIME_LONG_FORMAT, new Date());
    } else {
      this.sysDept[field] = null;
    }
  }

  public retrieveSysDept(sysDeptId): void {
    this.sysDeptService()
      .find(sysDeptId)
      .then(res => {
        res.createTime = new Date(res.createTime);
        res.updateTime = new Date(res.updateTime);
        this.sysDept = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
