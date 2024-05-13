import { Dispatch, SetStateAction, createContext } from "react";
import { ProductInterface } from "../interfaces/ProductInterface";

interface CartContextType {
	cart: ProductInterface[] | null
	setCart: Dispatch<SetStateAction<ProductInterface[]>> | null
}

export const CartContext = createContext<CartContextType>({
	cart: null,
	setCart: null,
})
