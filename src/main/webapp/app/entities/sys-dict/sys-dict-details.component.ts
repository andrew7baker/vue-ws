import { Component, Vue, Inject } from 'vue-property-decorator';

import { ISysDict } from '@/shared/model/sys-dict.model';
import SysDictService from './sys-dict.service';

@Component
export default class SysDictDetails extends Vue {
  @Inject('sysDictService') private sysDictService: () => SysDictService;
  public sysDict: ISysDict = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.sysDictId) {
        vm.retrieveSysDict(to.params.sysDictId);
      }
    });
  }

  public retrieveSysDict(sysDictId) {
    this.sysDictService()
      .find(sysDictId)
      .then(res => {
        this.sysDict = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
