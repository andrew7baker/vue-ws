import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';
import { ISysRelation, SysRelation } from '@/shared/model/sys-relation.model';
import SysRelationService from './sys-relation.service';

const validations: any = {
  sysRelation: {
    fromId: {},
    toId: {},
    typeCode: {}
  }
};

@Component({
  validations
})
export default class SysRelationUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('sysRelationService') private sysRelationService: () => SysRelationService;
  public sysRelation: ISysRelation = new SysRelation();
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.sysRelationId) {
        vm.retrieveSysRelation(to.params.sysRelationId);
      }
    });
  }

  public save(): void {
    this.isSaving = true;
    if (this.sysRelation.id) {
      this.sysRelationService()
        .update(this.sysRelation)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A SysRelation is updated with identifier ' + param.id;
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.sysRelationService()
        .create(this.sysRelation)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A SysRelation is created with identifier ' + param.id;
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveSysRelation(sysRelationId): void {
    this.sysRelationService()
      .find(sysRelationId)
      .then(res => {
        this.sysRelation = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
