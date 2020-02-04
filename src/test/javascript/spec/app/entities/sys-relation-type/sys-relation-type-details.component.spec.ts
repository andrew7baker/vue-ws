/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import SysRelationTypeDetailComponent from '@/entities/sys-relation-type/sys-relation-type-details.vue';
import SysRelationTypeClass from '@/entities/sys-relation-type/sys-relation-type-details.component';
import SysRelationTypeService from '@/entities/sys-relation-type/sys-relation-type.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('SysRelationType Management Detail Component', () => {
    let wrapper: Wrapper<SysRelationTypeClass>;
    let comp: SysRelationTypeClass;
    let sysRelationTypeServiceStub: SinonStubbedInstance<SysRelationTypeService>;

    beforeEach(() => {
      sysRelationTypeServiceStub = sinon.createStubInstance<SysRelationTypeService>(SysRelationTypeService);

      wrapper = shallowMount<SysRelationTypeClass>(SysRelationTypeDetailComponent, {
        store,
        localVue,
        provide: { sysRelationTypeService: () => sysRelationTypeServiceStub }
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundSysRelationType = { id: 123 };
        sysRelationTypeServiceStub.find.resolves(foundSysRelationType);

        // WHEN
        comp.retrieveSysRelationType(123);
        await comp.$nextTick();

        // THEN
        expect(comp.sysRelationType).toBe(foundSysRelationType);
      });
    });
  });
});
