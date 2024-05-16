import { useNavigate } from "react-router-dom";

export const Checkout: React.FC = () => {

    //TODO: have the order return on confirmation to database and set it in useState here or in useContext
    const navigate = useNavigate();
    return(
        <div>
            <h2>Thank you for shopping with us!</h2>
            <p>Your order ID is: </p>
            <button onClick={() => navigate("/")}>Back to Products</button>
        </div>
    )
}