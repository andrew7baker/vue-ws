/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import BmtChangCiComponent from '@/entities/bmt-chang-ci/bmt-chang-ci.vue';
import BmtChangCiClass from '@/entities/bmt-chang-ci/bmt-chang-ci.component';
import BmtChangCiService from '@/entities/bmt-chang-ci/bmt-chang-ci.service';

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
  describe('BmtChangCi Management Component', () => {
    let wrapper: Wrapper<BmtChangCiClass>;
    let comp: BmtChangCiClass;
    let bmtChangCiServiceStub: SinonStubbedInstance<BmtChangCiService>;

    beforeEach(() => {
      bmtChangCiServiceStub = sinon.createStubInstance<BmtChangCiService>(BmtChangCiService);
      bmtChangCiServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<BmtChangCiClass>(BmtChangCiComponent, {
        store,
        localVue,
        stubs: { jhiItemCount: true, bPagination: true, bModal: bModalStub as any },
        provide: {
          alertService: () => new AlertService(store),
          bmtChangCiService: () => bmtChangCiServiceStub
        }
      });
      comp = wrapper.vm;
    });

    it('should be a Vue instance', () => {
      expect(wrapper.isVueInstance()).toBeTruthy();
    });

    it('Should call load all on init', async () => {
      // GIVEN
      bmtChangCiServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllBmtChangCis();
      await comp.$nextTick();

      // THEN
      expect(bmtChangCiServiceStub.retrieve.called).toBeTruthy();
      expect(comp.bmtChangCis[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should load a page', async () => {
      // GIVEN
      bmtChangCiServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });
      comp.previousPage = 1;

      // WHEN
      comp.loadPage(2);
      await comp.$nextTick();

      // THEN
      expect(bmtChangCiServiceStub.retrieve.called).toBeTruthy();
      expect(comp.bmtChangCis[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should not load a page if the page is the same as the previous page', () => {
      // GIVEN
      bmtChangCiServiceStub.retrieve.reset();
      comp.previousPage = 1;

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(bmtChangCiServiceStub.retrieve.called).toBeFalsy();
    });

    it('should re-initialize the page', async () => {
      // GIVEN
      bmtChangCiServiceStub.retrieve.reset();
      bmtChangCiServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.loadPage(2);
      await comp.$nextTick();
      comp.clear();
      await comp.$nextTick();

      // THEN
      expect(bmtChangCiServiceStub.retrieve.callCount).toEqual(3);
      expect(comp.page).toEqual(1);
      expect(comp.bmtChangCis[0]).toEqual(jasmine.objectContaining({ id: 123 }));
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
      bmtChangCiServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      comp.removeBmtChangCi();
      await comp.$nextTick();

      // THEN
      expect(bmtChangCiServiceStub.delete.called).toBeTruthy();
      expect(bmtChangCiServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
