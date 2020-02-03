import axios from 'axios';

import buildPaginationQueryOpts from '@/shared/sort/sorts';

import { ISysDict } from '@/shared/model/sys-dict.model';

const baseApiUrl = 'api/sys-dicts';

export default class SysDictService {
  public find(id: number): Promise<ISysDict> {
    return new Promise<ISysDict>(resolve => {
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

  public create(entity: ISysDict): Promise<ISysDict> {
    return new Promise<ISysDict>(resolve => {
      axios.post(`${baseApiUrl}`, entity).then(function(res) {
        resolve(res.data);
      });
    });
  }

  public update(entity: ISysDict): Promise<ISysDict> {
    return new Promise<ISysDict>(resolve => {
      axios.put(`${baseApiUrl}`, entity).then(function(res) {
        resolve(res.data);
      });
    });
  }
}
