import { Component, Vue, Inject } from 'vue-property-decorator';

import { ISysRelationType } from '@/shared/model/sys-relation-type.model';
import SysRelationTypeService from './sys-relation-type.service';

@Component
export default class SysRelationTypeDetails extends Vue {
  @Inject('sysRelationTypeService') private sysRelationTypeService: () => SysRelationTypeService;
  public sysRelationType: ISysRelationType = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.sysRelationTypeId) {
        vm.retrieveSysRelationType(to.params.sysRelationTypeId);
      }
    });
  }

  public retrieveSysRelationType(sysRelationTypeId) {
    this.sysRelationTypeService()
      .find(sysRelationTypeId)
      .then(res => {
        this.sysRelationType = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
