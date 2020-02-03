export interface ISysDept {
  id?: number;
  parentId?: number;
  parentIds?: string;
  simpleName?: string;
  fullName?: string;
  description?: string;
  version?: string;
  status?: string;
  sort?: number;
  createTime?: Date;
  createUser?: number;
  updateTime?: Date;
  updateUser?: number;
}

export class SysDept implements ISysDept {
  constructor(
    public id?: number,
    public parentId?: number,
    public parentIds?: string,
    public simpleName?: string,
    public fullName?: string,
    public description?: string,
    public version?: string,
    public status?: string,
    public sort?: number,
    public createTime?: Date,
    public createUser?: number,
    public updateTime?: Date,
    public updateUser?: number
  ) {}
}
