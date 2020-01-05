import { IBmtPayRecord } from '@/shared/model/bmt-pay-record.model';

export interface IBmtChangCi {
  id?: number;
  name?: string;
  weekDay?: string;
  timeBegin?: Date;
  timeEnd?: Date;
  owner?: string;
  userId?: number;
  bmtPayRecords?: IBmtPayRecord[];
}

export class BmtChangCi implements IBmtChangCi {
  constructor(
    public id?: number,
    public name?: string,
    public weekDay?: string,
    public timeBegin?: Date,
    public timeEnd?: Date,
    public owner?: string,
    public userId?: number,
    public bmtPayRecords?: IBmtPayRecord[]
  ) {}
}
