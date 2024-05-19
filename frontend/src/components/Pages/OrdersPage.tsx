import { useEffect, useState } from "react"
import { OrderInterface } from "../../interfaces/OrderInterface"
import { Orders } from "../Orders/Orders"
import { Page } from "./Page"
import { useUser } from "../../contexts/UserContext"
import { getAllOrders, getUserOrders } from "../../api/orderAPI"
import { useNavigate } from "react-router-dom"

export const OrdersPage: React.FC = () => {


    const {user} = useUser()
    const navigate = useNavigate()
    


	return user !== null? (
		<Page>
            <Orders/>
		</Page>
	) 
    :
    (
        <Page>
        <div>
            You Must Be Logged in to See Orders!
        </div>
        <button onClick={() => navigate("/login")}>Login</button>
        </Page>
    )
}