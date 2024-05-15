import { useCart } from "../../contexts/CartContext"
import { ProductItem } from "../Product/ProductItem"

export const Cart: React.FC = () => {
	const { cart } = useCart()

	return (
		<div>
			<h2>Your Cart: </h2>
			{Object.values(cart).map((product, index) => {
				return <ProductItem product={product} key={"prd" + index}/>
			})}
			<button>Continue to checkout</button>
		</div>
	)
}