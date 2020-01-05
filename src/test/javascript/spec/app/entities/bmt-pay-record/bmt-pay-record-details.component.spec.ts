/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import BmtPayRecordDetailComponent from '@/entities/bmt-pay-record/bmt-pay-record-details.vue';
import BmtPayRecordClass from '@/entities/bmt-pay-record/bmt-pay-record-details.component';
import BmtPayRecordService from '@/entities/bmt-pay-record/bmt-pay-record.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('BmtPayRecord Management Detail Component', () => {
    let wrapper: Wrapper<BmtPayRecordClass>;
    let comp: BmtPayRecordClass;
    let bmtPayRecordServiceStub: SinonStubbedInstance<BmtPayRecordService>;

    beforeEach(() => {
      bmtPayRecordServiceStub = sinon.createStubInstance<BmtPayRecordService>(BmtPayRecordService);

      wrapper = shallowMount<BmtPayRecordClass>(BmtPayRecordDetailComponent, {
        store,
        localVue,
        provide: { bmtPayRecordService: () => bmtPayRecordServiceStub }
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundBmtPayRecord = { id: 123 };
        bmtPayRecordServiceStub.find.resolves(foundBmtPayRecord);

        // WHEN
        comp.retrieveBmtPayRecord(123);
        await comp.$nextTick();

        // THEN
        expect(comp.bmtPayRecord).toBe(foundBmtPayRecord);
      });
    });
  });
});
