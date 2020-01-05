import { Component, Vue, Inject } from 'vue-property-decorator';

import { IBmtChangCi } from '@/shared/model/bmt-chang-ci.model';
import BmtChangCiService from './bmt-chang-ci.service';

@Component
export default class BmtChangCiDetails extends Vue {
  @Inject('bmtChangCiService') private bmtChangCiService: () => BmtChangCiService;
  public bmtChangCi: IBmtChangCi = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.bmtChangCiId) {
        vm.retrieveBmtChangCi(to.params.bmtChangCiId);
      }
    });
  }

  public retrieveBmtChangCi(bmtChangCiId) {
    this.bmtChangCiService()
      .find(bmtChangCiId)
      .then(res => {
        this.bmtChangCi = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
