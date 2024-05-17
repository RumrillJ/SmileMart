export interface UserInterface {
    userId?: number;  // Optional to ensure compatibility in situations where it is not available (e.g., before login)
    username: string;
    password: string;
    role?: number; //role is an enum on the back end: 0 is user, 1 is admin
}
