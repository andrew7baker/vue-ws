export interface ISysConfig {
  id?: number;
  name?: string;
  code?: string;
  dictFlag?: string;
  dictTypeId?: number;
  value?: string;
  remark?: string;
  createTime?: Date;
  createUser?: number;
  updateTime?: Date;
  updateUser?: number;
}

export class SysConfig implements ISysConfig {
  constructor(
    public id?: number,
    public name?: string,
    public code?: string,
    public dictFlag?: string,
    public dictTypeId?: number,
    public value?: string,
    public remark?: string,
    public createTime?: Date,
    public createUser?: number,
    public updateTime?: Date,
    public updateUser?: number
  ) {}
}
