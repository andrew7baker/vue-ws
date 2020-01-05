/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import format from 'date-fns/format';
import parseISO from 'date-fns/parseISO';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import BmtChangCiUpdateComponent from '@/entities/bmt-chang-ci/bmt-chang-ci-update.vue';
import BmtChangCiClass from '@/entities/bmt-chang-ci/bmt-chang-ci-update.component';
import BmtChangCiService from '@/entities/bmt-chang-ci/bmt-chang-ci.service';

import BmtPayRecordService from '@/entities/bmt-pay-record/bmt-pay-record.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});

describe('Component Tests', () => {
  describe('BmtChangCi Management Update Component', () => {
    let wrapper: Wrapper<BmtChangCiClass>;
    let comp: BmtChangCiClass;
    let bmtChangCiServiceStub: SinonStubbedInstance<BmtChangCiService>;

    beforeEach(() => {
      bmtChangCiServiceStub = sinon.createStubInstance<BmtChangCiService>(BmtChangCiService);

      wrapper = shallowMount<BmtChangCiClass>(BmtChangCiUpdateComponent, {
        store,
        localVue,
        router,
        provide: {
          alertService: () => new AlertService(store),
          bmtChangCiService: () => bmtChangCiServiceStub,

          bmtPayRecordService: () => new BmtPayRecordService()
        }
      });
      comp = wrapper.vm;
    });

    describe('load', () => {
      it('Should convert date from string', () => {
        // GIVEN
        const date = new Date('2019-10-15T11:42:02Z');

        // WHEN
        const convertedDate = comp.convertDateTimeFromServer(date);

        // THEN
        expect(convertedDate).toEqual(format(date, DATE_TIME_LONG_FORMAT));
      });

      it('Should not convert date if date is not present', () => {
        expect(comp.convertDateTimeFromServer(null)).toBeNull();
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.bmtChangCi = entity;
        bmtChangCiServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(bmtChangCiServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.bmtChangCi = entity;
        bmtChangCiServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(bmtChangCiServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });
  });
});
