import { useCart } from "../../contexts/CartContext"
import { ProductItem } from "../Product/ProductItem"
import  { Navbar, defaultLinks }  from "../Reusable/Navbar";

export const Cart: React.FC = () => {
	const { cart } = useCart()

	return (
		<div>
			<div>
				<Navbar links={defaultLinks} />
			</div>
			<h2>Your Cart: </h2>
			{Object.values(cart).map((product, index) => {
				return <ProductItem product={product} key={"prd" + index}/>
			})}
			<button>Continue to checkout</button>
		</div>
	)
}