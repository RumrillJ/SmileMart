import { useNavigate } from "react-router-dom";
import  { Navbar, defaultLinks }  from "../Reusable/Navbar";

export const Checkout: React.FC = () => {

    //TODO: have the order return on confirmation to database and set it in useState here or in useContext
    const navigate = useNavigate();
    return(
        <div>
			<div>
				<Navbar links={defaultLinks} />
			</div>
            <h2>Thank you for shopping with us!</h2>
            <p>Your order ID is: </p>
            <button onClick={() => navigate("/")}>Back to Products</button>
        </div>
    )
}