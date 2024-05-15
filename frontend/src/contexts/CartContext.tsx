import { ReactNode, createContext, useContext, useState } from "react"
import { ProductInterface } from "../interfaces/ProductInterface"

interface CartContextType {
	cart: Record<number, ProductInterface>
	addToCart: (product: ProductInterface) => void
	removeFromCart: (product: ProductInterface) => void
	clearCart: () => void
}

const CartContext = createContext<CartContextType | undefined>(undefined)

interface Props {
	children: ReactNode
}

export const CartProvider: React.FC<Props> = ({ children }) => {
	const [cart, setCart] = useState({} as Record<number, ProductInterface>)

	const addToCart = (product: ProductInterface) => {
		const pid = product.productId
		setCart((prevCart) => ({
			...prevCart,
			[pid]: { ...product, quantity: (prevCart[pid]?.quantity ?? 0) + 1 },
		}))
	}

	const removeFromCart = (product: ProductInterface) => {
		const pid = product.productId
		setCart((prevCart) => {
			const updatedCart = { ...prevCart }

			if (updatedCart[pid]) {
				updatedCart[pid] = {
					...updatedCart[pid],
					quantity: (updatedCart[pid].quantity ?? 0) - 1,
				}

				if ((updatedCart[pid].quantity ?? 0) < 1) {
					delete updatedCart[pid]
				}
			}

			return updatedCart
		})
	}

	const clearCart = () => {
		setCart({})
	}

	return (
		<CartContext.Provider
			value={{
				cart: cart,
				addToCart: addToCart,
				removeFromCart: removeFromCart,
				clearCart: clearCart
			}}
		>
			{children}
		</CartContext.Provider>
	)
}

export const useCart = () => {
	const context = useContext(CartContext)
	if (!context) {
		throw Error
	}
	return context
}