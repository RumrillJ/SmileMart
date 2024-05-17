import { deleteProduct } from "../../api/productsAPI"
import { useCart } from "../../contexts/CartContext"
import { useUser } from "../../contexts/UserContext"
import { ProductInterface } from "../../interfaces/ProductInterface"

interface Props {
	product: ProductInterface
	onDelete?: () => void
}

// TODO: conditional rendering of buttons for add to cart vs quantity?
// // And for Admin users to remove items from database
// // Get product image from S3 bucket /products/id

export const ProductItem: React.FC<Props> = ({ product, onDelete }) => {
	const { cart, addToCart, removeFromCart } = useCart()
	const { user } = useUser()

	return (
		<div>
			<h5>{JSON.stringify(product)}</h5>
			<h5>{cart[product.productId]?.quantity ?? ""}</h5>
			{user && user.role == "USER" ? (
				<>
					<button onClick={() => addToCart(product)}>
						Increase Quantity
					</button>
					<button onClick={() => removeFromCart(product)}>
						Decrease Quantity
					</button>
				</>
			) : null}

			{user && user.role == "ADMIN" ? (
				<>
					<button
						onClick={() => {
							deleteProduct(product)
							if (onDelete) {
								onDelete()
							}
						}}
					>
						Delete
					</button>
				</>
			) : null}
		</div>
	)
}
