import { useEffect, useState } from "react"
import { OrderInterface } from "../../interfaces/OrderInterface"
import { Orders } from "../Orders/Orders"
import { Page } from "./Page"
import { useUser } from "../../contexts/UserContext"
import { getAllOrders, getUserOrders } from "../../api/orderAPI"

export const OrdersPage: React.FC = () => {

    const [orders, setOrders] = useState([] as OrderInterface[])
    const {user} = useUser()
    
    async function getOrders() {
        if(user != null){
        if(user.role == "USER"){
            const response = await getUserOrders(user.userId)
            setOrders(() => response.data)
        } else if (user.role == "ADMIN"){
            const response = await getAllOrders()
            setOrders(() => response.data)
        }
    }
    }


    useEffect(() => {
		getOrders()
	}, [])

	return user !== null? (
		<Page>
            <Orders orders={orders}/>
		</Page>
	) 
    :
    (
        <Page>
        <div>
            You Must Be Logged in to See Orders!
        </div>
        </Page>
    )
}