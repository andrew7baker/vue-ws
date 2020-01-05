import { Component, Vue, Inject } from 'vue-property-decorator';

import { IBmtPayRecord } from '@/shared/model/bmt-pay-record.model';
import BmtPayRecordService from './bmt-pay-record.service';

@Component
export default class BmtPayRecordDetails extends Vue {
  @Inject('bmtPayRecordService') private bmtPayRecordService: () => BmtPayRecordService;
  public bmtPayRecord: IBmtPayRecord = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.bmtPayRecordId) {
        vm.retrieveBmtPayRecord(to.params.bmtPayRecordId);
      }
    });
  }

  public retrieveBmtPayRecord(bmtPayRecordId) {
    this.bmtPayRecordService()
      .find(bmtPayRecordId)
      .then(res => {
        this.bmtPayRecord = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
