/* tslint:disable max-line-length */
import axios from 'axios';
import { format } from 'date-fns';

import * as config from '@/shared/config/config';
import { DATE_TIME_FORMAT } from '@/shared/date/filters';
import SysOperationLogService from '@/entities/sys-operation-log/sys-operation-log.service';
import { SysOperationLog } from '@/shared/model/sys-operation-log.model';

const mockedAxios: any = axios;
jest.mock('axios', () => ({
  get: jest.fn(),
  post: jest.fn(),
  put: jest.fn(),
  delete: jest.fn()
}));

describe('Service Tests', () => {
  describe('SysOperationLog Service', () => {
    let service: SysOperationLogService;
    let elemDefault;
    let currentDate: Date;
    beforeEach(() => {
      service = new SysOperationLogService();
      currentDate = new Date();

      elemDefault = new SysOperationLog(0, 'AAAAAAA', 'AAAAAAA', 0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', currentDate, 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            createTime: format(currentDate, DATE_TIME_FORMAT)
          },
          elemDefault
        );
        mockedAxios.get.mockReturnValue(Promise.resolve({ data: returnedFromService }));

        return service.find(123).then(res => {
          expect(res).toMatchObject(elemDefault);
        });
      });
      it('should create a SysOperationLog', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            createTime: format(currentDate, DATE_TIME_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            createTime: currentDate
          },
          returnedFromService
        );

        mockedAxios.post.mockReturnValue(Promise.resolve({ data: returnedFromService }));
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should update a SysOperationLog', async () => {
        const returnedFromService = Object.assign(
          {
            logType: 'BBBBBB',
            logName: 'BBBBBB',
            userId: 1,
            className: 'BBBBBB',
            method: 'BBBBBB',
            succeed: 'BBBBBB',
            createTime: format(currentDate, DATE_TIME_FORMAT),
            message: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            createTime: currentDate
          },
          returnedFromService
        );
        mockedAxios.put.mockReturnValue(Promise.resolve({ data: returnedFromService }));

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });
      it('should return a list of SysOperationLog', async () => {
        const returnedFromService = Object.assign(
          {
            logType: 'BBBBBB',
            logName: 'BBBBBB',
            userId: 1,
            className: 'BBBBBB',
            method: 'BBBBBB',
            succeed: 'BBBBBB',
            createTime: format(currentDate, DATE_TIME_FORMAT),
            message: 'BBBBBB'
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            createTime: currentDate
          },
          returnedFromService
        );
        mockedAxios.get.mockReturnValue(Promise.resolve([returnedFromService]));
        return service.retrieve({ sort: {}, page: 0, size: 10 }).then(res => {
          expect(res).toContainEqual(expected);
        });
      });
      it('should delete a SysOperationLog', async () => {
        mockedAxios.delete.mockReturnValue(Promise.resolve({ ok: true }));
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });
    });
  });
});
