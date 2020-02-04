export interface ISysRelation {
  id?: number;
  fromId?: number;
  toId?: number;
  typeCode?: string;
}

export class SysRelation implements ISysRelation {
  constructor(public id?: number, public fromId?: number, public toId?: number, public typeCode?: string) {}
}
