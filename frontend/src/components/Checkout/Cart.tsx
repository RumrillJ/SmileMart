import { useContext } from "react"
import { CartContext } from "../../contexts/CartContext"
import { ProductItem } from "../Product/ProductItem"


export const Cart: React.FC = () => {
	const { cart } = useContext(CartContext)

	return (
		<div>
			<h2>Your Order: </h2>
			<button>Continue to checkout</button>

			{cart?.map((product) => {
				return <ProductItem product={product} />
			})}
		</div>
	)
}