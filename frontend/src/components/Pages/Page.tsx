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
			<main>{children}</main>
		</div>
	)
}