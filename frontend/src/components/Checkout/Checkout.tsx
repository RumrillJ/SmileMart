import { useNavigate } from "react-router-dom";
import { useCart } from "../../contexts/CartContext";
import { postOrder } from "../../api/orderAPI";
import { toast } from 'react-toastify';


export const Checkout: React.FC = () => {

    //TODO: have the order return on confirmation to database and set it in useState here or in useContext
    const { cart } = useCart()
    const navigate = useNavigate();
    const {clearCart} = useCart();

    async function purchaseCart() {
        try{
        const response = await postOrder(cart)
        clearCart()
        toast.done("Checkout Complete!")
        navigate("/")
        }
        catch (e:any) {
            console.log(e)
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