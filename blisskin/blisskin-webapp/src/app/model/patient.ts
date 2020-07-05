export interface Patient {
  id: number;
  firstName?: string;
  lastName?: string;
  username?: string;
  password?: string;
  age?: number;
  previousTreatments?: any[];
}
