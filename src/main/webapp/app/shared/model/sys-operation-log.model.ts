export interface ISysOperationLog {
  id?: number;
  logType?: string;
  logName?: string;
  userId?: number;
  className?: string;
  method?: string;
  succeed?: string;
  createTime?: Date;
  message?: string;
}

export class SysOperationLog implements ISysOperationLog {
  constructor(
    public id?: number,
    public logType?: string,
    public logName?: string,
    public userId?: number,
    public className?: string,
    public method?: string,
    public succeed?: string,
    public createTime?: Date,
    public message?: string
  ) {}
}
