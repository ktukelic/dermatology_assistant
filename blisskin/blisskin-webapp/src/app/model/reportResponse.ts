import {Patient} from './patient';

export interface ReportResponse {
  patientsWithSeriousSkinIssues: Patient[];
  patientsWithPossibleTSW: Patient[];
}
