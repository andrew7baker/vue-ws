/* tslint:disable max-line-length */
import axios from 'axios';
import { format } from 'date-fns';

import * as config from '@/shared/config/config';
import { DATE_TIME_FORMAT } from '@/shared/date/filters';
import SysDictService from '@/entities/sys-dict/sys-dict.service';
import { SysDict } from '@/shared/model/sys-dict.model';

const mockedAxios: any = axios;
jest.mock('axios', () => ({
  get: jest.fn(),
  post: jest.fn(),
  put: jest.fn(),
  delete: jest.fn()
}));

describe('Service Tests', () => {
  describe('SysDict Service', () => {
    let service: SysDictService;
    let elemDefault;
    let currentDate: Date;
    beforeEach(() => {
      service = new SysDictService();
      currentDate = new Date();

      elemDefault = new SysDict(0, 0, 'AAAAAAA', 'AAAAAAA', 0, 'AAAAAAA', 'AAAAAAA', 0, 'AAAAAAA', currentDate, 0, currentDate, 0);
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            createTime: format(currentDate, DATE_TIME_FORMAT),
            updateTime: format(currentDate, DATE_TIME_FORMAT)
          },
          elemDefault
        );
        mockedAxios.get.mockReturnValue(Promise.resolve({ data: returnedFromService }));

        return service.find(123).then(res => {
          expect(res).toMatchObject(elemDefault);
        });
      });
      it('should create a SysDict', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            createTime: format(currentDate, DATE_TIME_FORMAT),
            updateTime: format(currentDate, DATE_TIME_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            createTime: currentDate,
            updateTime: currentDate
          },
          returnedFromService
        );

        mockedAxios.post.mockReturnValue(Promise.resolve({ data: returnedFromService }));
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should update a SysDict', async () => {
        const returnedFromService = Object.assign(
          {
            dicTypeId: 1,
            code: 'BBBBBB',
            name: 'BBBBBB',
            parentId: 1,
            parentIds: 'BBBBBB',
            status: 'BBBBBB',
            sort: 1,
            description: 'BBBBBB',
            createTime: format(currentDate, DATE_TIME_FORMAT),
            createUser: 1,
            updateTime: format(currentDate, DATE_TIME_FORMAT),
            updateUser: 1
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            createTime: currentDate,
            updateTime: currentDate
          },
          returnedFromService
        );
        mockedAxios.put.mockReturnValue(Promise.resolve({ data: returnedFromService }));

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });
      it('should return a list of SysDict', async () => {
        const returnedFromService = Object.assign(
          {
            dicTypeId: 1,
            code: 'BBBBBB',
            name: 'BBBBBB',
            parentId: 1,
            parentIds: 'BBBBBB',
            status: 'BBBBBB',
            sort: 1,
            description: 'BBBBBB',
            createTime: format(currentDate, DATE_TIME_FORMAT),
            createUser: 1,
            updateTime: format(currentDate, DATE_TIME_FORMAT),
            updateUser: 1
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            createTime: currentDate,
            updateTime: currentDate
          },
          returnedFromService
        );
        mockedAxios.get.mockReturnValue(Promise.resolve([returnedFromService]));
        return service.retrieve({ sort: {}, page: 0, size: 10 }).then(res => {
          expect(res).toContainEqual(expected);
        });
      });
      it('should delete a SysDict', async () => {
        mockedAxios.delete.mockReturnValue(Promise.resolve({ ok: true }));
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });
    });
  });
});
