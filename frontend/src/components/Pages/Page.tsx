import { ReactNode, useContext, useEffect } from "react"
import { UserContext } from "../../contexts/UserContext"
import { useNavigate } from "react-router-dom"


interface Props {
	children?: ReactNode
	requireAuth?: boolean
}

export const Page: React.FC<Props> = ({ children, requireAuth = false }) => {

	const {user, setUser} = useContext(UserContext)
	const navigate = useNavigate()

	// should just redirect on a 403 status

	useEffect(() => {

	}, [user])

	return (
		<div>
			<main>{children}</main>
		</div>
	)
}