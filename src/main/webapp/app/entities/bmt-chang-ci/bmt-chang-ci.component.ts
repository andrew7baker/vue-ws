import { mixins } from 'vue-class-component';

import { Component, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IBmtChangCi } from '@/shared/model/bmt-chang-ci.model';
import AlertMixin from '@/shared/alert/alert.mixin';

import BmtChangCiService from './bmt-chang-ci.service';

@Component
export default class BmtChangCi extends mixins(Vue2Filters.mixin, AlertMixin) {
  @Inject('bmtChangCiService') private bmtChangCiService: () => BmtChangCiService;
  private removeId: number = null;
  public itemsPerPage = 20;
  public queryCount: number = null;
  public page = 1;
  public previousPage = 1;
  public propOrder = 'id';
  public reverse = false;
  public totalItems = 0;
  public bmtChangCis: IBmtChangCi[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllBmtChangCis();
  }

  public clear(): void {
    this.page = 1;
    this.retrieveAllBmtChangCis();
  }

  public retrieveAllBmtChangCis(): void {
    this.isFetching = true;

    const paginationQuery = {
      page: this.page - 1,
      size: this.itemsPerPage,
      sort: this.sort()
    };
    this.bmtChangCiService()
      .retrieve(paginationQuery)
      .then(
        res => {
          this.bmtChangCis = res.data;
          this.totalItems = Number(res.headers['x-total-count']);
          this.queryCount = this.totalItems;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public prepareRemove(instance: IBmtChangCi): void {
    this.removeId = instance.id;
  }

  public removeBmtChangCi(): void {
    this.bmtChangCiService()
      .delete(this.removeId)
      .then(() => {
        const message = 'A BmtChangCi is deleted with identifier ' + this.removeId;
        this.alertService().showAlert(message, 'danger');
        this.getAlertFromStore();

        this.removeId = null;
        this.retrieveAllBmtChangCis();
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
    this.retrieveAllBmtChangCis();
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
