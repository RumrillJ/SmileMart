import axios from "axios";
import { ProductInterface } from "../interfaces/ProductInterface";
import { backend } from "../App";

export async function getAllOrders() {
    try{
        const response = await axios.get(backend("/orders"))
        return response.data
    } 
    catch (e:any) {
        return e.response.data
    }
}

export async function getUserOrders(userId: number){
    try{
        const response = await axios.get(backend("/orders/" + userId))
        return response.data
    }
    catch (e:any) {
        return e.response.data
    }
}
    
export async function updateOrderStatus(orderId: number, status: string){
    try{
        const response = await axios.get(backend("/orders/" + orderId + "/" + status))
        return response.data
    }
    catch (e:any) {
        return e.response.data
    }
}

export async function postOrder(orderCheckout: Record<number, ProductInterface>) {
    try {
        const response = await axios.post(backend("/orders/checkout"))
        return response.data
    }
    catch (e:any) {
        return e.response.data
    }
}