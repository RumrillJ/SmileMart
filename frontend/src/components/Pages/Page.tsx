import { ReactNode } from "react"


interface Props {
	children: ReactNode
}

const Page: React.FC<Props> = ({ children }) => {
	return (
		<div>
			<main>{children}</main>
		</div>
	)
}