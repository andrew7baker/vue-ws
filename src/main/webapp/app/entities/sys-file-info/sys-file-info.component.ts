import { mixins } from 'vue-class-component';

import { Component, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ISysFileInfo } from '@/shared/model/sys-file-info.model';
import AlertMixin from '@/shared/alert/alert.mixin';

import SysFileInfoService from './sys-file-info.service';

@Component
export default class SysFileInfo extends mixins(Vue2Filters.mixin, AlertMixin) {
  @Inject('sysFileInfoService') private sysFileInfoService: () => SysFileInfoService;
  private removeId: number = null;
  public itemsPerPage = 20;
  public queryCount: number = null;
  public page = 1;
  public previousPage = 1;
  public propOrder = 'id';
  public reverse = false;
  public totalItems = 0;
  public sysFileInfos: ISysFileInfo[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllSysFileInfos();
  }

  public clear(): void {
    this.page = 1;
    this.retrieveAllSysFileInfos();
  }

  public retrieveAllSysFileInfos(): void {
    this.isFetching = true;

    const paginationQuery = {
      page: this.page - 1,
      size: this.itemsPerPage,
      sort: this.sort()
    };
    this.sysFileInfoService()
      .retrieve(paginationQuery)
      .then(
        res => {
          this.sysFileInfos = res.data;
          this.totalItems = Number(res.headers['x-total-count']);
          this.queryCount = this.totalItems;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public prepareRemove(instance: ISysFileInfo): void {
    this.removeId = instance.id;
  }

  public removeSysFileInfo(): void {
    this.sysFileInfoService()
      .delete(this.removeId)
      .then(() => {
        const message = 'A SysFileInfo is deleted with identifier ' + this.removeId;
        this.alertService().showAlert(message, 'danger');
        this.getAlertFromStore();

        this.removeId = null;
        this.retrieveAllSysFileInfos();
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
    this.retrieveAllSysFileInfos();
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
