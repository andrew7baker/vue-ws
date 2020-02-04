import { mixins } from 'vue-class-component';

import { Component, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ISysRelation } from '@/shared/model/sys-relation.model';
import AlertMixin from '@/shared/alert/alert.mixin';

import SysRelationService from './sys-relation.service';

@Component
export default class SysRelation extends mixins(Vue2Filters.mixin, AlertMixin) {
  @Inject('sysRelationService') private sysRelationService: () => SysRelationService;
  private removeId: number = null;
  public itemsPerPage = 20;
  public queryCount: number = null;
  public page = 1;
  public previousPage = 1;
  public propOrder = 'id';
  public reverse = false;
  public totalItems = 0;
  public sysRelations: ISysRelation[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllSysRelations();
  }

  public clear(): void {
    this.page = 1;
    this.retrieveAllSysRelations();
  }

  public retrieveAllSysRelations(): void {
    this.isFetching = true;

    const paginationQuery = {
      page: this.page - 1,
      size: this.itemsPerPage,
      sort: this.sort()
    };
    this.sysRelationService()
      .retrieve(paginationQuery)
      .then(
        res => {
          this.sysRelations = res.data;
          this.totalItems = Number(res.headers['x-total-count']);
          this.queryCount = this.totalItems;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public prepareRemove(instance: ISysRelation): void {
    this.removeId = instance.id;
  }

  public removeSysRelation(): void {
    this.sysRelationService()
      .delete(this.removeId)
      .then(() => {
        const message = 'A SysRelation is deleted with identifier ' + this.removeId;
        this.alertService().showAlert(message, 'danger');
        this.getAlertFromStore();

        this.removeId = null;
        this.retrieveAllSysRelations();
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
    this.retrieveAllSysRelations();
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
