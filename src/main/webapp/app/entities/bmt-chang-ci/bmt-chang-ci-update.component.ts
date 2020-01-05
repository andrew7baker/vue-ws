import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength } from 'vuelidate/lib/validators';
import format from 'date-fns/format';
import parse from 'date-fns/parse';
import parseISO from 'date-fns/parseISO';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import BmtPayRecordService from '../bmt-pay-record/bmt-pay-record.service';
import { IBmtPayRecord } from '@/shared/model/bmt-pay-record.model';

import AlertService from '@/shared/alert/alert.service';
import { IBmtChangCi, BmtChangCi } from '@/shared/model/bmt-chang-ci.model';
import BmtChangCiService from './bmt-chang-ci.service';

const validations: any = {
  bmtChangCi: {
    name: {},
    weekDay: {},
    timeBegin: {},
    timeEnd: {},
    owner: {},
    userId: {}
  }
};

@Component({
  validations
})
export default class BmtChangCiUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('bmtChangCiService') private bmtChangCiService: () => BmtChangCiService;
  public bmtChangCi: IBmtChangCi = new BmtChangCi();

  @Inject('bmtPayRecordService') private bmtPayRecordService: () => BmtPayRecordService;

  public bmtPayRecords: IBmtPayRecord[] = [];
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.bmtChangCiId) {
        vm.retrieveBmtChangCi(to.params.bmtChangCiId);
      }
      vm.initRelationships();
    });
  }

  public save(): void {
    this.isSaving = true;
    if (this.bmtChangCi.id) {
      this.bmtChangCiService()
        .update(this.bmtChangCi)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A BmtChangCi is updated with identifier ' + param.id;
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.bmtChangCiService()
        .create(this.bmtChangCi)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A BmtChangCi is created with identifier ' + param.id;
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
      this.bmtChangCi[field] = parse(event.target.value, DATE_TIME_LONG_FORMAT, new Date());
    } else {
      this.bmtChangCi[field] = null;
    }
  }

  public updateZonedDateTimeField(field, event) {
    if (event.target.value) {
      this.bmtChangCi[field] = parse(event.target.value, DATE_TIME_LONG_FORMAT, new Date());
    } else {
      this.bmtChangCi[field] = null;
    }
  }

  public retrieveBmtChangCi(bmtChangCiId): void {
    this.bmtChangCiService()
      .find(bmtChangCiId)
      .then(res => {
        res.timeBegin = new Date(res.timeBegin);
        res.timeEnd = new Date(res.timeEnd);
        this.bmtChangCi = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.bmtPayRecordService()
      .retrieve()
      .then(res => {
        this.bmtPayRecords = res.data;
      });
  }
}
