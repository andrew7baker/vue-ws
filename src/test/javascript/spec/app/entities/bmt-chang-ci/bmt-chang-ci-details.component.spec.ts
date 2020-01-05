/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import BmtChangCiDetailComponent from '@/entities/bmt-chang-ci/bmt-chang-ci-details.vue';
import BmtChangCiClass from '@/entities/bmt-chang-ci/bmt-chang-ci-details.component';
import BmtChangCiService from '@/entities/bmt-chang-ci/bmt-chang-ci.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('BmtChangCi Management Detail Component', () => {
    let wrapper: Wrapper<BmtChangCiClass>;
    let comp: BmtChangCiClass;
    let bmtChangCiServiceStub: SinonStubbedInstance<BmtChangCiService>;

    beforeEach(() => {
      bmtChangCiServiceStub = sinon.createStubInstance<BmtChangCiService>(BmtChangCiService);

      wrapper = shallowMount<BmtChangCiClass>(BmtChangCiDetailComponent, {
        store,
        localVue,
        provide: { bmtChangCiService: () => bmtChangCiServiceStub }
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundBmtChangCi = { id: 123 };
        bmtChangCiServiceStub.find.resolves(foundBmtChangCi);

        // WHEN
        comp.retrieveBmtChangCi(123);
        await comp.$nextTick();

        // THEN
        expect(comp.bmtChangCi).toBe(foundBmtChangCi);
      });
    });
  });
});
