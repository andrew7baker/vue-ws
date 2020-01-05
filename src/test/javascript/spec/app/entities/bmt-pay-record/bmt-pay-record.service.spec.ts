/* tslint:disable max-line-length */
import axios from 'axios';
import { format } from 'date-fns';

import * as config from '@/shared/config/config';
import { DATE_TIME_FORMAT } from '@/shared/date/filters';
import BmtPayRecordService from '@/entities/bmt-pay-record/bmt-pay-record.service';
import { BmtPayRecord } from '@/shared/model/bmt-pay-record.model';

const mockedAxios: any = axios;
jest.mock('axios', () => ({
  get: jest.fn(),
  post: jest.fn(),
  put: jest.fn(),
  delete: jest.fn()
}));

describe('Service Tests', () => {
  describe('BmtPayRecord Service', () => {
    let service: BmtPayRecordService;
    let elemDefault;
    let currentDate: Date;
    beforeEach(() => {
      service = new BmtPayRecordService();
      currentDate = new Date();

      elemDefault = new BmtPayRecord(0, currentDate, 'AAAAAAA', 'AAAAAAA', 0);
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            payTime: format(currentDate, DATE_TIME_FORMAT)
          },
          elemDefault
        );
        mockedAxios.get.mockReturnValue(Promise.resolve({ data: returnedFromService }));

        return service.find(123).then(res => {
          expect(res).toMatchObject(elemDefault);
        });
      });
      it('should create a BmtPayRecord', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            payTime: format(currentDate, DATE_TIME_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            payTime: currentDate
          },
          returnedFromService
        );

        mockedAxios.post.mockReturnValue(Promise.resolve({ data: returnedFromService }));
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should update a BmtPayRecord', async () => {
        const returnedFromService = Object.assign(
          {
            payTime: format(currentDate, DATE_TIME_FORMAT),
            payPersonId: 'BBBBBB',
            payPersonName: 'BBBBBB',
            payAmount: 1
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            payTime: currentDate
          },
          returnedFromService
        );
        mockedAxios.put.mockReturnValue(Promise.resolve({ data: returnedFromService }));

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });
      it('should return a list of BmtPayRecord', async () => {
        const returnedFromService = Object.assign(
          {
            payTime: format(currentDate, DATE_TIME_FORMAT),
            payPersonId: 'BBBBBB',
            payPersonName: 'BBBBBB',
            payAmount: 1
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            payTime: currentDate
          },
          returnedFromService
        );
        mockedAxios.get.mockReturnValue(Promise.resolve([returnedFromService]));
        return service.retrieve({ sort: {}, page: 0, size: 10 }).then(res => {
          expect(res).toContainEqual(expected);
        });
      });
      it('should delete a BmtPayRecord', async () => {
        mockedAxios.delete.mockReturnValue(Promise.resolve({ ok: true }));
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });
    });
  });
});
