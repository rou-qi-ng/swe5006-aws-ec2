import { Availability } from "./availability.model";

export class Appointment {
    appointmentId!: number;
    appointmentUserId!: number;
    appointmentServiceId!: number;
    appointmentDate!: Date;
    appointmentTime!: string;
}
