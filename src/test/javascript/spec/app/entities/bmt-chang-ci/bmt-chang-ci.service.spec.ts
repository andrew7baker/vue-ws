/* tslint:disable max-line-length */
import axios from 'axios';
import { format } from 'date-fns';

import * as config from '@/shared/config/config';
import { DATE_TIME_FORMAT } from '@/shared/date/filters';
import BmtChangCiService from '@/entities/bmt-chang-ci/bmt-chang-ci.service';
import { BmtChangCi } from '@/shared/model/bmt-chang-ci.model';

const mockedAxios: any = axios;
jest.mock('axios', () => ({
  get: jest.fn(),
  post: jest.fn(),
  put: jest.fn(),
  delete: jest.fn()
}));

describe('Service Tests', () => {
  describe('BmtChangCi Service', () => {
    let service: BmtChangCiService;
    let elemDefault;
    let currentDate: Date;
    beforeEach(() => {
      service = new BmtChangCiService();
      currentDate = new Date();

      elemDefault = new BmtChangCi(0, 'AAAAAAA', 'AAAAAAA', currentDate, currentDate, 'AAAAAAA', 0);
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            timeBegin: format(currentDate, DATE_TIME_FORMAT),
            timeEnd: format(currentDate, DATE_TIME_FORMAT)
          },
          elemDefault
        );
        mockedAxios.get.mockReturnValue(Promise.resolve({ data: returnedFromService }));

        return service.find(123).then(res => {
          expect(res).toMatchObject(elemDefault);
        });
      });
      it('should create a BmtChangCi', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            timeBegin: format(currentDate, DATE_TIME_FORMAT),
            timeEnd: format(currentDate, DATE_TIME_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            timeBegin: currentDate,
            timeEnd: currentDate
          },
          returnedFromService
        );

        mockedAxios.post.mockReturnValue(Promise.resolve({ data: returnedFromService }));
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should update a BmtChangCi', async () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            weekDay: 'BBBBBB',
            timeBegin: format(currentDate, DATE_TIME_FORMAT),
            timeEnd: format(currentDate, DATE_TIME_FORMAT),
            owner: 'BBBBBB',
            userId: 1
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            timeBegin: currentDate,
            timeEnd: currentDate
          },
          returnedFromService
        );
        mockedAxios.put.mockReturnValue(Promise.resolve({ data: returnedFromService }));

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });
      it('should return a list of BmtChangCi', async () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            weekDay: 'BBBBBB',
            timeBegin: format(currentDate, DATE_TIME_FORMAT),
            timeEnd: format(currentDate, DATE_TIME_FORMAT),
            owner: 'BBBBBB',
            userId: 1
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            timeBegin: currentDate,
            timeEnd: currentDate
          },
          returnedFromService
        );
        mockedAxios.get.mockReturnValue(Promise.resolve([returnedFromService]));
        return service.retrieve({ sort: {}, page: 0, size: 10 }).then(res => {
          expect(res).toContainEqual(expected);
        });
      });
      it('should delete a BmtChangCi', async () => {
        mockedAxios.delete.mockReturnValue(Promise.resolve({ ok: true }));
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });
    });
  });
});
