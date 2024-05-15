import { createContext, Dispatch, ReactNode, SetStateAction, useContext, useState } from 'react';
import { UserInterface } from '../interfaces/UserInterface';

interface UserContextType {
    user: UserInterface | null;
    setUser: Dispatch<SetStateAction<UserInterface | null>>;
}

const UserContext = createContext<UserContextType | undefined>(undefined)

interface Props {
    children: ReactNode
}

export const UserProvider: React.FC<Props> = ({ children }) => {
    const [user, setUser] = useState(null as UserInterface | null)

    return (
        <UserContext.Provider value={{user, setUser}}>
            {children}
        </UserContext.Provider>
    )
}

export const useUser = () => {
    const context = useContext(UserContext)
    if (!context) {
        throw Error
    }
    return context
}