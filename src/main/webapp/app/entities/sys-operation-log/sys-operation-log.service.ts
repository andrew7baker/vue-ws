import axios from 'axios';

import buildPaginationQueryOpts from '@/shared/sort/sorts';

import { ISysOperationLog } from '@/shared/model/sys-operation-log.model';

const baseApiUrl = 'api/sys-operation-logs';

export default class SysOperationLogService {
  public find(id: number): Promise<ISysOperationLog> {
    return new Promise<ISysOperationLog>(resolve => {
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

  public create(entity: ISysOperationLog): Promise<ISysOperationLog> {
    return new Promise<ISysOperationLog>(resolve => {
      axios.post(`${baseApiUrl}`, entity).then(function(res) {
        resolve(res.data);
      });
    });
  }

  public update(entity: ISysOperationLog): Promise<ISysOperationLog> {
    return new Promise<ISysOperationLog>(resolve => {
      axios.put(`${baseApiUrl}`, entity).then(function(res) {
        resolve(res.data);
      });
    });
  }
}
