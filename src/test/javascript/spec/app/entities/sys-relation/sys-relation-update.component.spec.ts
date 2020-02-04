/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import SysRelationUpdateComponent from '@/entities/sys-relation/sys-relation-update.vue';
import SysRelationClass from '@/entities/sys-relation/sys-relation-update.component';
import SysRelationService from '@/entities/sys-relation/sys-relation.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});

describe('Component Tests', () => {
  describe('SysRelation Management Update Component', () => {
    let wrapper: Wrapper<SysRelationClass>;
    let comp: SysRelationClass;
    let sysRelationServiceStub: SinonStubbedInstance<SysRelationService>;

    beforeEach(() => {
      sysRelationServiceStub = sinon.createStubInstance<SysRelationService>(SysRelationService);

      wrapper = shallowMount<SysRelationClass>(SysRelationUpdateComponent, {
        store,
        localVue,
        router,
        provide: {
          alertService: () => new AlertService(store),
          sysRelationService: () => sysRelationServiceStub
        }
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.sysRelation = entity;
        sysRelationServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(sysRelationServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.sysRelation = entity;
        sysRelationServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(sysRelationServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });
  });
});
