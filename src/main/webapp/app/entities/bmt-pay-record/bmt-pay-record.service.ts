import axios from 'axios';

import buildPaginationQueryOpts from '@/shared/sort/sorts';

import { IBmtPayRecord } from '@/shared/model/bmt-pay-record.model';

const baseApiUrl = 'api/bmt-pay-records';

export default class BmtPayRecordService {
  public find(id: number): Promise<IBmtPayRecord> {
    return new Promise<IBmtPayRecord>(resolve => {
      axios.get(`${baseApiUrl}/${id}`).then(function(res) {
        resolve(res.data);
      });
    });
  }

  public retrieve(paginationQuery?: any): Promise<any> {
    return new Promise<any>(resolve => {
      axios.get(baseApiUrl + `?${buildPaginationQueryOpts(paginationQuery)}`).then(function(res) {
        resolve(res);
      });
    });
  }

  public delete(id: number): Promise<any> {
    return new Promise<any>(resolve => {
      axios.delete(`${baseApiUrl}/${id}`).then(function(res) {
        resolve(res);
      });
    });
  }

  public create(entity: IBmtPayRecord): Promise<IBmtPayRecord> {
    return new Promise<IBmtPayRecord>(resolve => {
      axios.post(`${baseApiUrl}`, entity).then(function(res) {
        resolve(res.data);
      });
    });
  }

  public update(entity: IBmtPayRecord): Promise<IBmtPayRecord> {
    return new Promise<IBmtPayRecord>(resolve => {
      axios.put(`${baseApiUrl}`, entity).then(function(res) {
        resolve(res.data);
      });
    });
  }
}
