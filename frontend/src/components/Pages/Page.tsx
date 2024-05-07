import { ReactNode, useContext } from "react"
import { UserContext } from "../../contexts/UserContext"


interface Props {
	children?: ReactNode
}

export const Page: React.FC<Props> = ({ children }) => {

	// Example usage. Should use directly in the component where needed
	const {user, setUser} = useContext(UserContext)

	return (
		<div>
			<main>{children}</main>
		</div>
	)
}