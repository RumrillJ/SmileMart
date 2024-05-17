import { useEffect, useState } from "react"
import { ProductsContainer } from "../Product/ProductsContainer"
import { ProductMenu } from "../Product/ProductMenu"
import  { Navbar, defaultLinks }  from "../Reusable/Navbar";
import { Sidebar } from "../Reusable/Sidebar";

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
			<div>
				<Navbar links={defaultLinks} />
			</div>
			<ProductMenu/>
			<ProductsContainer products={products}/>
		</div>
	)
}