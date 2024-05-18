export interface UserInterface {
    userId?: number  // Optional to ensure compatibility in situations where it is not available (e.g., before login)
    username: string
    password: string
    role?: string

    address?: string
    city?: string
    state?: string
    country?: string
    zip?: number
    firstName?: string
    lastName?: string
    phoneNumber?: number,
}
