/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import ProductionDetailComponent from '@/entities/production/production-details.vue';
import ProductionClass from '@/entities/production/production-details.component';
import ProductionService from '@/entities/production/production.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('Production Management Detail Component', () => {
    let wrapper: Wrapper<ProductionClass>;
    let comp: ProductionClass;
    let productionServiceStub: SinonStubbedInstance<ProductionService>;

    beforeEach(() => {
      productionServiceStub = sinon.createStubInstance<ProductionService>(ProductionService);

      wrapper = shallowMount<ProductionClass>(ProductionDetailComponent, {
        store,
        localVue,
        provide: { productionService: () => productionServiceStub }
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundProduction = { id: 123 };
        productionServiceStub.find.resolves(foundProduction);

        // WHEN
        comp.retrieveProduction(123);
        await comp.$nextTick();

        // THEN
        expect(comp.production).toBe(foundProduction);
      });
    });
  });
});
