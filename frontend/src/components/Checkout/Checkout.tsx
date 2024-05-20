import { useNavigate } from "react-router-dom";
import { useCart } from "../../contexts/CartContext";
import { postOrder } from "../../api/orderAPI";
import { toast } from 'react-toastify';
import { useUser } from "../../contexts/UserContext";
import "../../styles/checkout.css"

export const Checkout: React.FC = () => {

    //TODO: have the order return on confirmation to database and set it in useState here or in useContext
    const { cart } = useCart()
    const {user} = useUser()
    const navigate = useNavigate();
    const {clearCart} = useCart();
    let total = 0;


    async function purchaseCart() {
        if(user == null || user.username == ""){
            toast.error("You must be logged in!")
        } else {
            try{
            let finalCart:Object[] = Object.values(cart).map((product, index) => {
                return {
                    productId: product.productId,
                    quantity: product.quantity
                }
            })
            console.log(finalCart)
            const response = await postOrder(finalCart)
            console.log(response)
            clearCart()
            toast.done("Checkout Complete!")
            navigate("/")
        }
        catch (e:any) {
            console.log(e)
        }
        }

    } 
    return(
        <div>
            <h2>Your Cart: </h2>
            <table className="checkout-table">
                <thead>
                    <tr>
                        <th></th>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Category</th>
                        <th>Description</th>
                        <th>Quantity</th>
                    </tr>
                </thead>
                <tbody>
                    {Object.values(cart).map((product, index) => {
                        const quantity = product.quantity || 1;
                        total += (product.cost * quantity)
				return (
                    <tr key={index}>
                        <th>{index+1}</th>
                        <td>{product.name}</td>
                        <td>${product.cost}</td>
                        <td>{product.category.description}</td>
                        <td>{product.description}</td>
                        <td>{product.quantity}</td>
                    </tr>
                    )
			})}
                </tbody>
            </table>
            <h4>Total: ${total.toFixed(2)}</h4>
			
            <button onClick={() => purchaseCart()}>Confirm Purchase</button>
        </div>
    )
}