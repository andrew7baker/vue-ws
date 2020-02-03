import { Component, Vue, Inject } from 'vue-property-decorator';

import { ISysDictType } from '@/shared/model/sys-dict-type.model';
import SysDictTypeService from './sys-dict-type.service';

@Component
export default class SysDictTypeDetails extends Vue {
  @Inject('sysDictTypeService') private sysDictTypeService: () => SysDictTypeService;
  public sysDictType: ISysDictType = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.sysDictTypeId) {
        vm.retrieveSysDictType(to.params.sysDictTypeId);
      }
    });
  }

  public retrieveSysDictType(sysDictTypeId) {
    this.sysDictTypeService()
      .find(sysDictTypeId)
      .then(res => {
        this.sysDictType = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
