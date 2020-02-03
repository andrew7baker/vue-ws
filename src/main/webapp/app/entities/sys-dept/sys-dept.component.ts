import { mixins } from 'vue-class-component';

import { Component, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ISysDept } from '@/shared/model/sys-dept.model';
import AlertMixin from '@/shared/alert/alert.mixin';

import SysDeptService from './sys-dept.service';

@Component
export default class SysDept extends mixins(Vue2Filters.mixin, AlertMixin) {
  @Inject('sysDeptService') private sysDeptService: () => SysDeptService;
  private removeId: number = null;
  public itemsPerPage = 20;
  public queryCount: number = null;
  public page = 1;
  public previousPage = 1;
  public propOrder = 'id';
  public reverse = false;
  public totalItems = 0;
  public sysDepts: ISysDept[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllSysDepts();
  }

  public clear(): void {
    this.page = 1;
    this.retrieveAllSysDepts();
  }

  public retrieveAllSysDepts(): void {
    this.isFetching = true;

    const paginationQuery = {
      page: this.page - 1,
      size: this.itemsPerPage,
      sort: this.sort()
    };
    this.sysDeptService()
      .retrieve(paginationQuery)
      .then(
        res => {
          this.sysDepts = res.data;
          this.totalItems = Number(res.headers['x-total-count']);
          this.queryCount = this.totalItems;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public prepareRemove(instance: ISysDept): void {
    this.removeId = instance.id;
  }

  public removeSysDept(): void {
    this.sysDeptService()
      .delete(this.removeId)
      .then(() => {
        const message = 'A SysDept is deleted with identifier ' + this.removeId;
        this.alertService().showAlert(message, 'danger');
        this.getAlertFromStore();

        this.removeId = null;
        this.retrieveAllSysDepts();
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
    this.retrieveAllSysDepts();
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
