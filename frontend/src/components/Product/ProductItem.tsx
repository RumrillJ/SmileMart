import { useCart } from "../../contexts/CartContext"
import { ProductInterface } from "../../interfaces/ProductInterface"

interface Props {
	product: ProductInterface
}

// TODO: conditional rendering of buttons for add to cart vs quantity?
// // And for Admin users to remove items from database
// // Get product image from S3 bucket /products/id

export const ProductItem: React.FC<Props> = ({ product }) => {
	const { cart, addToCart, removeFromCart } = useCart()

	return (
		<div>
			<h5>{JSON.stringify(product)}</h5>
			<h5>{cart[product.productId]?.quantity ?? ""}</h5>

			<button onClick={() => addToCart(product)}>
				Increase Quantity
			</button>
			<button onClick={() => removeFromCart(product)}>
				Decrease Quantity
			</button>
		</div>
	)
}
