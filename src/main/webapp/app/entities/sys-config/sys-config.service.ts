import axios from 'axios';

import buildPaginationQueryOpts from '@/shared/sort/sorts';

import { ISysConfig } from '@/shared/model/sys-config.model';

const baseApiUrl = 'api/sys-configs';

export default class SysConfigService {
  public find(id: number): Promise<ISysConfig> {
    return new Promise<ISysConfig>(resolve => {
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

  public create(entity: ISysConfig): Promise<ISysConfig> {
    return new Promise<ISysConfig>(resolve => {
      axios.post(`${baseApiUrl}`, entity).then(function(res) {
        resolve(res.data);
      });
    });
  }

  public update(entity: ISysConfig): Promise<ISysConfig> {
    return new Promise<ISysConfig>(resolve => {
      axios.put(`${baseApiUrl}`, entity).then(function(res) {
        resolve(res.data);
      });
    });
  }
}
