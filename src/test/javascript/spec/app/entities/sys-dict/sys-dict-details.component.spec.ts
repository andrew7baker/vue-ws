/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import SysDictDetailComponent from '@/entities/sys-dict/sys-dict-details.vue';
import SysDictClass from '@/entities/sys-dict/sys-dict-details.component';
import SysDictService from '@/entities/sys-dict/sys-dict.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('SysDict Management Detail Component', () => {
    let wrapper: Wrapper<SysDictClass>;
    let comp: SysDictClass;
    let sysDictServiceStub: SinonStubbedInstance<SysDictService>;

    beforeEach(() => {
      sysDictServiceStub = sinon.createStubInstance<SysDictService>(SysDictService);

      wrapper = shallowMount<SysDictClass>(SysDictDetailComponent, {
        store,
        localVue,
        provide: { sysDictService: () => sysDictServiceStub }
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundSysDict = { id: 123 };
        sysDictServiceStub.find.resolves(foundSysDict);

        // WHEN
        comp.retrieveSysDict(123);
        await comp.$nextTick();

        // THEN
        expect(comp.sysDict).toBe(foundSysDict);
      });
    });
  });
});
