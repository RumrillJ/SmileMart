import axios from "axios";
import { ProductInterface } from "../interfaces/ProductInterface";
import { backend } from "../App";


async function getProduct(productId: number) { //}: ProductInterface | string {
	try {
		const response = await axios.get(backend("/products/" + productId))
		return response.data as ProductInterface
	} catch (e: any) {
		return e.response.data
	}
}

async function insertProduct(product: ProductInterface) {
	try {
		const response = await axios.post(backend("/products"), product)
		return response.data
	}
	catch (e: any) {

	}
}
