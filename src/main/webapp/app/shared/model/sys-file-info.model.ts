export interface ISysFileInfo {
  id?: number;
  fileBucket?: string;
  fileName?: string;
  fileSuffix?: string;
  fileSizeKb?: number;
  finalName?: string;
  filePath?: string;
  createTime?: Date;
  createUser?: number;
  updateTime?: Date;
  updateUser?: number;
}

export class SysFileInfo implements ISysFileInfo {
  constructor(
    public id?: number,
    public fileBucket?: string,
    public fileName?: string,
    public fileSuffix?: string,
    public fileSizeKb?: number,
    public finalName?: string,
    public filePath?: string,
    public createTime?: Date,
    public createUser?: number,
    public updateTime?: Date,
    public updateUser?: number
  ) {}
}
