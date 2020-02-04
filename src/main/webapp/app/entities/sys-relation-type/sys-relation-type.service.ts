import axios from 'axios';

import buildPaginationQueryOpts from '@/shared/sort/sorts';

import { ISysRelationType } from '@/shared/model/sys-relation-type.model';

const baseApiUrl = 'api/sys-relation-types';

export default class SysRelationTypeService {
  public find(id: number): Promise<ISysRelationType> {
    return new Promise<ISysRelationType>(resolve => {
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

  public create(entity: ISysRelationType): Promise<ISysRelationType> {
    return new Promise<ISysRelationType>(resolve => {
      axios.post(`${baseApiUrl}`, entity).then(function(res) {
        resolve(res.data);
      });
    });
  }

  public update(entity: ISysRelationType): Promise<ISysRelationType> {
    return new Promise<ISysRelationType>(resolve => {
      axios.put(`${baseApiUrl}`, entity).then(function(res) {
        resolve(res.data);
      });
    });
  }
}
