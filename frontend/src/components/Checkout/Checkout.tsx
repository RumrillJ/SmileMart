import { useNavigate } from "react-router-dom";
import { useCart } from "../../contexts/CartContext";
import { postOrder } from "../../api/orderAPI";
import { toast } from 'react-toastify';
import { useUser } from "../../contexts/UserContext";


export const Checkout: React.FC = () => {

    //TODO: have the order return on confirmation to database and set it in useState here or in useContext
    const { cart } = useCart()
    const {user} = useUser()
    const navigate = useNavigate();
    const {clearCart} = useCart();


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
			{Object.values(cart).map((product, index) => {
				return (<div key={index}>
                    <h5>{JSON.stringify(product)}</h5>
                    <h5>{cart[product.productId]?.quantity ?? ""}</h5>
                    </div>
                    )
			})}
            <button onClick={() => purchaseCart()}>Confirm Purchase</button>
        </div>
    )
}