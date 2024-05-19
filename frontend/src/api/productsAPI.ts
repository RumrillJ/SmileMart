import axios from "axios";
import { ProductInterface } from "../interfaces/ProductInterface";
import { backend } from "../App";


export async function getProducts() {
	const response = await axios.get(backend("/products"))
	return response
}

export async function getProduct(productId: number) { //}: ProductInterface | string {
	try {
		const response = await axios.get(backend("/products/" + productId))
		return response.data as ProductInterface
	} catch (e: any) {
		return e.response.data
	}
}


export async function insertProduct(product: ProductInterface) {
	const response = await axios.post(backend("/products"), product)
	return response.data
}

export async function deleteProduct(product: ProductInterface) {
	const response = await axios.delete(backend("/products/" + product.productId))
	return response.data
}