import { Component, Vue, Inject } from 'vue-property-decorator';

import { ISysRelation } from '@/shared/model/sys-relation.model';
import SysRelationService from './sys-relation.service';

@Component
export default class SysRelationDetails extends Vue {
  @Inject('sysRelationService') private sysRelationService: () => SysRelationService;
  public sysRelation: ISysRelation = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.sysRelationId) {
        vm.retrieveSysRelation(to.params.sysRelationId);
      }
    });
  }

  public retrieveSysRelation(sysRelationId) {
    this.sysRelationService()
      .find(sysRelationId)
      .then(res => {
        this.sysRelation = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
