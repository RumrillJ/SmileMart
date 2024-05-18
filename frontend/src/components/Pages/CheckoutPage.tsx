import { useNavigate } from "react-router-dom"
import { useUser } from "../../contexts/UserContext"
import { Checkout } from "../Checkout/Checkout"
import { Page } from "./Page"

export const CheckoutPage: React.FC = () => {
	const {user} = useUser()
	const navigate = useNavigate()
	
	
	return user !== null? (
		<Page>
            <Checkout/>
		</Page>
	) 
    :
    (
        <Page>
        <div>
            You Must Be Logged in to Checkout!
        </div>
		<button onClick={() => navigate("/login")}>Login</button>
        </Page>
    )
}