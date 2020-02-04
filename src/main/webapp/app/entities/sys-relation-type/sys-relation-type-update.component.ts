import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';
import { ISysRelationType, SysRelationType } from '@/shared/model/sys-relation-type.model';
import SysRelationTypeService from './sys-relation-type.service';

const validations: any = {
  sysRelationType: {
    typeCode: {},
    typedName: {}
  }
};

@Component({
  validations
})
export default class SysRelationTypeUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('sysRelationTypeService') private sysRelationTypeService: () => SysRelationTypeService;
  public sysRelationType: ISysRelationType = new SysRelationType();
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.sysRelationTypeId) {
        vm.retrieveSysRelationType(to.params.sysRelationTypeId);
      }
    });
  }

  public save(): void {
    this.isSaving = true;
    if (this.sysRelationType.id) {
      this.sysRelationTypeService()
        .update(this.sysRelationType)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A SysRelationType is updated with identifier ' + param.id;
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.sysRelationTypeService()
        .create(this.sysRelationType)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A SysRelationType is created with identifier ' + param.id;
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveSysRelationType(sysRelationTypeId): void {
    this.sysRelationTypeService()
      .find(sysRelationTypeId)
      .then(res => {
        this.sysRelationType = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
