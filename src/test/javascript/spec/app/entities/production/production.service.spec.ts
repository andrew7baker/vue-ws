/* tslint:disable max-line-length */
import axios from 'axios';

import * as config from '@/shared/config/config';
import {} from '@/shared/date/filters';
import ProductionService from '@/entities/production/production.service';
import { Production } from '@/shared/model/production.model';

const mockedAxios: any = axios;
jest.mock('axios', () => ({
  get: jest.fn(),
  post: jest.fn(),
  put: jest.fn(),
  delete: jest.fn()
}));

describe('Service Tests', () => {
  describe('Production Service', () => {
    let service: ProductionService;
    let elemDefault;
    beforeEach(() => {
      service = new ProductionService();

      elemDefault = new Production(0, 'AAAAAAA', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign({}, elemDefault);
        mockedAxios.get.mockReturnValue(Promise.resolve({ data: returnedFromService }));

        return service.find(123).then(res => {
          expect(res).toMatchObject(elemDefault);
        });
      });
      it('should create a Production', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);

        mockedAxios.post.mockReturnValue(Promise.resolve({ data: returnedFromService }));
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should update a Production', async () => {
        const returnedFromService = Object.assign(
          {
            version: 'BBBBBB',
            powerTime: 1,
            placeTime: 1,
            waitTime: 1,
            runTime: 1,
            idleTime: 1,
            inWaitTime: 1,
            wrongStopTime: 1,
            errorStopTIme: 1,
            wrongStopCount: 1,
            errorStopCount: 1,
            panelInCount: 1,
            panelCount: 1,
            pCBCount: 1,
            errorPcb: 1,
            skipPCB: 1,
            operationRate: 1,
            placementRate: 1,
            meanTime: 1,
            realTime: 1,
            transferTime: 1,
            placeCount: 1,
            theEfficiency: 1
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);
        mockedAxios.put.mockReturnValue(Promise.resolve({ data: returnedFromService }));

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });
      it('should return a list of Production', async () => {
        const returnedFromService = Object.assign(
          {
            version: 'BBBBBB',
            powerTime: 1,
            placeTime: 1,
            waitTime: 1,
            runTime: 1,
            idleTime: 1,
            inWaitTime: 1,
            wrongStopTime: 1,
            errorStopTIme: 1,
            wrongStopCount: 1,
            errorStopCount: 1,
            panelInCount: 1,
            panelCount: 1,
            pCBCount: 1,
            errorPcb: 1,
            skipPCB: 1,
            operationRate: 1,
            placementRate: 1,
            meanTime: 1,
            realTime: 1,
            transferTime: 1,
            placeCount: 1,
            theEfficiency: 1
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        mockedAxios.get.mockReturnValue(Promise.resolve([returnedFromService]));
        return service.retrieve({ sort: {}, page: 0, size: 10 }).then(res => {
          expect(res).toContainEqual(expected);
        });
      });
      it('should delete a Production', async () => {
        mockedAxios.delete.mockReturnValue(Promise.resolve({ ok: true }));
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });
    });
  });
});
