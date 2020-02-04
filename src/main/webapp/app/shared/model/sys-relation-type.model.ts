export interface ISysRelationType {
  id?: number;
  typeCode?: string;
  typedName?: string;
}

export class SysRelationType implements ISysRelationType {
  constructor(public id?: number, public typeCode?: string, public typedName?: string) {}
}
