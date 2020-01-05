/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import format from 'date-fns/format';
import parseISO from 'date-fns/parseISO';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import BmtPayRecordUpdateComponent from '@/entities/bmt-pay-record/bmt-pay-record-update.vue';
import BmtPayRecordClass from '@/entities/bmt-pay-record/bmt-pay-record-update.component';
import BmtPayRecordService from '@/entities/bmt-pay-record/bmt-pay-record.service';

import BmtChangCiService from '@/entities/bmt-chang-ci/bmt-chang-ci.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});

describe('Component Tests', () => {
  describe('BmtPayRecord Management Update Component', () => {
    let wrapper: Wrapper<BmtPayRecordClass>;
    let comp: BmtPayRecordClass;
    let bmtPayRecordServiceStub: SinonStubbedInstance<BmtPayRecordService>;

    beforeEach(() => {
      bmtPayRecordServiceStub = sinon.createStubInstance<BmtPayRecordService>(BmtPayRecordService);

      wrapper = shallowMount<BmtPayRecordClass>(BmtPayRecordUpdateComponent, {
        store,
        localVue,
        router,
        provide: {
          alertService: () => new AlertService(store),
          bmtPayRecordService: () => bmtPayRecordServiceStub,

          bmtChangCiService: () => new BmtChangCiService()
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
        comp.bmtPayRecord = entity;
        bmtPayRecordServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(bmtPayRecordServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.bmtPayRecord = entity;
        bmtPayRecordServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(bmtPayRecordServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });
  });
});
