import axios from "axios";
import { ProductInterface } from "../interfaces/ProductInterface";


async function getProduct(productId: number) { //}: ProductInterface | string {
	try {
		const response = await axios.get(
			"http://localhost:8080/products/" + productId
		)
		return response.data as ProductInterface
	} catch (e: any) {
		return e.response.data
	}
}