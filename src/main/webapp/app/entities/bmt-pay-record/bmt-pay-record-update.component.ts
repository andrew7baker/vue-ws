import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength } from 'vuelidate/lib/validators';
import format from 'date-fns/format';
import parse from 'date-fns/parse';
import parseISO from 'date-fns/parseISO';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import BmtChangCiService from '../bmt-chang-ci/bmt-chang-ci.service';
import { IBmtChangCi } from '@/shared/model/bmt-chang-ci.model';

import AlertService from '@/shared/alert/alert.service';
import { IBmtPayRecord, BmtPayRecord } from '@/shared/model/bmt-pay-record.model';
import BmtPayRecordService from './bmt-pay-record.service';

const validations: any = {
  bmtPayRecord: {
    payTime: {},
    payPersonId: {},
    payPersonName: {},
    payAmount: {}
  }
};

@Component({
  validations
})
export default class BmtPayRecordUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('bmtPayRecordService') private bmtPayRecordService: () => BmtPayRecordService;
  public bmtPayRecord: IBmtPayRecord = new BmtPayRecord();

  @Inject('bmtChangCiService') private bmtChangCiService: () => BmtChangCiService;

  public bmtChangCis: IBmtChangCi[] = [];
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.bmtPayRecordId) {
        vm.retrieveBmtPayRecord(to.params.bmtPayRecordId);
      }
      vm.initRelationships();
    });
  }

  public save(): void {
    this.isSaving = true;
    if (this.bmtPayRecord.id) {
      this.bmtPayRecordService()
        .update(this.bmtPayRecord)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A BmtPayRecord is updated with identifier ' + param.id;
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.bmtPayRecordService()
        .create(this.bmtPayRecord)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A BmtPayRecord is created with identifier ' + param.id;
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public convertDateTimeFromServer(date: Date): string {
    if (date) {
      return format(date, DATE_TIME_LONG_FORMAT);
    }
    return null;
  }

  public updateInstantField(field, event) {
    if (event.target.value) {
      this.bmtPayRecord[field] = parse(event.target.value, DATE_TIME_LONG_FORMAT, new Date());
    } else {
      this.bmtPayRecord[field] = null;
    }
  }

  public updateZonedDateTimeField(field, event) {
    if (event.target.value) {
      this.bmtPayRecord[field] = parse(event.target.value, DATE_TIME_LONG_FORMAT, new Date());
    } else {
      this.bmtPayRecord[field] = null;
    }
  }

  public retrieveBmtPayRecord(bmtPayRecordId): void {
    this.bmtPayRecordService()
      .find(bmtPayRecordId)
      .then(res => {
        res.payTime = new Date(res.payTime);
        this.bmtPayRecord = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.bmtChangCiService()
      .retrieve()
      .then(res => {
        this.bmtChangCis = res.data;
      });
  }
}
