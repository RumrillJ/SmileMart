import { deleteProduct } from "../../api/productsAPI"
import { useCart } from "../../contexts/CartContext"
import { useUser } from "../../contexts/UserContext"
import { ProductInterface } from "../../interfaces/ProductInterface"
import  { Navbar, defaultLinks }  from "../Reusable/Navbar";
import "../../styles/productitem.css"

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
		<div className="item-container">
			<h3>{product.name}</h3>
			<h6 className="category-section">Category: {product.category.description}</h6>
			<h6 className="category-section">Price: ${product.cost}</h6>
			<p className="product-details"><b>Product Details: </b>{product.description}</p>
		
			{user && user.role == "USER" ? (
				<div className="quantity">
					<h6>Quantity: </h6>
					<button onClick={() => addToCart(product)}>
						+
					</button>
					{cart[product.productId]?.quantity ?? 0}
					<button onClick={() => removeFromCart(product)}>
						-
					</button>
				</div>
			) : null}

			{user && user.role == "ADMIN" ? (
				<>
					<button
						onClick={async () => {
							await deleteProduct(product)
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
