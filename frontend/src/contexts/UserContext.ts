import { createContext, Dispatch, SetStateAction } from 'react';
import { UserInterface } from '../interfaces/UserInterface';

interface UserContextType {
    user: UserInterface | null;
    setUser: Dispatch<SetStateAction<UserInterface>> | null;
}

export const UserContext = createContext<UserContextType>({
    user: null,  // Assume no user is logged in initially
    setUser: null  // Will be set by the component that provides the context
});
