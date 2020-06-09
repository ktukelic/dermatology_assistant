export interface TreatmentRequest {
  username?: string;
  patientAge: number;
  humidity: Assessment;
  sunExposure: Assessment;
  skinProperties: any;
  skinIssues: any[];
}

export enum Assessment {
  LOW,
  NORMAL,
  HIGH
}

export enum Drug {
  ANTIFUNGAL,
  RETINOID,
  ANTIBIOTIC,
  CORTICOSTEROID,
  OTHER,
  NONE
}
