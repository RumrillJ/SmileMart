import { ReactNode, useEffect } from "react"
import { useNavigate } from "react-router-dom"
import { useUser } from "../../contexts/UserContext"


interface Props {
	children?: ReactNode
	requireAuth?: boolean
}

export const Page: React.FC<Props> = ({ children, requireAuth = false }) => {

	const { user, setUser } = useUser()
	const navigate = useNavigate()

	// should just redirect on a 403 status

	useEffect(() => {

	}, [user])

	return (
		<div>
			<button onClick={() => navigate("/register")}>Register</button>
			<button onClick={() => navigate("/login")}>Login</button>
			<button onClick={() => navigate("/")}>Products</button>
			<button onClick={() => navigate("/add-product")}>AddProduct</button>
			<button onClick={() => navigate("/cart")}>Cart</button>
			<button onClick={() => navigate("/checkout")}>Checkout</button>
			<button onClick={() => navigate("/orders")}>Orders</button>
			<button onClick={() => navigate("/profile")}>Profile</button>
			<main>{children}</main>
		</div>
	)
}