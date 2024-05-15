export interface UserInterface {
    userId?: number;  // Optional to ensure compatibility in situations where it is not available (e.g., before login)
    username: string;
    password: string;
}
