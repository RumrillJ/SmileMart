import { useEffect, useState } from "react"
import { useUser } from "../../contexts/UserContext"
import { OrderInterface } from "../../interfaces/OrderInterface"
import { getAllOrders, getUserOrders, updateOrderStatus } from "../../api/orderAPI"
import axios from "axios"
import { backend } from "../../App"
import { toast } from "react-toastify"
import { useNavigate } from "react-router-dom"



export const Orders: React.FC = () => {

    const [orders, setOrders] = useState([] as OrderInterface[])
    const [status, setStatus] = useState<string>("Shipped")

    const navigate = useNavigate()

    async function getOrders() {
        if(user != null){
        if(user.role == "USER"){
            const response = await axios.get(backend("/orders/user"), {
            withCredentials: true,
            headers: {
                Authorization: "Bearer " + user.token
            }
        })
        setOrders(response.data)
            
            console.log("got user order" + response.data)
        } else if (user.role == "ADMIN"){
            const response = await axios.get(backend("/orders"), {
            withCredentials:true,
            headers: {
                Authorization: "Bearer " + user.token
            }
            })
            setOrders(response.data)
            console.log(response.data)
        }
    }
    }
    
    async function changeStatus(order: OrderInterface, newStatus: string) {
        const response = await axios.patch(backend("/orders/" + order.orderId + "/" + newStatus),
        {orderId: order.orderId, date: order.date, statusId: order.status.statusId, userId: order.user.userId}, {
            withCredentials:true,
            headers: {
                Authorization: "Bearer " + user!.token
            }
            })
        toast.info("Status Updated!")
        navigate("/")
        
    }

    const storeStatus = (input:any) => {
        setStatus(input.target.value)
    }
    
    const {user} = useUser()


    useEffect(() => {
		getOrders()
	}, [])

   
    console.log(orders)

    const ordersTsx = user!.role !== "ADMIN"? orders.map((order, index) => {
                let productTsx = order.products.map((product) => {
                    console.log(JSON.stringify(product.product))
                    return(
                            <h5>{JSON.stringify(product.product)}</h5>
                    )
                })
                return(
                    
                <div key={index}>
                <h3>{order.orderId}</h3>
                    <div>
                    {productTsx}
                    </div>
                <h3>{order.status.statusId}</h3>
                </div>
                )
            })
            :
            orders.map((order, index) => {
                console.log(order)
                return(
                    <div key={index}>
                        <h4>{order.orderId}</h4>
                        <h4>{order.status.statusId}</h4>
                        <button onClick={() => changeStatus(order, status)}>Change Status</button>
                        <select name="Status Filter" id="status-filter" onChange={storeStatus}>
                            <option value="Shipped">Shipped</option>
                            <option value="Delivered">Delivered</option>
                            <option value="Processing">Processing</option>
                        </select>
                    </div>
                )
            })
    console.log(ordersTsx)
                   
    //I cannot return the time/date as we have it
    return(
        <div>
            {ordersTsx}
        </div>
    )
}