import axios from 'axios';

import buildPaginationQueryOpts from '@/shared/sort/sorts';

import { IProduction } from '@/shared/model/production.model';

const baseApiUrl = 'api/productions';

export default class ProductionService {
  public find(id: number): Promise<IProduction> {
    return new Promise<IProduction>(resolve => {
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

  public create(entity: IProduction): Promise<IProduction> {
    return new Promise<IProduction>(resolve => {
      axios.post(`${baseApiUrl}`, entity).then(function(res) {
        resolve(res.data);
      });
    });
  }

  public update(entity: IProduction): Promise<IProduction> {
    return new Promise<IProduction>(resolve => {
      axios.put(`${baseApiUrl}`, entity).then(function(res) {
        resolve(res.data);
      });
    });
  }
}
