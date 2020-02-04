import { Component, Vue, Inject } from 'vue-property-decorator';

import { ISysFileInfo } from '@/shared/model/sys-file-info.model';
import SysFileInfoService from './sys-file-info.service';

@Component
export default class SysFileInfoDetails extends Vue {
  @Inject('sysFileInfoService') private sysFileInfoService: () => SysFileInfoService;
  public sysFileInfo: ISysFileInfo = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.sysFileInfoId) {
        vm.retrieveSysFileInfo(to.params.sysFileInfoId);
      }
    });
  }

  public retrieveSysFileInfo(sysFileInfoId) {
    this.sysFileInfoService()
      .find(sysFileInfoId)
      .then(res => {
        this.sysFileInfo = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
