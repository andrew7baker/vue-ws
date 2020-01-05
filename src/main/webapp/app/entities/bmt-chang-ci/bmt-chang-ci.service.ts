import axios from 'axios';

import buildPaginationQueryOpts from '@/shared/sort/sorts';

import { IBmtChangCi } from '@/shared/model/bmt-chang-ci.model';

const baseApiUrl = 'api/bmt-chang-cis';

export default class BmtChangCiService {
  public find(id: number): Promise<IBmtChangCi> {
    return new Promise<IBmtChangCi>(resolve => {
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

  public create(entity: IBmtChangCi): Promise<IBmtChangCi> {
    return new Promise<IBmtChangCi>(resolve => {
      axios.post(`${baseApiUrl}`, entity).then(function(res) {
        resolve(res.data);
      });
    });
  }

  public update(entity: IBmtChangCi): Promise<IBmtChangCi> {
    return new Promise<IBmtChangCi>(resolve => {
      axios.put(`${baseApiUrl}`, entity).then(function(res) {
        resolve(res.data);
      });
    });
  }
}
