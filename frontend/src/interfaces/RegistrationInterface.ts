import { UserInterface } from "./UserInterface";

// RegistrationInterface extends UserInterface for user registration. 
// userId is not included because it is typically assigned by the backend upon successful registration.
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
