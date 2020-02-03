import { Component, Vue, Inject } from 'vue-property-decorator';

import { ISysConfig } from '@/shared/model/sys-config.model';
import SysConfigService from './sys-config.service';

@Component
export default class SysConfigDetails extends Vue {
  @Inject('sysConfigService') private sysConfigService: () => SysConfigService;
  public sysConfig: ISysConfig = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.sysConfigId) {
        vm.retrieveSysConfig(to.params.sysConfigId);
      }
    });
  }

  public retrieveSysConfig(sysConfigId) {
    this.sysConfigService()
      .find(sysConfigId)
      .then(res => {
        this.sysConfig = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
