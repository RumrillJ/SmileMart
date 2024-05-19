import { UserInterface } from "./UserInterface";

// SettingsInterface extends UserInterface to update user information.
export interface SettingsInterface { //} extends UserInterface {
    // from user
    username: string
    password: string

    firstName?: string;
    lastName?: string;
    email?: string;
    address?: string;
    city?: string;
    state?: string;
    zip?: string;
    country?: string;
    phoneNumber?: string;
    confirmPassword?: string;  
}
