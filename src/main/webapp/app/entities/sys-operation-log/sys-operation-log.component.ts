import { mixins } from 'vue-class-component';

import { Component, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ISysOperationLog } from '@/shared/model/sys-operation-log.model';
import AlertMixin from '@/shared/alert/alert.mixin';

import SysOperationLogService from './sys-operation-log.service';

@Component
export default class SysOperationLog extends mixins(Vue2Filters.mixin, AlertMixin) {
  @Inject('sysOperationLogService') private sysOperationLogService: () => SysOperationLogService;
  private removeId: number = null;
  public itemsPerPage = 20;
  public queryCount: number = null;
  public page = 1;
  public previousPage = 1;
  public propOrder = 'id';
  public reverse = false;
  public totalItems = 0;
  public sysOperationLogs: ISysOperationLog[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllSysOperationLogs();
  }

  public clear(): void {
    this.page = 1;
    this.retrieveAllSysOperationLogs();
  }

  public retrieveAllSysOperationLogs(): void {
    this.isFetching = true;

    const paginationQuery = {
      page: this.page - 1,
      size: this.itemsPerPage,
      sort: this.sort()
    };
    this.sysOperationLogService()
      .retrieve(paginationQuery)
      .then(
        res => {
          this.sysOperationLogs = res.data;
          this.totalItems = Number(res.headers['x-total-count']);
          this.queryCount = this.totalItems;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public prepareRemove(instance: ISysOperationLog): void {
    this.removeId = instance.id;
  }

  public removeSysOperationLog(): void {
    this.sysOperationLogService()
      .delete(this.removeId)
      .then(() => {
        const message = 'A SysOperationLog is deleted with identifier ' + this.removeId;
        this.alertService().showAlert(message, 'danger');
        this.getAlertFromStore();

        this.removeId = null;
        this.retrieveAllSysOperationLogs();
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
    this.retrieveAllSysOperationLogs();
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
