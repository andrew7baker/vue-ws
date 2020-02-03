export interface ISysDictType {
  id?: number;
  code?: string;
  name?: string;
  systemFlag?: string;
  status?: string;
  sort?: number;
  description?: string;
  createTime?: Date;
  createUser?: number;
  updateTime?: Date;
  updateUser?: number;
}

export class SysDictType implements ISysDictType {
  constructor(
    public id?: number,
    public code?: string,
    public name?: string,
    public systemFlag?: string,
    public status?: string,
    public sort?: number,
    public description?: string,
    public createTime?: Date,
    public createUser?: number,
    public updateTime?: Date,
    public updateUser?: number
  ) {}
}
