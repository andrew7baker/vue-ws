import { mixins } from 'vue-class-component';

import { Component, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ISysRelationType } from '@/shared/model/sys-relation-type.model';
import AlertMixin from '@/shared/alert/alert.mixin';

import SysRelationTypeService from './sys-relation-type.service';

@Component
export default class SysRelationType extends mixins(Vue2Filters.mixin, AlertMixin) {
  @Inject('sysRelationTypeService') private sysRelationTypeService: () => SysRelationTypeService;
  private removeId: number = null;
  public itemsPerPage = 20;
  public queryCount: number = null;
  public page = 1;
  public previousPage = 1;
  public propOrder = 'id';
  public reverse = false;
  public totalItems = 0;
  public sysRelationTypes: ISysRelationType[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllSysRelationTypes();
  }

  public clear(): void {
    this.page = 1;
    this.retrieveAllSysRelationTypes();
  }

  public retrieveAllSysRelationTypes(): void {
    this.isFetching = true;

    const paginationQuery = {
      page: this.page - 1,
      size: this.itemsPerPage,
      sort: this.sort()
    };
    this.sysRelationTypeService()
      .retrieve(paginationQuery)
      .then(
        res => {
          this.sysRelationTypes = res.data;
          this.totalItems = Number(res.headers['x-total-count']);
          this.queryCount = this.totalItems;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public prepareRemove(instance: ISysRelationType): void {
    this.removeId = instance.id;
  }

  public removeSysRelationType(): void {
    this.sysRelationTypeService()
      .delete(this.removeId)
      .then(() => {
        const message = 'A SysRelationType is deleted with identifier ' + this.removeId;
        this.alertService().showAlert(message, 'danger');
        this.getAlertFromStore();

        this.removeId = null;
        this.retrieveAllSysRelationTypes();
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
    this.retrieveAllSysRelationTypes();
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
