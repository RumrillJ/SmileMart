import axios from "axios";
import { ProductInterface } from "../interfaces/ProductInterface";
import { backend } from "../App";
import { UserInterface } from "../interfaces/UserInterface";

export async function getAllOrders(user: UserInterface) {
    try{
        const response = await axios.get(backend("/orders"), {
            withCredentials:true,
            headers: {
                Authorization: "Bearer " + user.token
            }
        })
        return response.data
    } 
    catch (e:any) {
        return e.response.data
    }
}

export async function getUserOrders(user: UserInterface){ //can't feed it just numbers due to interface having it be optional
    try{
        console.log(user.token)
        const response = await axios.get(backend("/orders/user"), {
            withCredentials: true,
            headers: {
                Authorization: "Bearer " + user.token
            }
        })
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

export async function postOrder(orderCheckout: Object[]) {
    try {
        console.log({...orderCheckout})
        const response = await axios.post(backend("/orders/checkout"), orderCheckout)
        return response.data
    }
    catch (e:any) {
        return e.response.data
    }
}