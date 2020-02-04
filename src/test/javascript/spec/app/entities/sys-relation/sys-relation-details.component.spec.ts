/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import SysRelationDetailComponent from '@/entities/sys-relation/sys-relation-details.vue';
import SysRelationClass from '@/entities/sys-relation/sys-relation-details.component';
import SysRelationService from '@/entities/sys-relation/sys-relation.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('SysRelation Management Detail Component', () => {
    let wrapper: Wrapper<SysRelationClass>;
    let comp: SysRelationClass;
    let sysRelationServiceStub: SinonStubbedInstance<SysRelationService>;

    beforeEach(() => {
      sysRelationServiceStub = sinon.createStubInstance<SysRelationService>(SysRelationService);

      wrapper = shallowMount<SysRelationClass>(SysRelationDetailComponent, {
        store,
        localVue,
        provide: { sysRelationService: () => sysRelationServiceStub }
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundSysRelation = { id: 123 };
        sysRelationServiceStub.find.resolves(foundSysRelation);

        // WHEN
        comp.retrieveSysRelation(123);
        await comp.$nextTick();

        // THEN
        expect(comp.sysRelation).toBe(foundSysRelation);
      });
    });
  });
});
