import { useEffect, useState } from "react"
import { ProductsContainer } from "../Product/ProductsContainer"
import { ProductMenu } from "../Product/ProductMenu"
import { getProducts } from "../../api/productsAPI"
import { ProductInterface } from "../../interfaces/ProductInterface"
import { Page } from "./Page"
import  { Navbar, defaultLinks }  from "../Reusable/Navbar";
import { Sidebar } from "../Reusable/Sidebar";

export const ProductsPage: React.FC = () => {

	const [products, setProducts] = useState([] as ProductInterface[])

	async function getAllProducts() {
		try {
			const response = await getProducts()
			setProducts(() => response.data)
		}
		catch (e: any) {
			console.log(e)
		}
	}

	useEffect(() => {
		getAllProducts()
	}, [])

	return (
		<Page>
			<h2>SmileMart</h2>

			<div>
				<Navbar links={defaultLinks} />
			</div>
			<ProductMenu/>
			<ProductsContainer products={products}/>
		</Page>
	)
}