import { useNavigate } from "react-router-dom"
import { useCart } from "../../contexts/CartContext"
import { ProductItem } from "../Product/ProductItem"
import  { Navbar, defaultLinks }  from "../Reusable/Navbar";


export const Cart: React.FC = () => {
	const { cart } = useCart()
	const navigate = useNavigate()
	return (
		<div>
			<h2>Your Cart: </h2>
			{Object.values(cart).map((product, index) => {
				return <ProductItem product={product} key={"prd" + index}/>
			})}
			<button onClick={() => navigate("/checkout")}>Continue to checkout</button>
		</div>
	)
}