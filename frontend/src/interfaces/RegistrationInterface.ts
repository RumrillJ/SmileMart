import { UserInterface } from "./UserInterface";

export interface RegistrationInterface extends UserInterface {
    firstName: string;
    lastName: string;
    email: string;
    address?: string;
    city?: string;
    state?: string;
    zip?: string;
    country?: string;
    phoneNumber?: string;
    confirmPassword: string;  // Used for password validation during registration
}
