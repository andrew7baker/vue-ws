/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import SysRelationTypeUpdateComponent from '@/entities/sys-relation-type/sys-relation-type-update.vue';
import SysRelationTypeClass from '@/entities/sys-relation-type/sys-relation-type-update.component';
import SysRelationTypeService from '@/entities/sys-relation-type/sys-relation-type.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});

describe('Component Tests', () => {
  describe('SysRelationType Management Update Component', () => {
    let wrapper: Wrapper<SysRelationTypeClass>;
    let comp: SysRelationTypeClass;
    let sysRelationTypeServiceStub: SinonStubbedInstance<SysRelationTypeService>;

    beforeEach(() => {
      sysRelationTypeServiceStub = sinon.createStubInstance<SysRelationTypeService>(SysRelationTypeService);

      wrapper = shallowMount<SysRelationTypeClass>(SysRelationTypeUpdateComponent, {
        store,
        localVue,
        router,
        provide: {
          alertService: () => new AlertService(store),
          sysRelationTypeService: () => sysRelationTypeServiceStub
        }
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.sysRelationType = entity;
        sysRelationTypeServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(sysRelationTypeServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.sysRelationType = entity;
        sysRelationTypeServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(sysRelationTypeServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });
  });
});
