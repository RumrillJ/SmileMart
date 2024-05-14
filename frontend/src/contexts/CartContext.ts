import { Dispatch, SetStateAction, createContext } from "react";
import { ProductInterface } from "../interfaces/ProductInterface";

interface CartContextType {
	cart: Record<number, ProductInterface> | null
	setCart: Dispatch<SetStateAction<Record<number, ProductInterface>>> | null
}

export const CartContext = createContext<CartContextType>({
	cart: null,
	setCart: null,
})
