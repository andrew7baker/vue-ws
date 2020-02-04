import axios from 'axios';

import buildPaginationQueryOpts from '@/shared/sort/sorts';

import { ISysFileInfo } from '@/shared/model/sys-file-info.model';

const baseApiUrl = 'api/sys-file-infos';

export default class SysFileInfoService {
  public find(id: number): Promise<ISysFileInfo> {
    return new Promise<ISysFileInfo>(resolve => {
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

  public create(entity: ISysFileInfo): Promise<ISysFileInfo> {
    return new Promise<ISysFileInfo>(resolve => {
      axios.post(`${baseApiUrl}`, entity).then(function(res) {
        resolve(res.data);
      });
    });
  }

  public update(entity: ISysFileInfo): Promise<ISysFileInfo> {
    return new Promise<ISysFileInfo>(resolve => {
      axios.put(`${baseApiUrl}`, entity).then(function(res) {
        resolve(res.data);
      });
    });
  }
}
