/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import SysRelationTypeComponent from '@/entities/sys-relation-type/sys-relation-type.vue';
import SysRelationTypeClass from '@/entities/sys-relation-type/sys-relation-type.component';
import SysRelationTypeService from '@/entities/sys-relation-type/sys-relation-type.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('b-alert', {});
localVue.component('b-badge', {});
localVue.directive('b-modal', {});
localVue.component('b-button', {});
localVue.component('router-link', {});

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {}
  }
};

describe('Component Tests', () => {
  describe('SysRelationType Management Component', () => {
    let wrapper: Wrapper<SysRelationTypeClass>;
    let comp: SysRelationTypeClass;
    let sysRelationTypeServiceStub: SinonStubbedInstance<SysRelationTypeService>;

    beforeEach(() => {
      sysRelationTypeServiceStub = sinon.createStubInstance<SysRelationTypeService>(SysRelationTypeService);
      sysRelationTypeServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<SysRelationTypeClass>(SysRelationTypeComponent, {
        store,
        localVue,
        stubs: { jhiItemCount: true, bPagination: true, bModal: bModalStub as any },
        provide: {
          alertService: () => new AlertService(store),
          sysRelationTypeService: () => sysRelationTypeServiceStub
        }
      });
      comp = wrapper.vm;
    });

    it('should be a Vue instance', () => {
      expect(wrapper.isVueInstance()).toBeTruthy();
    });

    it('Should call load all on init', async () => {
      // GIVEN
      sysRelationTypeServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllSysRelationTypes();
      await comp.$nextTick();

      // THEN
      expect(sysRelationTypeServiceStub.retrieve.called).toBeTruthy();
      expect(comp.sysRelationTypes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should load a page', async () => {
      // GIVEN
      sysRelationTypeServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });
      comp.previousPage = 1;

      // WHEN
      comp.loadPage(2);
      await comp.$nextTick();

      // THEN
      expect(sysRelationTypeServiceStub.retrieve.called).toBeTruthy();
      expect(comp.sysRelationTypes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should not load a page if the page is the same as the previous page', () => {
      // GIVEN
      sysRelationTypeServiceStub.retrieve.reset();
      comp.previousPage = 1;

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(sysRelationTypeServiceStub.retrieve.called).toBeFalsy();
    });

    it('should re-initialize the page', async () => {
      // GIVEN
      sysRelationTypeServiceStub.retrieve.reset();
      sysRelationTypeServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.loadPage(2);
      await comp.$nextTick();
      comp.clear();
      await comp.$nextTick();

      // THEN
      expect(sysRelationTypeServiceStub.retrieve.callCount).toEqual(3);
      expect(comp.page).toEqual(1);
      expect(comp.sysRelationTypes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should calculate the sort attribute for an id', () => {
      // WHEN
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['id,desc']);
    });

    it('should calculate the sort attribute for a non-id attribute', () => {
      // GIVEN
      comp.propOrder = 'name';

      // WHEN
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['name,desc', 'id']);
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      sysRelationTypeServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      comp.removeSysRelationType();
      await comp.$nextTick();

      // THEN
      expect(sysRelationTypeServiceStub.delete.called).toBeTruthy();
      expect(sysRelationTypeServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
