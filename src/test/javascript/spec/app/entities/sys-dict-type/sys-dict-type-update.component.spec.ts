/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import format from 'date-fns/format';
import parseISO from 'date-fns/parseISO';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import SysDictTypeUpdateComponent from '@/entities/sys-dict-type/sys-dict-type-update.vue';
import SysDictTypeClass from '@/entities/sys-dict-type/sys-dict-type-update.component';
import SysDictTypeService from '@/entities/sys-dict-type/sys-dict-type.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});

describe('Component Tests', () => {
  describe('SysDictType Management Update Component', () => {
    let wrapper: Wrapper<SysDictTypeClass>;
    let comp: SysDictTypeClass;
    let sysDictTypeServiceStub: SinonStubbedInstance<SysDictTypeService>;

    beforeEach(() => {
      sysDictTypeServiceStub = sinon.createStubInstance<SysDictTypeService>(SysDictTypeService);

      wrapper = shallowMount<SysDictTypeClass>(SysDictTypeUpdateComponent, {
        store,
        localVue,
        router,
        provide: {
          alertService: () => new AlertService(store),
          sysDictTypeService: () => sysDictTypeServiceStub
        }
      });
      comp = wrapper.vm;
    });

    describe('load', () => {
      it('Should convert date from string', () => {
        // GIVEN
        const date = new Date('2019-10-15T11:42:02Z');

        // WHEN
        const convertedDate = comp.convertDateTimeFromServer(date);

        // THEN
        expect(convertedDate).toEqual(format(date, DATE_TIME_LONG_FORMAT));
      });

      it('Should not convert date if date is not present', () => {
        expect(comp.convertDateTimeFromServer(null)).toBeNull();
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.sysDictType = entity;
        sysDictTypeServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(sysDictTypeServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.sysDictType = entity;
        sysDictTypeServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(sysDictTypeServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });
  });
});
