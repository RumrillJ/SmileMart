import { useEffect, useState } from "react"
import { ProductsContainer } from "../Product/ProductsContainer"
import { ProductMenu } from "../Product/ProductMenu"

export const ProductsPage: React.FC = () => {

	const [products, setProducts] = useState([])

	// TODO: Get filter criteria from a menu component
	// // Send API request to get products by filter criteria
	useEffect(() => {
		setProducts((prevProducts) => [])
	}, [])

	return (
		<div>
			<h2>SmileMart</h2>
			<ProductMenu/>
			<ProductsContainer products={products}/>
		</div>
	)
}