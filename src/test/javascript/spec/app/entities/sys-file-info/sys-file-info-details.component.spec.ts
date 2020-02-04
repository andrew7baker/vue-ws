/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import SysFileInfoDetailComponent from '@/entities/sys-file-info/sys-file-info-details.vue';
import SysFileInfoClass from '@/entities/sys-file-info/sys-file-info-details.component';
import SysFileInfoService from '@/entities/sys-file-info/sys-file-info.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('SysFileInfo Management Detail Component', () => {
    let wrapper: Wrapper<SysFileInfoClass>;
    let comp: SysFileInfoClass;
    let sysFileInfoServiceStub: SinonStubbedInstance<SysFileInfoService>;

    beforeEach(() => {
      sysFileInfoServiceStub = sinon.createStubInstance<SysFileInfoService>(SysFileInfoService);

      wrapper = shallowMount<SysFileInfoClass>(SysFileInfoDetailComponent, {
        store,
        localVue,
        provide: { sysFileInfoService: () => sysFileInfoServiceStub }
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundSysFileInfo = { id: 123 };
        sysFileInfoServiceStub.find.resolves(foundSysFileInfo);

        // WHEN
        comp.retrieveSysFileInfo(123);
        await comp.$nextTick();

        // THEN
        expect(comp.sysFileInfo).toBe(foundSysFileInfo);
      });
    });
  });
});
