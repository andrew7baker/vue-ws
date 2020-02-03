import axios from 'axios';

import buildPaginationQueryOpts from '@/shared/sort/sorts';

import { ISysDept } from '@/shared/model/sys-dept.model';

const baseApiUrl = 'api/sys-depts';

export default class SysDeptService {
  public find(id: number): Promise<ISysDept> {
    return new Promise<ISysDept>(resolve => {
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

  public create(entity: ISysDept): Promise<ISysDept> {
    return new Promise<ISysDept>(resolve => {
      axios.post(`${baseApiUrl}`, entity).then(function(res) {
        resolve(res.data);
      });
    });
  }

  public update(entity: ISysDept): Promise<ISysDept> {
    return new Promise<ISysDept>(resolve => {
      axios.put(`${baseApiUrl}`, entity).then(function(res) {
        resolve(res.data);
      });
    });
  }
}
