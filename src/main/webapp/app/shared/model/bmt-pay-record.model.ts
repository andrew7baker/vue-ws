import { IBmtChangCi } from '@/shared/model/bmt-chang-ci.model';

export interface IBmtPayRecord {
  id?: number;
  payTime?: Date;
  payPersonId?: string;
  payPersonName?: string;
  payAmount?: number;
  bmtChangCi?: IBmtChangCi;
}

export class BmtPayRecord implements IBmtPayRecord {
  constructor(
    public id?: number,
    public payTime?: Date,
    public payPersonId?: string,
    public payPersonName?: string,
    public payAmount?: number,
    public bmtChangCi?: IBmtChangCi
  ) {}
}
