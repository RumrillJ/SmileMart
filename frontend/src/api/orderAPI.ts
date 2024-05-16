import axios from "axios";
import { ProductInterface } from "../interfaces/ProductInterface";
import { backend } from "../App";

export async function postOrder(orderCheckout: Record<number, ProductInterface>) {
    try {
        const response = await axios.post(backend("/checkout"))
        return response.data
    }
    catch (e:any) {
        return e.response.data
    }
}