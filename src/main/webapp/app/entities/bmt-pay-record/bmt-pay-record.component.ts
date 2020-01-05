import { mixins } from 'vue-class-component';

import { Component, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IBmtPayRecord } from '@/shared/model/bmt-pay-record.model';
import AlertMixin from '@/shared/alert/alert.mixin';

import BmtPayRecordService from './bmt-pay-record.service';

@Component
export default class BmtPayRecord extends mixins(Vue2Filters.mixin, AlertMixin) {
  @Inject('bmtPayRecordService') private bmtPayRecordService: () => BmtPayRecordService;
  private removeId: number = null;
  public itemsPerPage = 20;
  public queryCount: number = null;
  public page = 1;
  public previousPage = 1;
  public propOrder = 'id';
  public reverse = false;
  public totalItems = 0;
  public bmtPayRecords: IBmtPayRecord[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllBmtPayRecords();
  }

  public clear(): void {
    this.page = 1;
    this.retrieveAllBmtPayRecords();
  }

  public retrieveAllBmtPayRecords(): void {
    this.isFetching = true;

    const paginationQuery = {
      page: this.page - 1,
      size: this.itemsPerPage,
      sort: this.sort()
    };
    this.bmtPayRecordService()
      .retrieve(paginationQuery)
      .then(
        res => {
          this.bmtPayRecords = res.data;
          this.totalItems = Number(res.headers['x-total-count']);
          this.queryCount = this.totalItems;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public prepareRemove(instance: IBmtPayRecord): void {
    this.removeId = instance.id;
  }

  public removeBmtPayRecord(): void {
    this.bmtPayRecordService()
      .delete(this.removeId)
      .then(() => {
        const message = 'A BmtPayRecord is deleted with identifier ' + this.removeId;
        this.alertService().showAlert(message, 'danger');
        this.getAlertFromStore();

        this.removeId = null;
        this.retrieveAllBmtPayRecords();
        this.closeDialog();
      });
  }

  public sort(): Array<any> {
    const result = [this.propOrder + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.propOrder !== 'id') {
      result.push('id');
    }
    return result;
  }

  public loadPage(page: number): void {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.transition();
    }
  }

  public transition(): void {
    this.retrieveAllBmtPayRecords();
  }

  public changeOrder(propOrder): void {
    this.propOrder = propOrder;
    this.reverse = !this.reverse;
    this.transition();
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
