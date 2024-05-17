import { useUser } from "../../contexts/UserContext"
import { OrderInterface } from "../../interfaces/OrderInterface"

interface Props {
    orders: OrderInterface[]
}

export const Orders: React.FC<Props> = ({orders}) => {

    const {user} = useUser()

    const editButton = user?.userId == 1? 
        <button>Edit Status</button>
        :
        ""
    //I cannot return the time/date as we have it
    return(
        <div>
            {orders.map((order, index) => {
                return(
                <div>
                <h3>{order.order_id}</h3>
                <h3>{order.status_id}</h3>
                {editButton}
                </div>
                )
            })}
        </div>
    )
}