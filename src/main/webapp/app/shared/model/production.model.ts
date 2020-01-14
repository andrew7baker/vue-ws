export interface IProduction {
  id?: number;
  version?: string;
  powerTime?: number;
  placeTime?: number;
  waitTime?: number;
  runTime?: number;
  stopTime?: number;
  idleTime?: number;
  inWaitTime?: number;
  outWaitTime?: number;
  transTime?: number;
  wrongStopTime?: number;
  errorStopTIme?: number;
  wrongStopCount?: number;
  errorStopCount?: number;
  panelInCount?: number;
  panelOutCount?: number;
  panelCount?: number;
  pCBCount?: number;
  errorPcb?: number;
  skipPCB?: number;
  operationRate?: number;
  placementRate?: number;
  meanTime?: number;
  realTime?: number;
  transferTime?: number;
  placeCount?: number;
  theEfficiency?: number;
}

export class Production implements IProduction {
  constructor(
    public id?: number,
    public version?: string,
    public powerTime?: number,
    public placeTime?: number,
    public waitTime?: number,
    public runTime?: number,
    public stopTime?: number,
    public idleTime?: number,
    public inWaitTime?: number,
    public outWaitTime?: number,
    public transTime?: number,
    public wrongStopTime?: number,
    public errorStopTIme?: number,
    public wrongStopCount?: number,
    public errorStopCount?: number,
    public panelInCount?: number,
    public panelOutCount?: number,
    public panelCount?: number,
    public pCBCount?: number,
    public errorPcb?: number,
    public skipPCB?: number,
    public operationRate?: number,
    public placementRate?: number,
    public meanTime?: number,
    public realTime?: number,
    public transferTime?: number,
    public placeCount?: number,
    public theEfficiency?: number
  ) {}
}
