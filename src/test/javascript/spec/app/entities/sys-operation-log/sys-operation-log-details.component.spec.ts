/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import SysOperationLogDetailComponent from '@/entities/sys-operation-log/sys-operation-log-details.vue';
import SysOperationLogClass from '@/entities/sys-operation-log/sys-operation-log-details.component';
import SysOperationLogService from '@/entities/sys-operation-log/sys-operation-log.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('SysOperationLog Management Detail Component', () => {
    let wrapper: Wrapper<SysOperationLogClass>;
    let comp: SysOperationLogClass;
    let sysOperationLogServiceStub: SinonStubbedInstance<SysOperationLogService>;

    beforeEach(() => {
      sysOperationLogServiceStub = sinon.createStubInstance<SysOperationLogService>(SysOperationLogService);

      wrapper = shallowMount<SysOperationLogClass>(SysOperationLogDetailComponent, {
        store,
        localVue,
        provide: { sysOperationLogService: () => sysOperationLogServiceStub }
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundSysOperationLog = { id: 123 };
        sysOperationLogServiceStub.find.resolves(foundSysOperationLog);

        // WHEN
        comp.retrieveSysOperationLog(123);
        await comp.$nextTick();

        // THEN
        expect(comp.sysOperationLog).toBe(foundSysOperationLog);
      });
    });
  });
});
