import { ProductInterface } from "./ProductInterface"
import { UserInterface } from "./UserInterface"

export interface OrderInterface {
    orderId: number,
    date: Date,
    status: {
        statusId: number
    },
    products: Array<{
        product: ProductInterface
    }>,
    user: UserInterface
}