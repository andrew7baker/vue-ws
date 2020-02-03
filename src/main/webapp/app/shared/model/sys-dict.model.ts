export interface ISysDict {
  id?: number;
  dicTypeId?: number;
  code?: string;
  name?: string;
  parentId?: number;
  parentIds?: string;
  status?: string;
  sort?: number;
  description?: string;
  createTime?: Date;
  createUser?: number;
  updateTime?: Date;
  updateUser?: number;
}

export class SysDict implements ISysDict {
  constructor(
    public id?: number,
    public dicTypeId?: number,
    public code?: string,
    public name?: string,
    public parentId?: number,
    public parentIds?: string,
    public status?: string,
    public sort?: number,
    public description?: string,
    public createTime?: Date,
    public createUser?: number,
    public updateTime?: Date,
    public updateUser?: number
  ) {}
}
