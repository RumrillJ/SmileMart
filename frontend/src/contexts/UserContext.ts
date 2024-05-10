import { Dispatch, SetStateAction, createContext } from "react";
import { UserInterface } from "../interfaces/UserInterface";

interface UserContextType {
	user: UserInterface | null
	setUser: Dispatch<SetStateAction<UserInterface>> | null
}

export const UserContext = createContext<UserContextType>({} as UserContextType)