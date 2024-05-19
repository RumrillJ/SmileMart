import { useNavigate } from "react-router-dom"
import { useCart } from "../../contexts/CartContext"
import { ProductItem } from "../Product/ProductItem"
import  { Navbar, defaultLinks }  from "../Reusable/Navbar";
import "../../styles/cart.css"


export const Cart: React.FC = () => {
	const { cart } = useCart()
	const navigate = useNavigate()
	return (
		<div className="cart">
			<h2>Your Cart: </h2>
			<div className="cart-items">
			{Object.values(cart).map((product, index) => {
				return <ProductItem product={product} key={"prd" + index}/>
			})}
			</div>
			<button onClick={() => navigate("/checkout")}>Continue to checkout</button>
		</div>
	)
}