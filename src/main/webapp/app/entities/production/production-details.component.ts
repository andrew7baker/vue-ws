import { Component, Vue, Inject } from 'vue-property-decorator';

import { IProduction } from '@/shared/model/production.model';
import ProductionService from './production.service';

@Component
export default class ProductionDetails extends Vue {
  @Inject('productionService') private productionService: () => ProductionService;
  public production: IProduction = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.productionId) {
        vm.retrieveProduction(to.params.productionId);
      }
    });
  }

  public retrieveProduction(productionId) {
    this.productionService()
      .find(productionId)
      .then(res => {
        this.production = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
